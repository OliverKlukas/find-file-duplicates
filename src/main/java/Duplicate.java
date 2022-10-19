package src.main.java;

import java.util.ArrayList;

public class Duplicate implements IDuplicate {
    ArrayList<String> paths;

    public Duplicate(ArrayList<String> paths){
        this.paths = paths;
    }

    public Duplicate(){
        this.paths = new ArrayList<>();
    }

    public void add(String path){
        this.paths.add(path);
    }

    public boolean contains(String path){
        return paths.contains(path);
    }

    @Override
    public ArrayList<String> FilePaths() {
        return paths;
    }
}
