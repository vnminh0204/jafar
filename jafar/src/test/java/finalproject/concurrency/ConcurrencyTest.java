package finalproject.concurrency;

import finalproject.compilation.HaskellFileGenerator;
import finalproject.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ConcurrencyTest {
    private final String JAFAR_DIR ="src/test/java/finalproject/concurrency/jafarprogram";
    private final String HASKELL_DIR ="src/test/java/finalproject/concurrency/spril";
    private final String OUTPUT_DIR ="src/test/java/finalproject/concurrency/output";
    private HaskellFileGenerator haskell = HaskellFileGenerator.instance();
    @Before
    public void setUp() {
        haskell.setJafarDir(JAFAR_DIR);
        haskell.setHaskellDir(HASKELL_DIR);
        haskell.setOutputDir(OUTPUT_DIR);
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
    public void testBanking() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("banking");
        String expectedResult = "Sprockell 0 says 250\n";
        assertEquals(result, expectedResult);
    }

    @Test
    public void testNonLock() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("nonLock");
        String expectedResult = "Sprockell 0 says 75\n";
        System.out.println(result);
        assertNotEquals(result, expectedResult);
    }
}
