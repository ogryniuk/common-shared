# Yildiz-Engine common-shared

This is the official repository of The Common library, part of the Yildiz-Engine project.
The common library is a set of utility and helper classes to handle easily logging, collections, time,...

## Features

* Logging.
* Collections.
* Vector, quaternions, Point3D, Point2D, coordinates,...
* Time computation.
* ...

## Requirements

To build this module, you will need a java 8 JDK and Maven 3.

## Coding Style and other information

Project website:
http://www.yildiz-games.be

Issue tracker:
https://yildiz.atlassian.net

Wiki:
https://yildiz.atlassian.net/wiki

Quality report:
https://sonarqube.com/overview?id=be.yildiz-games:common-shared

## License

All source code files are licensed under the permissive MIT license
(http://opensource.org/licenses/MIT) unless marked differently in a particular folder/file.

## Build instructions

Go to your root directory, where you POM file is located.

Then invoke maven

	mvn clean install

This will compile the source code, then run the unit tests, and finally build a jar file.

## Usage

In your maven project, add the dependency

```xml
<dependency>
    <groupId>be.yildiz-games</groupId>
    <artifactId>common-shared</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Contact
Owner of this repository: Grégory Van den Borre