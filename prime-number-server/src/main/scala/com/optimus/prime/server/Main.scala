package com.optimus.prime.server

import scalapb.zio_grpc.{ServerMain, ServiceList}
import zio.ZEnv

object Main extends ServerMain {

  override def services: ServiceList[ZEnv] = ServiceList.add(PrimeNumbersServer)

}
