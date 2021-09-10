package com.optimus.prime.proxy.numbers

import com.optimus.prime.contracts.Types.GreaterThanOne
import com.optimus.prime.proxy.error.ErrorCodecs
import sttp.capabilities.fs2.Fs2Streams
import sttp.model.StatusCode.Ok
import sttp.tapir.SchemaType.SString
import sttp.tapir._
import sttp.tapir.codec.refined.TapirCodecRefined
import sttp.tapir.generic.SchemaDerivation
import sttp.tapir.json.circe.jsonBody
import zio.RIO
import zio.clock.Clock

import java.nio.charset.StandardCharsets

trait PrimeNumbersAPI extends ErrorCodecs with SchemaDerivation with TapirCodecRefined {

  final val getPrimeNumbersUpToEndpoint =
    endpoint.get
      .in("prime" / path[GreaterThanOne]("number"))
      .name("retrieves a sequence of prime numbers up to the given number")
      .summary("retrieves a sequence of prime numbers up to the given number")
      .tag("prime")
      .out(statusCode(Ok))
      .out(streamBody(Fs2Streams[RIO[Clock, *]])(Schema(SString), CodecFormat.TextPlain(), Some(StandardCharsets.UTF_8)))
      .errorOut(jsonBody[PrimeNumbersServiceError])

  final val endpoints = List(getPrimeNumbersUpToEndpoint)

}
