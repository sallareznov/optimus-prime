import sbt._

object Versions {

  final val refined   = "0.9.27"
  final val scalatest = "3.2.9"
  final val zio       = "1.0.11"

}

object Dependencies {

  final val refined    = "eu.timepit"    %% "refined"     % Versions.refined
  final val scalatest  = "org.scalatest" %% "scalatest"   % Versions.scalatest
  final val zioStreams = "dev.zio"       %% "zio-streams" % Versions.zio

}
