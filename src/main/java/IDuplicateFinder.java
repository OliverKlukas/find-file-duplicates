package src.main.java;

public interface IDuplicateFinder {
    /**
     * Compares files for equality based on file name and size.
     *
     * <p>Walks through all files in the specified folder path and compares based on file name and size.
     *
     * @param folderPath - Path in which to be compared files lay.
     * @return Returns all files that appear to be identical.
     */
    Iterable<IDuplicate> GetCandidates(String folderPath);

    /**
     * Compares files for equality based on specified comparison mode.
     *
     * <p>Walks through all files in the specified folder path and compares based comparison mode that may be file size, file name or both.
     *
     * @param folderPath - Path in which to be compared files lay.
     * @param mode       - Comparison mode for files.
     * @return Returns all files that appear to be identical.
     */
    Iterable<IDuplicate> GetCandidates(String folderPath, CompareMode mode);

    /**
     * Checks files for their effective equality.
     *
     * <p>Utilizes a MD5 hash based comparison of files.
     *
     * @param candidates - Iterable list of candidate files that appear to be identical.
     * @return Returns the actual duplicates as a list.
     */
    Iterable<?> CheckCandidates(Iterable<IDuplicate> candidates);
}
