package com.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.stream.Stream;

public class DuplicateFinder implements IDuplicateFinder {
    @Override
    public ArrayList<IDuplicate> GetCandidates(String folderPath) {
        return GetCandidates(folderPath, CompareMode.SizeAndName);
    }

    @Override
    public ArrayList<IDuplicate> GetCandidates(String folderPath, CompareMode mode) {
        // Candidate list with potential Duplicates in it.
        ArrayList<IDuplicate> candidates = new ArrayList<>();

        // Hashmap to identify duplicate candidates.
        HashMap<String, String> identifier = new HashMap<>();

        // Build stream of all file paths through traversing given directory tree.
        try (Stream<Path> filePaths = Files.walk(Paths.get(folderPath)).filter(Files::isRegularFile)) {
            filePaths.forEach(path -> {
                // Build (key, value) pair based on path and mode.
                String key;
                try {
                    switch (mode) {
                        case Size:
                            key = String.valueOf(Files.size(path));
                            break;
                        case Name:
                            key = String.valueOf(path.getFileName());
                            break;
                        default:
                            key = String.valueOf(Files.size(path)) + String.valueOf(path.getFileName());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String value = path.toString();

                // Check if file might qualify as candidate based on already traversed files.
                String existingValue = identifier.put(key, value);
                if (existingValue != null) {

                    // Check if file needs to be added to already existing duplicate list.
                    int index = -1;
                    for (IDuplicate candidate : candidates) {
                        if (((Duplicate) candidate).contains(existingValue)) {
                            index = candidates.indexOf(candidate);
                            break;
                        }
                    }
                    if (index >= 0) {
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
    public ArrayList<IDuplicate> CheckCandidates(Iterable<IDuplicate> candidates) {
        // List of verified duplicates.
        ArrayList<IDuplicate> duplicates = new ArrayList<>();

        // Check each candidate list separately for duplicates.
        for (IDuplicate candidate : candidates) {
            ArrayList<String> checksums = new ArrayList<>();
            for (String path : candidate.FilePaths()) {
                // Calculate MD5 checksum of file, inspired by: https://www.baeldung.com/java-md5 and https://stackoverflow.com/a/19469285/19312696
                try {
                    MessageDigest md;
                    md = MessageDigest.getInstance("MD5");
                    md.update(Files.readAllBytes(Paths.get(path)));
                    byte[] digest = md.digest();
                    Formatter formatter = new Formatter();
                    for (byte b : digest) {
                        formatter.format("%02x", b);
                    }
                    String checksum = formatter.toString();
                    checksums.add(checksum);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            // Compare checksums of files to each other and add to real duplicate instance. // TODO: very inefficient way of doing this.
            Duplicate duplicate = new Duplicate();
            for (int i = 0; i < checksums.size(); i++) {
                for (int j = 0; j < checksums.size(); j++) {
                    if (i != j && checksums.get(i).equalsIgnoreCase(checksums.get(j))) {
                        if (!duplicate.contains(((Duplicate) candidate).get(i))) {
                            duplicate.add(((Duplicate) candidate).get(i));
                        }
                        if (!duplicate.contains(((Duplicate) candidate).get(j))) {
                            duplicate.add(((Duplicate) candidate).get(j));
                        }
                    }
                }
            }

            // Add real duplicate to duplicate list.
            if (!duplicate.FilePaths().isEmpty()) {
                duplicates.add(duplicate);
            }
        }
        return duplicates;
    }
}

