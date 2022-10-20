import com.duplicates.CompareMode;
import com.duplicates.DuplicateFinder;
import com.duplicates.IDuplicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuplicateFinderTest {

    @Test
    @DisplayName("GetCandidates without specified modes defaults to SizeAndName comparison.")
    void testGetCandidates() {
        DuplicateFinder finder = new DuplicateFinder();
        ArrayList<IDuplicate> candidates = finder.GetCandidates("src/test/resources", CompareMode.SizeAndName);
        ArrayList<IDuplicate> candidatesWithoutMode = finder.GetCandidates("src/test/resources");
        assertEquals(candidatesWithoutMode.get(0).FilePaths().toString(), candidates.get(0).FilePaths().toString());
    }

    @Test
    @DisplayName("GetCandidates successfully identifies potential files.")
    void testGetCandidatesWithMode() {
        DuplicateFinder finder = new DuplicateFinder();
        ArrayList<IDuplicate> candidatesSize = finder.GetCandidates("src/test/resources", CompareMode.Size);
        assertEquals("[src/test/resources/test/test.txt, src/test/resources/test.txt]", candidatesSize.get(0).FilePaths().toString());

        ArrayList<IDuplicate> candidatesSizeAndName = finder.GetCandidates("src/test/resources", CompareMode.SizeAndName);
        assertEquals("[src/test/resources/test/test.txt, src/test/resources/test.txt]", candidatesSizeAndName.get(0).FilePaths().toString());

        ArrayList<IDuplicate> candidatesName = finder.GetCandidates("src/test/resources", CompareMode.Name);
        assertEquals("[src/test/resources/testUnequal/test.txt, src/test/resources/test/test.txt, src/test/resources/test.txt]", candidatesName.get(0).FilePaths().toString());
    }

    @Test
    @DisplayName("CheckCandidates identifies non duplicate files in GetCandidates candidate list.")
    void testCheckCandidates() {
        DuplicateFinder finder = new DuplicateFinder();
        ArrayList<IDuplicate> candidates = finder.GetCandidates("src/test/resources", CompareMode.Name);
        ArrayList<IDuplicate> duplicates = finder.CheckCandidates(candidates);
        assertEquals("[src/test/resources/test/test.txt, src/test/resources/test.txt]", duplicates.get(0).FilePaths().toString());
    }

}
