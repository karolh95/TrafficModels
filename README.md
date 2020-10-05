# **chowdhury**

A Spring Boot application for multi-lane traffic models

## Table of contents

* [Summary](#summary)
* [Technologies](#technologies)
* [Installation information](#installation-information)
* [Definition list](#definition-list)

## Summary

The aim of this project is to develop models of multi-lane traffic with different types of vehicles (with different values of maximum allowable speed), with the possibility of expanding the system with custom models.

Basic models ([ASEP](#asep) and [Nagel-Schreckenberg](#ns)) were implemented to demonstrate the use and expected behavior of the models.

## Technologies

- **Java** 11
- **Spring Boot** 2.3.3.RELEASE
- **WebSocket**
- **Lombok**

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

## Definition List

<dl>
 <dt><a id="asep">ASEP model</a></dt>
 <dd>
  One of the simplest dynamical models is the so-called "asymmetric simple exclusion process" (ASEP).  
  The update rule of this model is extremely simple: one vehicle is picked up at random and moved forward by one site if the new site is empty. In this random sequential update, it is the random picking that introduces stochasticity (noise) into the model and leads to different stationary states than that of the deterministic dynamics if the positions of all the particles were updated in parallel. By construction of the rule, the maximum possible speed of a vehicle in this model is 1.
 </dd>
 <dt><a id="ns">Nagel-Schreckenberg model</a></dt>
 <dd>
  The simplest single-lane stochastic cellular automata model, studied by Nagel-chreckenberg, is defined as follows:
  
The speed of each vehicle can take all the integer values from 0 to Vmax where Vmax is the maximum possible speed of each of the vehicles. An instantaneous state of the traffic system in a lane of length L is given by the set of
positions and speeds of all the vehicles in the system at that instant of time. We shall use the symbol V(n) and dX(n) to denote, respectively, the speed of the nth vehicle
and the gap in front of it.

Starting from a given initial condition, the state of the system is updated according to the following rules:
1. if V(n) < Vmax, then V(n) = V(n) + 1,
1. if V (n) > ( dX(n) - 1), then V(n) = dX(n) - 1,
1. if V(n) > 0 and ran f< Pa, then V(n) = V(n) - 1,
1. X(n) = X(n) + V(n),  
  where Pa is the probability of a random deceleration and ran f is a random fraction uniformly distributed between 0 and 1.  

 </dd>
 </dl>
