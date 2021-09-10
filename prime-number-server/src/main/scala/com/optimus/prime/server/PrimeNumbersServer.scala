package com.optimus.prime.server

import com.github.mlangc.slf4zio.api._
import com.optimus.prime.contracts.Types.GreaterThanOne
import com.optimus.prime.prime_numbers.ZioPrimeNumbers.ZPrimeNumbers
import com.optimus.prime.prime_numbers.{PrimeNumbersRequest, PrimeNumbersResponse}
import eu.timepit.refined.api.RefType
import io.grpc.Status
import zio.ZEnv
import zio.stream.ZStream

object PrimeNumbersServer extends ZPrimeNumbers[ZEnv, Any] with LoggingSupport {

  override def getPrimeNumbers(request: PrimeNumbersRequest): ZStream[ZEnv, Status, PrimeNumbersResponse] = {
    logger.info(s"received number: ${request.number}")

    RefType.applyRef[GreaterThanOne](request.number) match {
      case Left(input)   =>
        logger.info(s"invalid number: $input. the input must be a number greater than 1")
        ZStream.fail(Status.INVALID_ARGUMENT)
      case Right(number) =>
        val primeNumbers = PrimeNumbers.primeNumbersUpTo(number)
        ZStream.fromIteratorTotal(primeNumbers.iterator).map(PrimeNumbersResponse(_))
    }
  }

}
