# gatling-starter
Starter for performance test runing on gatling with sbt plugin

## Features
* Running performance tests from sbt
* Building Fat JAR to run performance test from separate environment without need run it from sbt 
* Providing external configuration of performance tests from ENV variables e.g. -DUSERS=10

## Installation 

Install from [idea plugins marketplace](https://plugins.jetbrains.com/plugin/11942-cheat-sh-code-snippets) 

## Usage

### Run from SBT

1. ```gatling-it:test``` - to run all tests
2. ```gatling-it:testOnly pl.szymonprz.ScenarioExample``` - to run only one scenario

### Use FAT jar

1. ```it:assembly``` - to build fat jar
2. ```java -jar -DUSERS=10 gatling-starter-0.1.jar -s pl.szymonprz.ScenarioExample``` - to run scenario with changed configuration of users count 


