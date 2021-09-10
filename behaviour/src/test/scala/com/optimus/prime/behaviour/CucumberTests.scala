package com.optimus.prime.behaviour

import io.cucumber.core.cli.Main

import scala.util.Try

object CucumberTests extends App {

  AppContext.beforeAll()

  Try(
    Main.main(
      Array(
        "--strict",
        "-g",
        "com.optimus.prime.behaviour",
        "--plugin",
        "pretty",
        "--plugin",
        "html:target/cucumber",
        "behaviour/src/test/resources"
      ): _*
    )
  )

  AppContext.afterAll()

}
