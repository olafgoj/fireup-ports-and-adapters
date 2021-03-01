# About
Very simple app in hexagonal architecture. There is one domain event and CRUD for it. Besides that, for every change in the system, event to Kafka is fired. Events are in AVRO format.

Keywords:
- Java
- Spring Boot
- Docker compose
- Kafka
- AVRO schema registry
- PostgreSQL

# Instructions:
There are two ways to start the app:
-  Use `run.sh` load script.
   
or
-  Run manually using docker-compose from `docker` directory and after successful containers startup, run app from main directory:
`./gradlew bootRun`


# TODO

1. Add tests (unit and integration with some in memory database and embedded kafka (e.g. testcontainers kafka))
2. Remove credentials from `application.yml` and use some secret manager (e.g. vault or helm secrets if deploying to k8s cluster) to expose credentials to app via environment variables.
3. Prepare some init script for DB and use some database versioning tool like flyway to keep schema changes under control.
4. For kafka-related-code, make kafka calls async, run them in separate threads and also introduce some event store, e.g. *Outbox pattern*, to ensure that you won't lose unsent events in case of any message broker failure.
5. Add API documentation (e.g. openAPI)
6. Container should use volumes to persist state