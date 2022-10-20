package com.duplicates;

public interface IDuplicateFinder {
    /**
     * Compares files for equality based on file name and size.
     *
     * <p>Walks through all files in the specified folder path, adds them to a hashmap using the file name and size and
     * identifies potential duplicate candidates via duplicated keys in hashmap.
     *
     * @param folderPath - Path in which to be compared files lay.
     * @return Returns all files that appear to be identical.
     */
    Iterable<IDuplicate> GetCandidates(String folderPath);

    /**
     * Compares files for equality based on specified comparison mode.
     *
     * <p>Walks through all files in the specified folder path, adds them to a hashmap using the specified comparison
     * mode and identifies potential duplicate candidates via duplicated keys in hashmap.
     *
     * @param folderPath - Path in which to be compared files lay.
     * @param mode       - Comparison mode for files.
     * @return Returns all files that appear to be identical.
     */
    Iterable<IDuplicate> GetCandidates(String folderPath, CompareMode mode);

    /**
     * Checks files for their effective equality.
     *
     * <p>Calculates HD5 checksums of files in the given list of candidates and adds them to duplicate list in case
     * checksums are identical.
     *
     * @param candidates - Iterable list of candidate files that appear to be identical.
     * @return Returns the actual duplicates as a list.
     */
    Iterable<?> CheckCandidates(Iterable<IDuplicate> candidates);
}
