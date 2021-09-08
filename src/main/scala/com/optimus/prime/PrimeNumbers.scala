package com.optimus.prime

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.predicates.all.Greater

object PrimeNumbers {

  type GreaterThanOne = Int Refined Greater[W.`1`.T]

  private def eratostheneSieve(s: LazyList[Int]): LazyList[Int] = s.head #:: eratostheneSieve(s.tail.filter(_ % s.head != 0))
  private final val primes: LazyList[Int]                       = eratostheneSieve(LazyList.from(2))
  def primeNumbersUpTo(n: GreaterThanOne): LazyList[Int]        = primes.takeWhile(_ <= n)

}
