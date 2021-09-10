package com.optimus.prime.behaviour

import com.optimus.prime.behaviour.AppContext.proxyAppPort
import io.cucumber.scala.{EN, ScalaDsl}
import org.scalatest.matchers.should.Matchers
import requests._

object PrimeNumbersSteps extends ScalaDsl with EN with Matchers {

  final var response: Response = _

  Given("""the user asks for the prime numbers up to the number '{int}'""") { (number: Int) =>
    response = callService(number.toString)
  }

  Given("""the user asks for the prime numbers with the input {string}""") { (input: String) =>
    response = callService(input)
  }

  Then("""the system answers with the numbers {string}""") { (primeNumbers: String) =>
    response.statusCode shouldBe 200
    response.text() shouldBe primeNumbers
  }

  Then("""the system answers with an error""")(() => response.is4xx)

  def callService(input: String): Response = get(s"http://localhost:$proxyAppPort/prime/$input", check = false)

}
