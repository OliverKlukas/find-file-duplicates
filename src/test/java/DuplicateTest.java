import com.duplicates.Duplicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DuplicateTest {
    @Test
    @DisplayName("Paths are added to duplicate list.")
    void testAdd() {
        Duplicate duplicate = new Duplicate();
        duplicate.add("test.txt");
        assertEquals(1, duplicate.FilePaths().size());
    }

    @Test
    @DisplayName("Contained paths are recognized.")
    void testContains() {
        Duplicate duplicate = new Duplicate();
        duplicate.add("test.txt");
        assertTrue(duplicate.contains("test.txt"));
    }

    @Test
    @DisplayName("Paths are retrievable via index.")
    void testGetPath() {
        Duplicate duplicate = new Duplicate();
        duplicate.add("test.txt");
        assertEquals("test.txt", duplicate.getPath(0));
    }

    @Test
    @DisplayName("Exception is thrown on empty duplicate list file name request.")
    void testGetFileName() {
        Duplicate duplicate = new Duplicate();
        assertThrows(RuntimeException.class, duplicate::getFileName);
    }

    @Test
    @DisplayName("File paths are correctly returned as ArrayList.")
    void testFilePaths() {
        Duplicate duplicate = new Duplicate();
        duplicate.add("test.txt");
        ArrayList<String> tester = new ArrayList<>();
        tester.add("test.txt");
        assertEquals(tester, duplicate.FilePaths());
    }

}
