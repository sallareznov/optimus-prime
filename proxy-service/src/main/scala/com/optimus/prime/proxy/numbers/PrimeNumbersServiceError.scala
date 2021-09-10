package com.optimus.prime.proxy.numbers

final case class PrimeNumbersServiceError(code: String, message: String) extends RuntimeException(message)

object PrimeNumbersServiceError {

  final val serviceUnavailable = PrimeNumbersServiceError("UNAVAILABLE", "Service unavailable")
  final val unknownError       = PrimeNumbersServiceError("UNKNOWN", "An unknown error has been encountered")

}
