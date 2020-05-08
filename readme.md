# appliance-inventory api  
[](https://app.fossa.io/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FSpring-Boot-Application-Template?ref=badge_shield)

This project is expose rest endpoints to consumer.


## Built With

* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Junit]([https://junit.org/junit5/](https://junit.org/junit5/)) -  The programmer-friendly testing framework for Java
* 	[H2 Database](https://www.h2database.com/html/main.html](https://www.h2database.com/html/main.html)) - Open-Source Relational Database Management System
* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system 
* 	[Sonar]([https://www.sonarqube.org/](https://www.sonarqube.org/)) - # Code Quality and Security
* 	[Jococo]([https://www.jacoco.org/](https://www.jacoco.org/)) - EclEmma is a free Java code coverage tool.
* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.
* 

## External Tools Used

* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)

## To-Do

- [ ] Logger (Console, File, Mail)
- [x] RESTful Web Service (CRUD)
- [x] Bootstrap - CSS
- [x] Web - HTML, JavaScript (jQuery)
- [x] Content Negotiation
- [x] Security (Basic Authentication)
- [ ] Material Design for Bootstrap
- [ ] Docker
- [ ] HATEOS
- [ ] Spring Boot Admin
- [ ] NoSQL (MongoDB)
- [ ] MySQL (Connect to Multiple Schemas)
- [ ] Micrometer
- [ ] Grafna


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.cg.AppliancesInventoryApplication` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run

```
### Git Repo
Git location for api : [https://github.com/rampuriaankur/appliances-inventory](https://github.com/rampuriaankur/appliances-inventory)
Git location for UI : https://github.com/rampuriaankur/myhome-ui

### Swagger contract
To view api contract see below file in project repo.
/appliances-inventory/appliance-inventory-apidoc.json

### Actuator
To monitor and manage your application

|  URL |  Method |
|----------|--------------|
|`http://localhost:8080`  						| GET |
|`http://localhost:8080/actuator/`             | GET |
|`http://localhost:8080/actuator/health`    	| GET |
|`http://localhost:8080/actuator/info`      	| GET |
|`http://localhost:8080/actuator/prometheus`| GET |
|`http://localhost:8080/actuator/httptrace` | GET |

### code coverage url
we can see code coverage report is available below project location 
 /appliances-inventory/target/jacoco-report/index.html



## Deployment 
Two cloud portal used to host api and ui component.
* [Microsoft Azure](https://azure.microsoft.com/) - To deploy appliances-inventory api.
* [Heroku](https://www.heroku.com/) - To host home-appliance ui.


## Documentation

* [Postman Collection](https://documenter.getpostman.com/view/2449187/RWTiwzb2) - online, with code auto-generated snippets in cURL, jQuery, Ruby,Python Requests, Node, PHP and Go programming languages
* [Postman Collection](https://github.com/AnanthaRajuC/Spring-Boot-Application-Template/blob/master/Spring%20Boot%20Template.postman_collection.json) - offline
* [Swagger](http://localhost:8088/swagger-ui.html) - Documentation & Testing



## packages

- `models` — to hold our entities;
- `repositories` — to communicate with the database;
- `services` — to hold our business logic;
- `controllers` — to listen to the client;

- `resources/application.properties` - It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file.

- `test/` - contains unit and integration tests

- `pom.xml` - contains all the project dependencies
 
## Resources

* [My API Lifecycle Checklist and Scorecard](https://dzone.com/articles/my-api-lifecycle-checklist-and-scorecard)
* [HTTP Status Codes](https://www.restapitutorial.com/httpstatuscodes.html)
* [Common application properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)

