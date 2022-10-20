package com.duplicates;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestWordMax;

import java.io.File;
import java.util.ArrayList;
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
        do {
            System.out.print("Please enter a valid folder path: ");
            folderPath = scanner.nextLine();
            folder = new File(folderPath);
            if (!(folder.exists() && folder.isDirectory())) {
                System.out.println("Not a valid folder path!");
            } else {
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
        } while (mode == null);

        // Retrieve sorting of results by name and/or size.
        int sorting = 0;
        do {
            System.out.print("Do you wish to sort the results by name = 1, size = 2 or both = 3? ");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    sorting = 1;
                    break;
                case "2":
                    sorting = 2;
                    break;
                case "3":
                    sorting = 3;
                    break;
                default:
                    System.out.println("Please only enter the desired sorting number [1|2|3]!");
            }
        } while (sorting == 0);

        System.out.printf("Searching for duplicate files in %s...\n\n", folderPath);

        // Identify duplicates with given path and mode.
        DuplicateFinder finder = new DuplicateFinder();
        ArrayList<IDuplicate> candidates = finder.GetCandidates(folderPath, mode);
        ArrayList<IDuplicate> duplicates = finder.CheckCandidates(candidates);

        // Display results of duplicate file finding.
        System.out.println("RESULTS:");
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Name", "Size", "Paths");
        at.addRule();
        if (duplicates.isEmpty()) {
            at.addRow("No duplicate files were found in the given file system!");
        } else {
            // Sort duplicates according to desired sorting.
            int finalSorting = sorting;
            duplicates.sort((a, b) -> {
                String fileA, fileB;
                Integer sizeA, sizeB;
                switch (finalSorting) {
                    case 1:
                        fileA = ((Duplicate) a).getFileName();
                        fileB = ((Duplicate) b).getFileName();
                        return fileA.compareTo(fileB);
                    case 2:
                        sizeA = Integer.valueOf(((Duplicate) a).getFileSize());
                        sizeB = Integer.valueOf(((Duplicate) b).getFileSize());
                        return sizeA.compareTo(sizeB);
                    default:
                        fileA = ((Duplicate) a).getFileName();
                        fileB = ((Duplicate) b).getFileName();
                        sizeA = Integer.valueOf(((Duplicate) a).getFileSize());
                        sizeB = Integer.valueOf(((Duplicate) b).getFileSize());
                        return fileA.compareTo(fileB) + sizeA.compareTo(sizeB);
                }
            });


            // Add duplicates to table.
            duplicates.forEach(duplicate -> {
                String paths = String.valueOf(duplicate.FilePaths());
                paths = paths.substring(1, paths.length() - 1);
                at.addRow(((Duplicate) duplicate).getFileName(), ((Duplicate) duplicate).getFileSize() + " bytes", paths);
                at.addRule();
            });
        }
        at.getRenderer().setCWC(new CWC_LongestWordMax(new int[]{-1, -1, -1}));
        at.setPaddingLeftRight(1);
        System.out.println(at.render());
        scanner.close();
    }
}
