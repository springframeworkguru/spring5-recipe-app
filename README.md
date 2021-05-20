# Spring Boot Recipe Application

[![CircleCI](https://circleci.com/gh/kostasmantz/spring5-recipe-app.svg?style=svg)](https://circleci.com/gh/kostasmantz/spring5-recipe-app) [![codecov](https://codecov.io/gh/kostasmantz/spring5-recipe-app/branch/master/graph/badge.svg?token=MRG3D4FSZG)](https://codecov.io/gh/kostasmantz/spring5-recipe-app)


This is an example Recipe application built with Spring Boot for the needs of a udemy course.
The app consists of a front-end implemented with Thymeleaf and the backend exposing a REST API implemented with Spring Boot.

##Requirements
The following are required to build and run the application.

- Java (8 and greater)

##Tech
Technologies used to develop the application:

- Java
- Spring Boot
- Spring Security
- MySQL
- Project Lombok
- Maven
- JUnit

##Installation
```
cd spring5-recipe-app
./mvnw clean install
./mvnw spring-boot:run
```