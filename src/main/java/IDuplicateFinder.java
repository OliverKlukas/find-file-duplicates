package src.main.java;

public interface IDuplicateFinder {
    public Iterable<?> GetCandidates(String folderPath);
    public Iterable<?> GetCandidates(String folderPath, CompareMode mode);

    public Iterable<?> CheckCandidates(Iterable<?> candidates);
}
