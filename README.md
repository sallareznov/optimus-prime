![CI](https://github.com/sallareznov/optimus-prime/actions/workflows/scala.yml/badge.svg)

[Workflows](https://github.com/sallareznov/optimus-prime/actions)

# optimus-prime

## Diagrams

### System diagram

![System diagram](http://www.plantuml.com/plantuml/png/5Ssx3G8n303GdYbWW8jxgbl59DQTfTYE_071z43rohT7ecV9lDnO-wn1vVDwPgwu0pY-si5vrgE2l9icQRPk0e5pejD7xGcsagLYMyv28CWAmph4Ev9a6uo7exJrJtRIyVCB)

### Container diagram

![Container diagram](http://www.plantuml.com/plantuml/png/5OsxhSCm303xDyNB01RxL5w5aGYRW4WA_2H0fezTtHK7t1w9dYNpxy7QPmqgd_zdveedWEEd7PndlIh8kscIPkic43WdEdtLtc0hMIgsvIm4WgmmhCETNqw-3SR3KTgwFplfkFW3)

### Component diagram

![Component diagram](http://www.plantuml.com/plantuml/png/5Sqz3i8m343XdLF00HhlJBsAIQpMKcn7_W69qrEcjppfsoFHisJU7gnzrg3oUJypLpm173_ju3phLK7UJPCqMpU1m3dHwQls1bl9Kh4jPo4GP8LX7QvjcP9cR8FnS9Is_j1EEgx-0000)

### Sequence diagram

![Prime numbers](http://www.plantuml.com/plantuml/png/5Ssx3G8n303GdYbWW8jxgbl59DQTfTWE_e34z43rohT7ecV9lDnO-wn1vVDwPgwu0pY-sy5vrgE2l9icQRPk0e5pejD7xGMsagLYMyv28CWAmpe4FOkqqpOO3qTfwz_ifEFd5m00)

## Implementation choices and preferences

- gRPC library: [zio-grpc](https://scalapb.github.io/zio-grpc/) helped me write purely functional gRPC services
with typelevel error and effect handling with ZIO.
- type-safety: [refined](https://github.com/fthomas/refined) helped me handle at type-system level invalid inputs and numbers (i.e <= 1)
- http service:
  - [tapir](https://tapir.softwaremill.com/en/latest/) helped me separate APIs descriptions from their implementations,
    and library-free Swagger documentation. Documentation is key in software engineering and is often underrated, so 
  having a library that enables to generate always up-to-date documentation is really a great tool to have.
  - [http4s](https://http4s.org/) helped me implement a functional HTTP REST API
- streaming: [zio-streams](https://zio.dev/docs/datatypes/stream/zstream) and [fs2](https://fs2.io/) helped me leverage ZIO-powered purely
functional streaming. I had to use both libraries and not commit to just one because `tapir`'s integration with `zstream`
is not mature enough.
- configuration: [pureconfig](https://pureconfig.github.io/) helped me load the [app configuration](https://github.com/sallareznov/optimus-prime/blob/main/proxy-service/src/main/resources/application.conf) in a fail-fast way.
- logging: [slf4zio](https://github.com/mlangc/slf4zio) helped me log HTTP endpoints and inputs.
- unit testing: [scalatest](https://www.scalatest.org/), the de facto library for unit-testing in Scala, helped me write
simple tests with a good level of readability.
- behaviour testing: [cucumber-scala](https://cucumber.io/docs/installation/scala/) helped me use the great Gherkin
implementation named Cucumber to write executable, behaviour-driven tests. The Gherkin [feature file](https://github.com/sallareznov/optimus-prime/blob/main/behaviour/src/test/resources/prime-number.feature) also serves as
documentation, and also represents a contract between developers and domain experts. Again, it follows the same
philosophy as `tapir` and `ZIO`: separate description from
execution

You surely noticed that I use a lot of ZIO-powered libraries.
In my Scala projects I favor `ZIO` over `cats-effect` for example because errors
are known at compile-time, and the environment parameter from `ZIO` (i.e also its layer-system) really helps structure
code and dependencies better. `ZIO`'s type-system is more powerful and offers more possibilities for testing.

## Other implementation choices that were considered irrelevant for this use case
- Domain-Driven-Design and Hexagonal Architecture are approaches that I often use when dealing with complex software,
but I considered this use case not complex enough to follow it

## The project itself

### How to compile it
 
At the root of the project, execute the command: `sbt test:compile`. It will compile
all four modules (`contracts`, `prime-number-server`, `proxy-service` and `behaviour`).

### How to run it

At the root of the project:
- run the `prime-number-server` with: `sbt prime-number-server/run`
- run the `proxy-service` with: `sbt proxy-service/run`

The HTTP server acting as a proxy runs on port `8080`, so one can execute a
`GET` request at `http://localhost:8080/prime/17`.

You can also use the Swagger UI to perform some requests: `http://localhost:8080/docs`.

### How to run tests

- unit tests can be run with the command `sbt test`
- behaviour tests can be run with the command `sbt behaviour/cucumber`

## How to improve it

- improve logging and add tracing [mandatory before going into production, as it eases debugging and monitoring] (maybe with [zio-telemetry](https://zio.github.io/zio-telemetry/))
- implement a test that illustrates the wanted behaviour when the `prime-number-server` is unavailable
