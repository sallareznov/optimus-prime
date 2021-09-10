package com.optimus.prime.proxy.healthcheck

import org.http4s.HttpRoutes
import sttp.tapir.server.http4s.ztapir.ZHttp4sServerInterpreter
import zio.clock.Clock
import zio.interop.catz._
import zio.{RIO, UIO}

final class HealthCheckController extends HealthCheckAPI {

  val routes: HttpRoutes[RIO[Clock, *]] =
    ZHttp4sServerInterpreter.from(healthCheckEndpoint)(_ => UIO("optimus is healthy")).toRoutes

}
