package com.optimus.prime.proxy.logging

import org.http4s.HttpRoutes
import org.http4s.server.middleware.Logger
import zio.RIO
import zio.clock.Clock
import zio.interop.catz._

trait LoggingMiddleware {

  implicit final class ServerWithLogging(routes: HttpRoutes[RIO[Clock, *]]) {
    def logged: HttpRoutes[RIO[Clock, *]] = Logger.httpRoutes(logHeaders = true, logBody = true)(routes)
  }

}
