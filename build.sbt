import CrossVersion._
import CucumberSettings.cucumberSettings
import Dependencies._
import DependenciesScopes._

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

lazy val optimusPrime = Project("optimus-prime", base = file("."))
  .aggregate(contracts, proxyService, primeNumberServer, behaviour)

lazy val contracts =
  project
    .in(file("contracts"))
    .settings(
      Compile / PB.targets := Seq(
        scalapb.gen(grpc = true)          -> (Compile / sourceManaged).value / "scalapb",
        scalapb.zio_grpc.ZioCodeGenerator -> (Compile / sourceManaged).value / "scalapb"
      )
    )
    .settings(
      libraryDependencies ++=
        Seq(
          grpcNetty,
          logbackClassic,
          logstashLogbackEncoder,
          refined,
          scalapbRuntimeGrpc,
          slf4zio
        )
    )

lazy val proxyService =
  Project("proxy-service", base = file("proxy-service"))
    .dependsOn(contracts)
    .settings(
      addCompilerPlugin(betterMonadicFor),
      addCompilerPlugin(kindProjector cross full)
    )
    .settings(
      libraryDependencies ++=
        compileDependencies(
          circeGenericExtras,
          http4sJetty,
          logbackClassic,
          logstashLogbackEncoder,
          "com.github.pureconfig" %% "pureconfig" % Versions.pureconfig,
          pureconfigGeneric,
          refinedPureconfig,
          slf4zio,
          tapirJsonCirce,
          tapirOpenapiCirceYaml,
          tapirOpenapiDocs,
          tapirRefined,
          tapirSwaggerUiHttp4s,
          tapirZio,
          tapirZioHttp4sServer
        )
    )

lazy val primeNumberServer =
  Project("prime-number-server", base = file("prime-number-server"))
    .dependsOn(contracts)
    .settings(
      libraryDependencies ++=
        compileDependencies(
          zioStreams
        )
    )
    .settings(
      libraryDependencies ++= testDependencies(scalatest)
    )

lazy val behaviour =
  project
    .in(file("behaviour"))
    .dependsOn(proxyService, primeNumberServer)
    .settings(cucumberSettings("com.optimus.prime.behaviour.CucumberTests"))
    .settings(
      libraryDependencies ++=
        testDependencies(
          cucumberScala,
          requests,
          scalatest
        )
    )
