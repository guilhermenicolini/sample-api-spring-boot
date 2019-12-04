# Sample API
> A simple API to store user and beers information

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=guilhermenicolini_sample-api-spring-boot&metric=alert_status)](https://sonarcloud.io/dashboard?id=guilhermenicolini_sample-api-spring-boot)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=guilhermenicolini_sample-api-spring-boot&metric=coverage)](https://sonarcloud.io/dashboard?id=guilhermenicolini_sample-api-spring-boot)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=guilhermenicolini_sample-api-spring-boot&metric=security_rating)](https://sonarcloud.io/dashboard?id=guilhermenicolini_sample-api-spring-boot)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=guilhermenicolini_sample-api-spring-boot&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=guilhermenicolini_sample-api-spring-boot)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/dashboard?id=guilhermenicolini_sample-api-spring-boot)

## Demo

https://sample-api-spring-boot.herokuapp.com/swagger-ui.html

## Installation

Clone this repository in your operating system using Git

```sh
git clone git@github.com:guilhermenicolini/sample-api-spring-boot.git
cd sample-api-spring-boot
```

Run
```sh
./gradlew bootRun
```

## Swagger documentation

http://localhost:9000/swagger-ui.html

## Running the tests

Change application.properties jwt.secret to any value.

Change application.properties datasource to H2 and token jwt:
```sh
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driverClassName=org.h2.Driver

jwt.secret=mytokensecret
```

In Objects.java file, change the value of wrongToken(), noExpirationToken(), expiredToken() and invalidToken() for your token.

OSX & Linux
```sh
./gradlew test jacocoTestReport
```

Windows
```sh
gradlew test jacocoTestReport
```

The reports will be in build/reports/tests/test/index.html and build/reports/coverage/index.html

## Authors

* **Guilherme Nicolini** - *Fullstack Developer* - [GuilhermeNicolini](https://github.com/guilhermenicolini)

## License

This project is licensed under the MIT License - see the [LICENCE.md](LICENCE.md) file for details

## Acknowledgments

* Spring Boot REST API
* Unit tests
* OpenAPI (Swagger)
* HATEOAS
* Jacoco
* Sonar
* Heroku deploy