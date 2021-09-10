import sbt._

object Versions {

  final val betterMonadicFor       = "0.3.1"
  final val circeGenericExtras     = "0.14.1"
  final val cucumberScala          = "7.0.0"
  final val grpcNetty              = "1.40.1"
  final val http4s                 = "0.21.26"
  final val kindProjector          = "0.13.0"
  final val logbackClassic         = "1.2.6"
  final val logbackLogstashEncoder = "6.6"
  final val pureconfig             = "0.14.1"
  final val refined                = "0.9.23"
  final val requests               = "0.6.9"
  final val scalapbRuntime         = scalapb.compiler.Version.scalapbVersion
  final val scalatest              = "3.2.9"
  final val slf4zio                = "1.0.0"
  final val tapir                  = "0.17.20"
  final val zio                    = "1.0.11"

}

object Dependencies {

  final val betterMonadicFor       = "com.olegpy"                  %% "better-monadic-for"       % Versions.betterMonadicFor
  final val circeGenericExtras     = "io.circe"                    %% "circe-generic-extras"     % Versions.circeGenericExtras
  final val cucumberScala          = "io.cucumber"                 %% "cucumber-scala"           % Versions.cucumberScala
  final val grpcNetty              = "io.grpc"                      % "grpc-netty"               % Versions.grpcNetty
  final val http4sJetty            = "org.http4s"                  %% "http4s-jetty"             % Versions.http4s
  final val kindProjector          = "org.typelevel"                % "kind-projector"           % Versions.kindProjector
  final val logbackClassic         = "ch.qos.logback"               % "logback-classic"          % Versions.logbackClassic
  final val logstashLogbackEncoder = "net.logstash.logback"         % "logstash-logback-encoder" % Versions.logbackLogstashEncoder
  final val pureconfigGeneric      = "com.github.pureconfig"       %% "pureconfig-generic"       % Versions.pureconfig
  final val refined                = "eu.timepit"                  %% "refined"                  % Versions.refined
  final val refinedPureconfig      = "eu.timepit"                  %% "refined-pureconfig"       % Versions.refined
  final val requests               = "com.lihaoyi"                 %% "requests"                 % Versions.requests
  final val scalatest              = "org.scalatest"               %% "scalatest"                % Versions.scalatest
  final val scalapbRuntimeGrpc     = "com.thesamet.scalapb"        %% "scalapb-runtime-grpc"     % Versions.scalapbRuntime
  final val slf4zio                = "com.github.mlangc"           %% "slf4zio"                  % Versions.slf4zio
  final val tapirJsonCirce         = "com.softwaremill.sttp.tapir" %% "tapir-json-circe"         % Versions.tapir
  final val tapirOpenapiCirceYaml  = "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % Versions.tapir
  final val tapirOpenapiDocs       = "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs"       % Versions.tapir
  final val tapirRefined           = "com.softwaremill.sttp.tapir" %% "tapir-refined"            % Versions.tapir
  final val tapirSttpClient        = "com.softwaremill.sttp.tapir" %% "tapir-sttp-client"        % Versions.tapir
  final val tapirSwaggerUiHttp4s   = "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-http4s"  % Versions.tapir
  final val tapirZio               = "com.softwaremill.sttp.tapir" %% "tapir-zio"                % Versions.tapir
  final val tapirZioHttp4sServer   = "com.softwaremill.sttp.tapir" %% "tapir-zio-http4s-server"  % Versions.tapir
  final val zioStreams             = "dev.zio"                     %% "zio-streams"              % Versions.zio

}
