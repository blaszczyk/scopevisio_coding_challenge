# Prämien Application

The Prämien Application allowes users to send premium requests to a ficticious car insurance agency.

## Architecture

The application consists of three HTTP services and a Web UI. The communication betweens services and the UI is handled in HTTP and JSON. Databse queries use SQL with JDBC and R2DBC drivers. 

![architecture_praemien.png](architecture_praemien.drawio.png)

## Post Code Service
* loads location data into database
* serves locations for queried postcode

## Praemien Service
The PraemienService handles PraemienAnfrage rquests
* accepts requests
* validates requests using PostCode Service
* computes Praemie
* persists summary
* serves summary

## Web UI
* web form for convenient user input and summary page

## Web Service + API Gateway
* serves static Web UI distribution
* routes API requests to encapsulated backend services

## Workflow
* User enters Fahrzeugtyp, Kilometerleistung and Postleitzahl
* UI requests locations for provided PLZ from PostCodeService
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
- Postgres
- **Spring R2DBC**, reactive database queries
- **Liquibase**, standatd for database initialization

### Testing
* **JUnit**, standard Java testing framework
* **RestAssure** for web service tests
* **TestContainers** provides postgres database with docker in tests
* **WireMock** to mock services the application depends on

### Documentation
* AsciiDoc
* **Spring RestDocs** uses tests to generate API documentation and to ensure validity

### Deployment
* **Docker** standard for container deployment
* **Temurin & Alpine** slim base image

### Frontend
* Angular/TypeScript

## Quick Start

### Prequisites
* Windows
* JDK (v21 or higher) in `JAVA_HOME`
* `docker` executable in `PATH`
* `npm` executable in `PATH`

### Build and Run
* run `build-all.bat` to build all components and create docker images for services
* run `docker compose up -d` to start service stack. On initial startup the PostCodeService requires more time to initialize the database
* open http://localhost in web browser

### Test and Documentation

## TODO
