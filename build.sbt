inThisBuild {
  List(
    isSnapshot                := true,
    run / fork                := true,
    scalacOptions ++= List("-language:postfixOps"),
    scalafmtOnCompile         := true,
    scalafmtCheck             := true,
    scalafmtSbtCheck          := true,
    scalaVersion              := "2.13.6",
    Test / fork               := true,
    Test / scalafmtCheck      := true,
    Test / scalafmtOnCompile  := true,
    Test / scalafmtSbtCheck   := true,
    Test / testForkedParallel := false,
    Test / javaOptions += "-Dconfig.resource=application-test.conf"
  )
}

lazy val optimusPrime = Project(id = "optimus-prime", base = file("."))
