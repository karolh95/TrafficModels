# **chowdhury**

A Spring Boot application for multi-lane traffic models

## Table of contents

* [Installation information](#installation-information)

## Installation Information

Chowdhury is a Spring Boot application built using Maven. You can build a jar file and run it from the command line:

```console
git clone https://github.com/karolh95/chowdhury.git
cd chowdhury
./mvnw package
java -jar target/*.jar
```

The base address for each calls is: **localhost:8080**.

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```console
 ./mvnw spring-boot:run
 ```
