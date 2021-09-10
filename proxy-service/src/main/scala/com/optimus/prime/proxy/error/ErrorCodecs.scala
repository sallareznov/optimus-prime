package com.optimus.prime.proxy.error

import com.optimus.prime.proxy.numbers.PrimeNumbersServiceError
import io.circe.Codec
import io.circe.generic.extras.defaults._
import io.circe.generic.extras.semiauto._

trait ErrorCodecs {

  implicit final val primeNumbersServiceError: Codec[PrimeNumbersServiceError] = deriveConfiguredCodec

}
