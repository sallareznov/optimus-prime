package com.optimus.prime.proxy

import cats.implicits._
import com.optimus.prime.prime_numbers.ZioPrimeNumbers.PrimeNumbersClient.Service
import com.optimus.prime.proxy.configuration.ProxyServiceConfiguration.ServiceConfiguration
import com.optimus.prime.proxy.documentation.DocumentationController
import com.optimus.prime.proxy.healthcheck.HealthCheckController
import com.optimus.prime.proxy.logging.LoggingMiddleware
import com.optimus.prime.proxy.numbers.PrimeNumbersController
import eu.timepit.refined.auto._
import org.http4s.HttpApp
import org.http4s.implicits._
import org.http4s.server.jetty.JettyBuilder
import org.http4s.server.{Router, Server}
import zio._
import zio.clock.Clock
import zio.interop.catz._

object ServerBuilder extends LoggingMiddleware {

  def build(service: Service, configuration: ServiceConfiguration): RManaged[Clock, Server[RIO[Clock, *]]] = {
    val primeNumbersController  = new PrimeNumbersController(service)
    val healthCheckController   = new HealthCheckController
    val documentationController = new DocumentationController(primeNumbersController.endpoints)

    val router: HttpApp[RIO[Clock, *]] =
      Router(
        "/" -> (
          primeNumbersController.routes.logged <+>
            healthCheckController.routes.logged <+>
            documentationController.routes
        )
      ).orNotFound

    for {
      implicit0(runtime: Runtime[Clock]) <- RIO.runtime[Clock].toManaged_
      server                             <- JettyBuilder[RIO[Clock, *]]
                                              .bindHttp(configuration.port, configuration.host)
                                              .mountHttpApp(router, "")
                                              .resource
                                              .toManaged
    } yield server
  }

}
