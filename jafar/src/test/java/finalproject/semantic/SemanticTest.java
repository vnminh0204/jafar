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
        HaskellFileGenerator haskell = HaskellFileGenerator.instance();
        // the 7th Fibonacci number is 21
        String result = haskell.buildAndRunJafar("fibFunc");
        System.out.println("ASDAS" + result);
        String expectedResult = "Sprockell 0 says 21\n";
        assertEquals(expectedResult, result);
    }
}
