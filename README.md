# find-file-duplicates
Hi there :wave:!
This is my implementation for the challenge set by Isar Aerospace... [TODO: finish intro text]

## Solution at a glance
[TODO: include a video]

## Technical documentation
### Prerequisites
- Java: 11.0.16

### Run commands
```
TODO: include run commands
```

### Comments
- Developed adhering to: https://google.github.io/styleguide/javaguide.html

## Challenge
Complete these tasks:
- Develop a library in C# that implements the interface depicted above
- Create a console application (your choice won’t impact the evaluation) that references your library and enables the user to:
  - Specify a folder path
  - Run the algorithm
  - Display the results in some way
  - Sort the results by name and/or size
  
GetCandidates() should walk through all files in the directory tree and roughly compare them to each other. Default comparison mode is by file name and size. Files that appear to be identical should be returned by the method.

The potential candidates should then be checked by CheckCandidates() for their effective equality by comparing their MD5 hashes. The method should return all candidates that had matching hashes as actual duplicates.