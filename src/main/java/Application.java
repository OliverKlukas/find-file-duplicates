package src.main.java;

import java.util.Objects;
import java.util.Scanner;

/**
 * Console application that enables the user to find duplicate files in folder structures.
 *
 * <p>Enables the user to specify folder paths, check for duplicates and sort the results by file names and/or sizes.
 */
public class Application {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        // TODO: check all inputs for malicious use and correctness

        // Retrieve desired path and mode.
        System.out.println("Welcome to Oliver's duplicate file finder!");
        System.out.print("Please enter a folder path: ");
        String folderPath = scanner.nextLine();
        System.out.print("Do you want to change the default comparison mode? [y/n] ");
        String changeMode = scanner.nextLine();
        CompareMode mode = CompareMode.SizeAndName;
        if(Objects.equals(changeMode, "y")){
            System.out.print("Please enter the comparison mode (Size, Name or SizeAndName): ");
            String choice = scanner.next("[y|n]");
            switch (choice){
                case "Size":
                    mode = CompareMode.Size;
                    break;
                case "Name":
                    mode = CompareMode.Name;
                    break;
                default:
                    break;
            }
        }

        // Identify duplicates with path and mode.
        DuplicateFinder finder = new DuplicateFinder();
        Iterable<IDuplicate> candidates = finder.GetCandidates(folderPath, mode);
        Iterable<IDuplicate> duplicates = finder.CheckCandidates(candidates);

        // Display the found duplicates.
        for (String elem : duplicates){

        }

    }
}
