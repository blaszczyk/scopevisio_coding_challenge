# Prämien Application

The Prämien Application allowes users to send premium requests to a ficticious car insurance agency.

## Architecture

The application consists of three microservices and a web UI. The services and the UI use HTTP and JSON for communication. Data is persisted in a Postgres SQL database.

![architecture_praemien.png](architecture_praemien.drawio.png)

### Post Code Service
* loads location data into database
* serves locations for queried postcode

### Praemien Service
* handles PraemienAnfrage requests
* validates requests using PostCode Service
* computes premium
* persists summary

### Web UI
* web form for convenient user input
* summary page

### Web Service + API Gateway
* serves static Web UI distribution
* routes API requests to encapsulated backend services

#### Other Possible Responsibilities
* Authentication
* Session management
* Security issues

## Workflow
* User enters Fahrzeugtyp, Kilometerleistung and Postleitzahl
* UI requests locations for provided Postleitzahl from PostCodeService
* User selects location and clicks Anfrage-button
* UI sends request to PraemienService
* PraemienService requests locations from PostCodeService to validate user request
* PramienService calculates Praemie, persists summary and responds
* UI redirects to summary page

## Technology Stack

### Web Service
- **Spring Boot**, the standard for Java web services
- **Spring WebFlux**, the reactive framework allowes an asynchroneous and resource efficient usage of service executor threads

### Persistence
- **Postgres**, open source, widely compatible, industry standard database
- **Spring R2DBC**, reactive queries in accordance with WebFlux
- **Liquibase**, standatd for database initialization

### Testing
* **JUnit** Java testing framework
* **RestAssure** simplifies web service tests
* **TestContainers** is used to provide a postgres database in a container for tests
* **WireMock** to mock services the application depends on

### Documentation
* **AsciiDoc** to generate service and API documentation
* **Spring RestDocs** uses tests to generate API documentation and to ensure validity

### Deployment
* **Docker** standard for container deployment
* **Temurin & Alpine** slim base image

### Frontend
* **Angular/TypeScript** web developement framework

## Quick Start

### Prequisites
* JDK distribution (21 or higher) in `JAVA_HOME` environment variable
* `docker` executable in `PATH` environment variable (tested with v27.4.0)
* `npm` executable in `PATH` environment variable (tested with v10.9.2, NodeJs v23.7.0) 

### Build and Run
* execute `build-all.bat` to build all components and create docker images for services
* execute `docker compose up -d` to start service stack. On initial startup the PostCodeService requires more time to initialize the database
* open http://localhost/ in web browser

### Test and Documentation
* execute `test-docs-all.bat` to test the services and assemble documentation
* if successful, the generated documentation can be found in the `docs` folder

## TODO
* code formatting / lint
* CI/CD
* security (OWASP 10)
* logging
* UI tests
