# find-file-duplicates
Hi there :wave:!

This is my implementation for the challenge set by Isar Aerospace... [TODO: finish intro text]

## Solution at a glance
[TODO: include a video]

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
