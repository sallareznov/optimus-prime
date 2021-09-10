package com.optimus.prime.proxy.configuration

import com.optimus.prime.proxy.configuration.ProxyServiceConfiguration._
import eu.timepit.refined.pureconfig._
import eu.timepit.refined.types.net.NonSystemPortNumber
import eu.timepit.refined.types.string.NonEmptyString
import pureconfig._
import pureconfig.generic.auto._
import zio.Task

final case class ProxyServiceConfiguration(
    httpServer: ServiceConfiguration,
    primeNumbersClient: ServiceConfiguration
)

object ProxyServiceConfiguration {

  final case class ServiceConfiguration(
      host: NonEmptyString,
      port: NonSystemPortNumber
  )

  def load: Task[ProxyServiceConfiguration] = Task.effect(ConfigSource.default.loadOrThrow[ProxyServiceConfiguration])

}
