package src.main.java;

import java.util.ArrayList;

public class Duplicate implements IDuplicate {
    ArrayList<String> paths;

    public Duplicate(ArrayList<String> paths){
        this.paths = paths;
    }

    @Override
    public ArrayList<String> FilePaths() {
        return paths;
    }
}
