# Library Management System

A REST API built with Spring Boot for managing authors, books, library
members, and book borrowing/returns. The project uses Spring Data JPA
and an in-memory H2 database, so no external database installation is
required.

## Features

-   Create, retrieve, and update authors
-   Create, retrieve, update, and delete books
-   Register, retrieve, and update library members
-   Borrow and return books
-   View a member's borrowing history
-   OpenAPI specification and Swagger UI documentation

## Technology Stack

-   Java
-   Spring Boot
-   Spring Web
-   Spring Data JPA / Hibernate
-   H2 in-memory database
-   Maven
-   springdoc-openapi / Swagger UI

Use the Java and Spring Boot versions declared in `pom.xml`. This README
assumes the application is configured to use H2.

## Prerequisites

1.  A JDK compatible with the Java version in `pom.xml`
2.  Maven, or the Maven Wrapper if the project contains `mvnw` /
    `mvnw.cmd`
3.  Git, if cloning the repository

Verify your installations:

``` bash
java -version
mvn -version
```

## Setup and Run

### 1. Get the project

If the project is in a Git repository:

``` bash
git clone <https://github.com/kavyamahesh123/library-management>
cd <project-directory>
```

Alternatively, in Spring Tool Suite (STS), choose **File → Import →
Existing Maven Projects** and select the project directory containing
`pom.xml`.

### 2. Check configuration

Review `src/main/resources/application.properties`. For an in-memory H2
database, configuration typically resembles:

``` properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Keep the configuration that matches your project if it already exists.
These example properties assume the H2 and JPA dependencies are present
in `pom.xml`.

If your service uses configurable borrowing rules, ensure the required
properties are configured. For example:

``` properties
library.loan-period-days=14
library.max-active-borrows-per-member=5
```

Use the values required by your implementation.

### 3. Build and test

From the directory containing `pom.xml`:

``` bash
mvn clean test
mvn clean package
```

If the Maven Wrapper is available, use `./mvnw clean test` and
`./mvnw clean package` on macOS/Linux, or `mvnw.cmd clean test` and
`mvnw.cmd clean package` on Windows.

### 4. Start the application

Run with Maven:

``` bash
mvn spring-boot:run
```

Or run the generated JAR:

``` bash
java -jar target/<generated-jar-name>.jar
```

In STS, right-click the main Spring Boot application class and select
**Run As → Spring Boot App**.

Unless configured otherwise, the base URL is `http://localhost:8080`.
Wait for the application to finish starting before sending requests.

## API Endpoints

All endpoints use the `/api` base path.

  -----------------------------------------------------------------------------
  Method                  Endpoint                      Purpose
  ----------------------- ----------------------------- -----------------------
  `POST`                  `/api/authors`                Create an author

  `GET`                   `/api/authors`                List authors

  `GET`                   `/api/authors/{id}`           Get an author

  `PUT`                   `/api/authors/{id}`           Update an author

  `POST`                  `/api/books`                  Create a book

  `GET`                   `/api/books`                  List books

  `GET`                   `/api/books/{id}`             Get a book

  `PUT`                   `/api/books/{id}`             Update a book

  `DELETE`                `/api/books/{id}`             Delete a book

  `POST`                  `/api/members`                Register a member

  `GET`                   `/api/members`                List members

  `GET`                   `/api/members/{id}`           Get a member

  `PUT`                   `/api/members/{id}`           Update a member

  `POST`                  `/api/borrows`                Borrow a book

  `PUT`                   `/api/borrows/{id}/return`    Return a borrow record;
                                                        the ID is the borrow
                                                        record ID

  `GET`                   `/api/members/{id}/borrows`   Get a member's
                                                        borrowing history
  -----------------------------------------------------------------------------

## Sample cURL Requests

The examples use `http://localhost:8080`. Run them in order where later
requests depend on earlier IDs. The examples assume newly created
resources receive ID `1`; use the IDs returned by your application if
they differ.

### 1. Create an author

``` bash
curl -i -X POST http://localhost:8080/api/authors \
  -H "Content-Type: application/json" \
  -d '{"name":"Robert Martin","email":"robert@example.com"}'
```

### 2. List authors

``` bash
curl -i http://localhost:8080/api/authors
```

### 3. Create a book

The `authorId` must refer to an existing author. If `available` is
omitted or set to `null`, the service is intended to default it to
`true`.

``` bash
curl -i -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Java","isbn":"ISBN-001","available":true,"authorId":1}'
```

### 4. List books

``` bash
curl -i http://localhost:8080/api/books
```

### 5. Register a member

``` bash
curl -i -X POST http://localhost:8080/api/members \
  -H "Content-Type: application/json" \
  -d '{"name":"Kavya","email":"kavya@example.com","phone":"9876543210"}'
```

### 6. List members

``` bash
curl -i http://localhost:8080/api/members
```

### 7. Borrow a book

Both IDs must refer to existing resources, and the book must be
available.

``` bash
curl -i -X POST http://localhost:8080/api/borrows \
  -H "Content-Type: application/json" \
  -d '{"bookId":1,"memberId":1}'
```

### 8. View a member's borrowing history

``` bash
curl -i http://localhost:8080/api/members/1/borrows
```

### 9. Return a borrowed book

Use the **borrow record ID** returned by the borrow request, not the
book ID.

``` bash
curl -i -X PUT http://localhost:8080/api/borrows/1/return
```

### 10. Update a book

``` bash
curl -i -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Java 8","available":true}'
```

### 11. Delete a book

``` bash
curl -i -X DELETE http://localhost:8080/api/books/1
```

Deleting a book may fail if its relationships or business rules prevent
deletion. Use the response from the running application as the source of
truth.

## Swagger UI and OpenAPI

If `springdoc-openapi-starter-webmvc-ui` is included in `pom.xml` and
Swagger UI is enabled, open:

-   Swagger UI: <http://localhost:8080/swagger-ui/index.html>
-   Generated OpenAPI JSON: <http://localhost:8080/v3/api-docs>

If Swagger UI is configured to load your custom YAML, place a copy of
`openapi.yaml` in `src/main/resources/static/` and add:

``` properties
springdoc.swagger-ui.url=/openapi.yaml
springdoc.swagger-ui.disable-swagger-default-url=true
```

Then the YAML should be accessible at
<http://localhost:8080/openapi.yaml>. The custom YAML documents the API;
it is not automatically synchronized with controller code.

## H2 Console

If enabled in `application.properties`, open
<http://localhost:8080/h2-console>.

Use the JDBC URL and credentials configured by the application. For the
example configuration above:

-   JDBC URL: `jdbc:h2:mem:librarydb`
-   User Name: `sa`
-   Password: leave blank

An in-memory database is reset when the application stops, unless
configured otherwise.

## Tests

Run the test suite:

``` bash
mvn test
```

To run verification steps:

``` bash
mvn clean verify
```

## Troubleshooting

-   **Port 8080 is already in use:** stop the other process or change
    `server.port` in `application.properties`.
-   **Book creation fails with author not found:** create the author
    first and use the returned author ID.
-   **Borrowing fails:** verify that the member and book exist, the book
    is available, and the member has not reached the active-borrow
    limit.
-   **Swagger UI is unavailable:** confirm the springdoc dependency is
    present, the application started successfully, and the configured
    URL is correct.
-   **H2 console does not open:** confirm H2 is on the classpath and
    `spring.h2.console.enabled=true`.

## Notes

-   API response envelopes and error responses follow the format
    implemented by the application's controller and global exception
    handler.
-   Confirm exact validation rules, HTTP status codes, and configuration
    property names against the current source code before submission.
