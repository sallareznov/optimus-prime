addSbtPlugin("com.github.cb372" % "sbt-explicit-dependencies" % "0.2.16")
addSbtPlugin("org.typelevel"    % "sbt-fs2-grpc"              % "2.1.5")
addSbtPlugin("com.thesamet"     % "sbt-protoc"                % "1.0.2")
addSbtPlugin("io.spray"         % "sbt-revolver"              % "0.9.1")
addSbtPlugin("org.scalameta"    % "sbt-scalafmt"              % "2.4.3")

libraryDependencies += "com.thesamet.scalapb.zio-grpc" %% "zio-grpc-codegen" % "0.5.0"
