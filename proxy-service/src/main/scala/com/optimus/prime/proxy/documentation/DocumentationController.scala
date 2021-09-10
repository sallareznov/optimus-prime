package com.optimus.prime.proxy.documentation

import org.http4s.HttpRoutes
import sttp.tapir.Endpoint
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.swagger.http4s.SwaggerHttp4s
import zio.RIO
import zio.clock.Clock
import zio.interop.catz._

final class DocumentationController(endpoints: List[Endpoint[_, _, _, _]]) {

  private val documentationYaml: String =
    OpenAPIDocsInterpreter
      .toOpenAPI(endpoints.sortBy(_.renderPathTemplate()), "optimus-prime", "1.0")
      .toYaml

  val routes: HttpRoutes[RIO[Clock, *]] = new SwaggerHttp4s(documentationYaml).routes

}
