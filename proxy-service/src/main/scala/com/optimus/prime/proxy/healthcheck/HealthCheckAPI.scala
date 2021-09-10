package com.optimus.prime.proxy.healthcheck

import sttp.model.StatusCode.Ok
import sttp.tapir.ztapir._
import sttp.tapir.{endpoint, statusCode}

trait HealthCheckAPI {

  final val healthCheckEndpoint =
    endpoint.get
      .in("health")
      .name("health check")
      .summary("health check")
      .tag("health")
      .out(statusCode(Ok))
      .out(stringBody)

  final val endpoints = List(healthCheckEndpoint)

}
