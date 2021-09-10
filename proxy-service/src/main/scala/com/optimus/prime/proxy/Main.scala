package com.optimus.prime.proxy

import com.optimus.prime.prime_numbers.ZioPrimeNumbers.PrimeNumbersClient
import com.optimus.prime.proxy.configuration.ProxyServiceConfiguration
import io.grpc.ManagedChannelBuilder
import scalapb.zio_grpc.ZManagedChannel
import zio._
import eu.timepit.refined.auto._
import org.http4s.server.Server
import zio.clock.Clock

object Main extends App {

  override def run(args: List[String]): URIO[ZEnv, ExitCode] = {
    val resource: RManaged[Clock, Server[RIO[Clock, *]]] =
      for {
        configuration                  <- ProxyServiceConfiguration.load.toManaged_
        httpServerConfiguration         = configuration.httpServer
        primeNumbersClientConfiguration = configuration.primeNumbersClient
        service                        <- PrimeNumbersClient.managed(
                                            ZManagedChannel(
                                              ManagedChannelBuilder
                                                .forAddress(
                                                  primeNumbersClientConfiguration.host,
                                                  primeNumbersClientConfiguration.port
                                                )
                                                .usePlaintext()
                                            )
                                          )
        proxy                          <- ServerBuilder.build(service, httpServerConfiguration)
      } yield proxy

    resource.useForever.exitCode
  }

}
