package finalproject.semantic;

import finalproject.codegeneration.HaskellFileGenerator;
import finalproject.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SemanticTest {
    private final String JAFAR_DIR ="src/test/java/finalproject/semantic/jafarprogram";
    private final String HASKELL_DIR ="src/test/java/finalproject/semantic/spril";
    private final String OUTPUT_DIR ="src/test/java/finalproject/semantic/output";
    private HaskellFileGenerator haskell = HaskellFileGenerator.instance();
    @Before
    public void setUp() {
        haskell.setJafarDir(JAFAR_DIR);
        haskell.setHaskellDir(HASKELL_DIR);
        haskell.setOutputDir(OUTPUT_DIR);
    }

    @Test
    public void testNumDaysFebruary() throws IOException, ParseException {
//        String result = haskell.buildAndRunJafar(filename);
//        System.out.println("RESULT: \n" +result);
//        Program prog = compile("gcd");
    }

    @Test
    public void testFunction() throws IOException, ParseException {
        // the 7th Fibonacci number is 21
        String result = haskell.buildAndRunJafar("fibFunc");
        String expectedResult = "Sprockell 0 says 21\n";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDivSimple() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("divSimple");
        String expectedResult = "Sprockell 0 says 2\n";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testModSimple() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("modSimple");
        String expectedResult = "Sprockell 0 says 0\n";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testPeterson() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("peterson");
        StringBuilder builder = new StringBuilder();
        builder.append("Sprockell 0 says 0").append("\n");
        for (int i = 1; i <= 5; i++){
            builder.append("Sprockell 1 says ").append(i).append("\n");
        }
        for (int i = 6; i <= 10; i++){
            builder.append("Sprockell 2 says ").append(i).append("\n");
        }
        String expectedResult = builder.toString();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNestedThreads() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("nestedThreads");
        boolean check = result.contains("19") && result.contains("68") && result.contains("106");
        assertEquals(check, true);
    }

    @Test
    public void testWhileLoop() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("prime");
        String expectedResult = "Sprockell 0 says 7\n";
        assertEquals(result, expectedResult);
    }

    @Test (timeout = 1000)
    public void testSomething() {
        while (true);
    }
}
