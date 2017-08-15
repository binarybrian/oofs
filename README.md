# Object-Oriented File System (OOFS)

[![Build Status](https://travis-ci.org/binarybrian/oofs.svg?branch=master)](https://travis-ci.org/binarybrian/oofs)

* This is a basic "proof-of-concept" design and implementation of an Object-Oriented File System.

## Dependencies
* [google/guava](https://github.com/google/guava)
* [JUnit4](https://github.com/junit-team/junit4)

## Building
Recommended:  
Import as a new project in Intellij or Eclipse.

Command Line:  
`mkdir -p bin && javac -d bin -sourcepath src -cp lib/guava-23.0.jar src/oofs/Demo.java`

Maven:  
`mvn clean compile`

## Running
There is one main executable class called 'Demo'.  
This demonstrates the creation of a basic file system and executes the **Move**, **Size** and **Path** operations.

Recommended:  
Launch 'Demo' from an IDE such as Intellij or Eclipse

Command Line:  
`java -cp bin/:lib/guava-23.0.jar oofs.Demo`

Maven:
`java -cp target/classes/:lib/guava-23.0.jar oofs.Demo`

## Testing
* The unit tests can be run indivdually or as a suite.  
* The test classes are located in the 'test' folder.
* The test suite is call 'AllTests'.
