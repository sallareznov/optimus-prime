package com.optimus.prime.proxy.numbers

import com.optimus.prime.prime_numbers.PrimeNumbersRequest
import com.optimus.prime.prime_numbers.ZioPrimeNumbers.PrimeNumbersClient.Service
import com.optimus.prime.proxy.numbers.PrimeNumbersServiceError._
import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.predicates.all.Greater
import io.grpc.Status
import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.Http4sServerInterpreter
import zio._
import zio.clock.Clock
import zio.interop.catz._
import zio.stream._
import zio.stream.interop.fs2z._

final class PrimeNumbersController(service: Service) extends PrimeNumbersAPI {

  val getPrimeNumbersUpToRoute: HttpRoutes[RIO[Clock, *]] =
    Http4sServerInterpreter.toRouteRecoverErrors(getPrimeNumbersUpToEndpoint) {
      (retrievePrimeNumbers _ andThen transformToBytes andThen transformToTask)(_)
    }

  private def retrievePrimeNumbers(number: Int Refined Greater[W.`1`.T]): Stream[PrimeNumbersServiceError, Int] =
    service
      .getPrimeNumbers(PrimeNumbersRequest(number.value))
      .either
      .flatMap {
        case Right(response)                                              => ZStream.succeed(response.primeNumber)
        case Left(status) if status.getCode == Status.UNAVAILABLE.getCode => ZStream.fail(serviceUnavailable)
        case Left(_)                                                      => ZStream.fail(unknownError)
      }

  private def transformToBytes(stream: Stream[PrimeNumbersServiceError, Int]): Stream[PrimeNumbersServiceError, Byte] =
    stream
      .map(_.toString)
      .intersperse(",")
      .transduce(toCharStream)
      .map(_.toByte)

  private def transformToTask(stream: Stream[PrimeNumbersServiceError, Byte]): Task[fs2.Stream[Task, Byte]] =
    stream.runDrain *> Task(stream.toFs2Stream)

  private val toCharStream: ZTransducer[Any, Nothing, String, Char] =
    ZTransducer
      .fromFunction[String, Chunk[Char]](s => Chunk.fromArray(s.toArray))
      .mapChunks(_.flatten)

  val routes: HttpRoutes[RIO[Clock, *]] = getPrimeNumbersUpToRoute

}
