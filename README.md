# Axon demo

## Prerequisites

* Java 8+ JDK
* Maven 3

## Usage

### Running the API

From the command line, run `mvn spring-boot:run`.

In an IDE, run the `main` method as a Java application.

Access the API via HTTP at <http://localhost:8080/>.

### Using the API

Create an application:

```
$ curl -X POST -H 'Authorization: sally' 'http://localhost:8080/applications'
{"id":"04d7b70d-b0c9-4940-b4b3-7ff02ae889c0"}
```

Select a course:

```
$ curl -X POST -H 'Authorization: sally' -H 'Content-Type: application/json' \
-d '{"course":"Arts"}' \
'http://localhost:8080/applications/04d7b70d-b0c9-4940-b4b3-7ff02ae889c0/course'
```

Submit application:

```
$ curl -X POST -H 'Authorization: sally' -H 'Content-Type: application/json' \
'http://localhost:8080/applications/04d7b70d-b0c9-4940-b4b3-7ff02ae889c0/submit'
```

Complete bash script:

```
APPLICATION_ID=$(curl -s -X POST -H 'Authorization: sally' 'http://localhost:8080/applications' | jq -r .id)
curl -X POST -H 'Authorization: sally' -H 'Content-Type: application/json' -d '{"course":"Arts"}' "http://localhost:8080/applications/${APPLICATION_ID}/course"
curl -X POST -H 'Authorization: sally' "http://localhost:8080/applications/${APPLICATION_ID}/submit"
```

### Querying in-memory database

```
mvn exec:java@h2
```

Example queries:

```
SELECT PAYLOAD_TYPE, UTF8TOSTRING(PAYLOAD), TYPE, AGGREGATE_IDENTIFIER, SEQUENCE_NUMBER FROM DOMAIN_EVENT_ENTRY ORDER BY TIME_STAMP;
SELECT * FROM APPLICATION;
SELECT * FROM APPLICATION2;
```

## Development notes

### Spring project

Created initial project using [Spring Initialzr](https://start.spring.io/):

* Project: Maven Project
* Language: Kotlin
* Spring Boot: 2.1.4
* Java Version: 8
* Dependencies: DevTools, Web, JPA, H2
