import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;

import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    private static final int Olap = 3;
    private static final int Olap2 = 5;
    private static final int FormyLoop = 100000;
    private static final int LowerBound = 500;
    private static final int UpperBound = 2000;

    @Test
    public void testCombinationTypicalCase() { // routine
        String str1 = "concat";
        String str2 = "cater";
        int overlap = Olap;
        String expected = "concater";
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(expected, result);
    }

    @Test
    public void testCombinationEmptyStrings() { //edge
        String str1 = "";
        String str2 = "";
        int overlap = 0;
        String expected = "";
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(expected, result);
    }

    @Test
    public void testCombinationLargeOverlap() { //challenge
        String str1 = "concater";
        String str2 = "cater";
        int overlap = Olap2; // overlap is the length of "cater"
        String expected = "concater"; // Expecting str1 since it contains str2
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(expected, result);
    }

    @Test
    public void testAddToSetRoutine() { //routine
        Set<String> strSet = new Set2<>(); // Use the custom Set implementation
        String str = "hello";
        String expectedSet = "{hello}"; // Adjusted expected result

        StringReassembly.addToSetAvoidingSubstrings(strSet, str);

        assertEquals(expectedSet, strSet.toString());
    }

    @Test
    public void testAddToSetEdge() { //edge
        Set<String> strSet = new Set2<>();
        strSet.add("hello");
        String str = "hell";
        String expectedSet = "{hello}";

        StringReassembly.addToSetAvoidingSubstrings(strSet, str);

        assertEquals(expectedSet, strSet.toString());
    }

    @Test
    public void testAddToSetChallenge() { //challenge
        Set<String> strSet = new Set2<>();
        strSet.add("apple");
        strSet.add("banana");

        String str = "pineapple";
        Set<String> expectedSet = new Set2<>();
        expectedSet.add("apple");
        expectedSet.add("banana");
        expectedSet.add("pineapple");

        StringReassembly.addToSetAvoidingSubstrings(strSet, str);

        assertEquals(expectedSet, strSet);
    }

    @Test
    public void testLinesFromInputRoutine() {
        // Routine case: Test with two-line input
        SimpleReader input = new SimpleReader1L("data/cheer-8-2.txt");
        Set<String> result = StringReassembly.linesFromInput(input);
        input.close();

        Set<String> expected = new Set2<>();
        expected.add("Bucks -- Beat");
        expected.add("Go Bucks");

        assertEquals(expected, result);
    }

    @Test
    public void testLinesFromInputEdge() { //edge
        // Edge case: Test with an empty input file
        SimpleReader in = new SimpleReader1L("data/gettysburg-30-4.txt");
        Set<String> result = StringReassembly.linesFromInput(in);
        in.close();
        assertTrue("Expected an empty set for empty input file",
                result.size() == 0);
    }

    @Test
    public void testLinesFromInputLargeFile() { //challenge
        // Challenge test: Test with a large input file
        SimpleReader in = new SimpleReader1L("data/declaration-50-8.txt");
        Set<String> result = StringReassembly.linesFromInput(in);
        in.close();

        // check if within acceptable range
        assertTrue(
                "Expected number of unique lines to be within range of large file",
                result.size() >= LowerBound && result.size() <= UpperBound);
    }

    @Test
    public void testPrintWithLineSeparatorsRoutine() {
        // Routine: Test with a string containing only '~' characters
        String allTildes = "~~~~~";
        SimpleWriter outRoutine = new SimpleWriter1L();
        StringReassembly.printWithLineSeparators(allTildes, outRoutine);
        outRoutine.close();
    }

    @Test
    public void testPrintWithLineSeparatorsEdgeCase() {
        // Edge case: Test with an empty string
        String emptyInput = "";
        SimpleWriter outEdge = new SimpleWriter1L();
        StringReassembly.printWithLineSeparators(emptyInput, outEdge);
        outEdge.close();
    }

    @Test
    public void testPrintWithLineSeparatorsChallenge() {
        // Challenge: Test with a very large input string
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < FormyLoop; i++) {
            largeInput.append("a~");
        }
        SimpleWriter outChallenge = new SimpleWriter1L();
        StringReassembly.printWithLineSeparators(largeInput.toString(),
                outChallenge);
        outChallenge.close();
    }

}
