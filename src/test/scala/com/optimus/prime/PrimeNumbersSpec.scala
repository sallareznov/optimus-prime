package com.optimus.prime

import com.optimus.prime.PrimeNumbers._
import eu.timepit.refined.auto._
import org.scalatest.funsuite.AnyFunSuiteLike
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.TableFor2

final class PrimeNumbersSpec extends AnyFunSuiteLike with Matchers {

  test("sequence of prime numbers up to a given number") {
    val table: TableFor2[GreaterThanOne, List[Int]] =
      Table(
        ("number", "prime numbers"),
        (2, List(2)),
        (17, List(2, 3, 5, 7, 11, 13, 17)),
        (38, List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37)),
        (59, List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59)),
        (75, List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73)),
        (96, List(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89))
      )

    forAll(table)((number, primeNumbers) => primeNumbersUpTo(number).toList shouldBe primeNumbers)
  }

}
