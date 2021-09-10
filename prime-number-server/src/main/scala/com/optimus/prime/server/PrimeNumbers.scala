package com.optimus.prime.server

import com.optimus.prime.contracts.Types.GreaterThanOne
import eu.timepit.refined.auto._

object PrimeNumbers {

  private def eratostheneSieve(s: LazyList[Int]): LazyList[Int] = s.head #:: eratostheneSieve(s.tail.filter(_ % s.head != 0))

  private final val primes: LazyList[Int] = eratostheneSieve(LazyList.from(2))

  def primeNumbersUpTo(n: GreaterThanOne): LazyList[Int] = primes.takeWhile(_ <= n)

}
