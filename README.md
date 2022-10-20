# find-file-duplicates
Hi there :wave:

This is my implementation for the challenge set by Isar Aerospace to develop an application that finds duplicate files in directory trees. My solution is written in Java and utilizes Apache Maven to package and test the application library. Please follow the stepps indicated in the technical documentation to try it for yourself!

## Solution at a glance
[Screencast from 20.10.2022 20:16:20.webm](https://user-images.githubusercontent.com/39443615/197027134-6d00ff65-e84d-49e7-a938-a8f26a48ef0c.webm)

## Technical documentation
### Prerequisites
- Java: ^11.0.16
- Apache Maven: ^3.6.3

### Run pre-built library
```
cd find-file-duplicates
java -jar target/find-duplicate-files-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### Build new version of library 
```
cd find-file-duplicates
mvn clean compile assembly:single
```

### Test implementation with unit tests
```
cd find-file-duplicates
mvn clean test
```

### Project structure
```
.
├── pom.xml                                                         # Maven configuration
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── duplicates
│   │   │           ├── CompareMode.java                            # Enum for comparison mode
│   │   │           ├── DuplicateFinder.java                        # Identifies duplicates in file tree
│   │   │           ├── Duplicate.java                              # Stores paths of duplicates
│   │   │           ├── IDuplicateFinder.java
│   │   │           ├── IDuplicate.java
│   │   │           └── Main.java                                   # CLI application
│   │   └── resources
│   └── test
│       ├── java                                                    # Unit tests for interface implementations
│       │   ├── DuplicateFinderTest.java                            
│       │   └── DuplicateTest.java
│       └── resources
└── target

```

### Comments
- Developed adhering to: https://google.github.io/styleguide/javaguide.html
