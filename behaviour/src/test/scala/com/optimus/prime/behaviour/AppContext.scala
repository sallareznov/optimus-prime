package com.optimus.prime.behaviour

import com.optimus.prime._
import com.optimus.prime.proxy.configuration.ProxyServiceConfiguration
import eu.timepit.refined.types.net.NonSystemPortNumber
import org.scalatest.concurrent.Eventually
import org.scalatest.matchers.should.Matchers
import org.slf4j.{Logger, LoggerFactory}
import requests.get
import zio.Exit.{Failure, Success}
import zio.Runtime.default._
import zio.console.Console
import zio.{ExitCode, URIO, ZEnv}

import scala.concurrent.duration._

object AppContext extends Eventually with Matchers {

  private val log: Logger = LoggerFactory.getLogger(classOf[AppContext.type])

  private val serverApp: URIO[ZEnv with Console, ExitCode] = server.Main.run(Nil)
  private val proxyApp: URIO[ZEnv, ExitCode]               = proxy.Main.run(Nil)

  final val proxyAppPort: NonSystemPortNumber = unsafeRun(ProxyServiceConfiguration.load).httpServer.port

  def beforeAll(): Unit = {
    unsafeRunAsync(serverApp) {
      case Success(code)  => log.info(s"App terminated with code $code")
      case Failure(error) => log.error(s"App failed with $error", error)
    }

    unsafeRunAsync(proxyApp) {
      case Success(code)  => log.info(s"App terminated with code $code")
      case Failure(error) => log.error(s"App failed with $error", error)
    }

    withClue("Wait for application to start") {
      eventually(timeout(1.minute), interval(5.seconds)) {
        get(s"http://localhost:$proxyAppPort/health").statusCode shouldBe 200
      }
    }

    ()
  }

  def afterAll(): Unit = {
    unsafeRun(serverApp.interruptAllChildren)
    unsafeRun(proxyApp.interruptAllChildren)
    ()
  }

}
