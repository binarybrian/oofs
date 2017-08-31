# Object-Oriented File System (OOFS)

[![Build Status](https://travis-ci.org/binarybrian/oofs.svg?branch=master)](https://travis-ci.org/binarybrian/oofs)

* This is a basic "proof-of-concept" design and implementation of an Object-Oriented File System.

## Dependencies
* [Guava](https://github.com/google/guava)
* [JUnit4](https://github.com/junit-team/junit4)

## Building
Recommended:  
Import as a new project in Intellij or Eclipse.

Command Line:  
`mkdir -p bin && javac -d bin -sourcepath src -cp lib/guava-23.0.jar src/oofs/Demo.java`

Maven:  
`mvn clean compile`

## Running
There is one main executable class called 'Demo'.  This creates a basic file system and executes the **Move**, **Size** and **Path** operations.
The operations shown in this demo are fairly trivial.  More robust tests are implemented as JUnit tests.  Please refer to and run the test suite located in `oofs.AllTests`.  

Recommended:  
Launch 'Demo' from an IDE such as Intellij or Eclipse

Command Line:  
`java -cp bin/:lib/guava-23.0.jar oofs.Demo`

Maven:  
`java -cp target/classes/:lib/guava-23.0.jar oofs.Demo`

## Testing
The unit tests can be run indivdually or as a suite and are located in 'test/oofs'.  The test suite is called 'AllTests'.

## Database Representation
[Nested Set Model for storing hierarchical data](http://mikehillyer.com/articles/managing-hierarchical-data-in-mysql/)