package com.duplicates;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Console application that enables the user to find duplicate files in folder structures.
 *
 * <p>Enables the user to specify folder paths, check for duplicates and sort the results by file names and/or sizes.
 */
public class Main {
    public static void main(String[] args) {
        // Retrieve desired path.
        System.out.println("Welcome to Oliver's duplicate file finder! All inputs need to be confirmed through pressing ENTER.\n");
        Scanner scanner = new Scanner(System.in);
        String folderPath;
        File folder;
        boolean folderPathValid = false;
        System.out.println("SETTINGS:");
        do{
            System.out.print("Please enter a valid folder path: ");
            folderPath = scanner.nextLine();
            folder = new File(folderPath);
            if(!(folder.exists() && folder.isDirectory())){
                System.out.println("Not a valid folder path!");
            } else{
                folderPathValid = true;
            }
        } while (!folderPathValid);

        // Retrieve desired comparison mode.
        CompareMode mode = null;
        do {
            System.out.print("Please indicate the desired file comparison mode number [1 = size | 2 = name | 3 = size and name]: ");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    mode = CompareMode.Size;
                    break;
                case "2":
                    mode = CompareMode.Name;
                    break;
                case "3":
                    mode = CompareMode.SizeAndName;
                    break;
                default:
                    System.out.println("Please only enter the desired mode number [1|2|3]!");
            }
        } while(mode == null);
        System.out.printf("\nSearching for duplicate files in %s while comparing with %s...\n%n", folderPath, mode);

        // Enable sorting of results by name and/or size.
        System.out.println("Do you wish to sort the results by name = 1, size = 2 or randomly = 3?");

        // Identify duplicates with given path and mode.
        DuplicateFinder finder = new DuplicateFinder();
        Iterable<IDuplicate> candidates = finder.GetCandidates(folderPath, mode);
        Iterable<IDuplicate> duplicates = finder.CheckCandidates(candidates);

        // Display results of duplicate file finding.
        System.out.println("RESULTS:");

        duplicates.forEach(duplicate -> {
            System.out.printf("Duplicate file %s found in paths: ", (Paths.get(((Duplicate) duplicate).get(0))).getFileName());
            System.out.println(duplicate.FilePaths());
        });
    }
}
