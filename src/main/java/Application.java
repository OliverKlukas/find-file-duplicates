package src.main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Console application that enables the user to find duplicate files in folder structures.
 *
 * <p>Enables the user to specify folder paths, check for duplicates and sort the results by file names and/or sizes.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // TODO: check all inputs for malicious use and correctness especially of the path.
        // Retrieve desired path and mode
        System.out.println("Welcome to Oliver's duplicate file finder!");
        System.out.print("Please enter a folder path: ");
        String folderPath = scanner.nextLine();
        System.out.print("Do you want to change the default comparison mode? [y/n] ");
        String changeMode = scanner.nextLine();
        CompareMode mode = CompareMode.SizeAndName;
        if (Objects.equals(changeMode, "y")) {
            System.out.print("Please enter the comparison mode (Size, Name or SizeAndName): ");
            String choice = scanner.next("[y|n]");
            switch (choice) {
                case "Size":
                    mode = CompareMode.Size;
                    break;
                case "Name":
                    mode = CompareMode.Name;
            }
        }

        // Identify duplicates with given path and mode.
        DuplicateFinder finder = new DuplicateFinder();
        System.out.println("CANDIDATES:");
        Iterable<IDuplicate> candidates = finder.GetCandidates(folderPath, mode);
        candidates.forEach(candidate -> System.out.println(candidate.FilePaths()));
        System.out.println("\nDUPLICATES:");
        Iterable<IDuplicate> duplicates = finder.CheckCandidates(candidates);
        duplicates.forEach(duplicate -> System.out.println(duplicate.FilePaths()));
    }
}
