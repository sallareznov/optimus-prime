package com.optimus.prime.contracts

import eu.timepit.refined.W
import eu.timepit.refined.api.Refined
import eu.timepit.refined.predicates.all.Greater

object Types {

  type GreaterThanOne = Int Refined Greater[W.`1`.T]

}
