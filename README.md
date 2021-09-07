# optimus-prime

## Context Diagram

```plantuml
@startuml
!include  https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/v1.0.0/C4.puml
!include  https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/v1.0.0/C4_Context.puml

title Context Diagram

Person(User, "User")

Boundary(Optimus, "Optimus") {
    System(Prime, "Prime")
}

Rel(User, Prime, "Asks for a sequence of prime numbers up to the given number")

@enduml
```

## Container Diagram

```plantuml
@startuml
!include  https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/v1.0.0/C4.puml
!include  https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/v1.0.0/C4_Context.puml
!include  https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/v1.0.0/C4_Container.puml

title Container Diagram

Person(User, "User")

Boundary(Optimus, "Optimus") {
    System(ProxyService, "proxy-service")
    System(PrimeNumberServer, "proxy-number-server")
}

Rel(User, ProxyService, "Asks for a sequence of prime numbers up to the given number", "http")
Rel(ProxyService, PrimeNumberServer, "Asks for a sequence of prime numbers up to the given number", "grpc")

@enduml
```

## Component Diagram

```plantuml
title Sequence Diagram: Prime numbers

user->proxyService: asks for a sequence of prime numbers up to a given number
alt #pink the number is not positive 
proxyService-->user: return an error
end
proxyService->primeNumberServer: asks for a sequence of prime numbers up to the given number
primeNumberServer->primeNumberServer: calculates prime numbers
primeNumberServer-->proxyService: return prime numbers up to the given number
proxyService-->user: return the sequence of prime numbers up to the given number
```
