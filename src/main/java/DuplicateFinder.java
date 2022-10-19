package src.main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DuplicateFinder implements IDuplicateFinder {
    @Override
    public Iterable<IDuplicate> GetCandidates(String folderPath) {
        return GetCandidates(folderPath, CompareMode.SizeAndName);
    }

    @Override
    public Iterable<IDuplicate> GetCandidates(String folderPath, CompareMode mode) {
        // Candidate list with potential Duplicates in it.
        ArrayList<IDuplicate> candidates = new ArrayList<>();

        // Hashmap to identify duplicate candidates.
        HashMap<String, String> identifier = new HashMap<>();

        // Build stream of all file paths through traversing given directory tree.
        try (Stream<Path> filePaths = Files.walk(Paths.get(folderPath)).filter(Files::isRegularFile)) {
            filePaths.forEach(path -> {
                // Build (key, value) pair based on path.
                String key;
                switch (mode){
                    case Size:
                        try {
                            key = String.valueOf(Files.size(path));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case Name:
                        key = String.valueOf(path.getFileName());
                        break;
                    default:
                        try {
                            key = String.valueOf(Files.size(path)) + String.valueOf(path.getFileName());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                }
                String value = path.toString();

                // Check if file might qualify as candidate based on already traversed files.
                String existingValue = identifier.put(key, value);
                if(existingValue != null){

                    // Check if file needs to be added to already existing duplicate list.
                    int index = -1;
                    for(IDuplicate candidate : candidates){
                        if(((Duplicate) candidate).contains(existingValue)){
                            index = candidates.indexOf(candidate);
                            break;
                        }
                    }
                    if(index >= 0){
                        // Overwrite existing duplicate list.
                        Duplicate candidate = (Duplicate) candidates.get(index);
                        candidate.add(value);
                        candidates.set(index, candidate);
                    } else {
                        // Create new duplicate list.
                        Duplicate candidate = new Duplicate();
                        candidate.add(existingValue);
                        candidate.add(value);
                        candidates.add(candidate);
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return candidates;
    }

    @Override
    public Iterable<IDuplicate> CheckCandidates(Iterable<IDuplicate> candidates) {
        return null;  // TODO: HD5 hashes
    }

    public static void main(String[] args){
        DuplicateFinder finder = new DuplicateFinder();
        Iterable<IDuplicate> candidates = finder.GetCandidates("./src", CompareMode.Name);
        candidates.forEach(candidate -> System.out.println(candidate.FilePaths()));
    }

}

