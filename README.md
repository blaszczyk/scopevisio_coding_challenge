# Prämien Application

The Prämien Application allowes users to send premium requests to a ficticious german car insurance agency.

## Concerning the Challenge

* With the domain language being german, whereas programming standards are in english, a mixture of languages is unfortunately unavoidable. I chose to keep the documentation in english apart and hope it does not cause too much confusion.

* According to the exercise, the user should input a Postleitzahl which will be mapped to a Bundesland to compute the Prämie. However, this mapping is not unique as there are locations with same Postleitzahl located in a different Bundesland. To resolve this issue, the user must also select a location after entering a valid Postleitzahl. The full location data is then included in the request and persisted by the backend.

* For simplicity the business intelligence component, which computes the Praemie, is hard-coded and unit-tested. Values for the Fahrzeugtyp and the various factors are completely made-up for the sake of the exercise. In reality one would prefer a more dynamic solution, e.g. a separate service.

* Deployment

## Architecture

The application consists of three microservices and a web UI. The services and the UI use HTTP and JSON for communication. Data is persisted in a Postgres SQL database.

![architecture_praemien.drawio.png](architecture_praemien.drawio.png)

The responsibilities of the components are the following:

### PostCodeService
* loads location data into database
* serves locations for queried Postleitzahl

### PraemienService
* handles PraemienAnfrage requests
* validates requests using PostCodeService
* computes Praemie
* persists summary

### Web UI
* web form for convenient user input
* summary page

### WebService + API Gateway
* serves static Web UI distribution
* routes API requests to encapsulated backend services

#### Other Possible Responsibilities
* Authentication
* Session management
* Security issues

## Workflow
The workflow for a successful user request consists of the following steps:
* User enters Fahrzeugtyp, Kilometerleistung and Postleitzahl
* UI requests locations for provided Postleitzahl from PostCodeService
* User selects location and clicks Anfrage-button
* UI sends request to PraemienService
* PraemienService requests locations from PostCodeService to validate user request
* PraemienService calculates Praemie, persists summary and responds
* UI redirects to summary page

## Technology Stack

### Web Service
- **Spring Boot**, the standard for Java web services
- **Spring WebFlux**, the reactive framework allowes an asynchroneous and resource efficient usage of service executor threads
- **SpringCloudGateway** to route UI requests to backend services

### Persistence
- **Postgres**, open source, widely compatible, industry standard database
- **Spring R2DBC**, reactive database queries in accordance with WebFlux
- **Liquibase**, standatd for database initialization

### Testing
* **JUnit**, standard Java testing framework
* **RestAssure** simplifies web service tests
* **TestContainers** is used to provide a postgres database in a container for tests
* **WireMock** to mock services the application depends on

### Documentation
* **AsciiDoc** to generate service and API documentation
* **Spring RestDocs** uses tests to generate API documentation and ensure validity

### Deployment
* **Docker** standard for container deployment
* **Temurin & Alpine** a slim base image

### Frontend
* **Angular/TypeScript** web developement framework

## Quick Start

### Prequisites
* JDK distribution (21 or higher) in `JAVA_HOME` environment variable
* `docker` executable in `PATH` environment variable (tested with v27.4.0)
* `npm` executable in `PATH` environment variable (tested with v10.9.2, NodeJs v23.7.0) 

### Build and Run
* execute `build-all.bat` to build all components and create docker images for services
* execute `docker compose up -d` to start service stack. On initial startup the PostCodeService requires more time to initialize the database.
* open http://localhost/ in web browser

### Test and Documentation
* execute `test-docs-all.bat` to test the services and assemble documentation
* if successful, the generated documentation can be found in the `docs` folder

## TODO
An incomplete list of improvements to make the application product-ready
* versioning
* CI/CD
* security (OWASP 10)
* code formatting / lint
* logging
* UI tests
* UX
