package com.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Duplicate implements IDuplicate {
    ArrayList<String> paths;

    public Duplicate() {
        this.paths = new ArrayList<>();
    }

    public void add(String path) {
        this.paths.add(path);
    }

    public boolean contains(String path) {
        return paths.contains(path);
    }

    public String getPath(int index) {
        return paths.get(index);
    }

    public String getFileName() {
        if (this.paths.isEmpty()) {
            throw new RuntimeException("No file in duplicate list so far!");
        }
        return Paths.get(this.paths.get(0)).getFileName().toString();
    }

    public String getFileSize() {
        String size;
        if (this.paths.isEmpty())
            throw new RuntimeException("No file in duplicate list so far!");
        try {
            size = String.valueOf(Files.size(Paths.get(this.paths.get(0))));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Given path %s is invalid!\n%s", this.paths.get(0), e));
        }
        return size;
    }

    @Override
    public ArrayList<String> FilePaths() {
        return paths;
    }
}
