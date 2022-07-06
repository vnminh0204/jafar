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
        String result = haskell.buildAndRunJafar("numDaysFebruary");
        String expectedResult = "Sprockell 0 says 29\n" + //year 2020
                "Sprockell 0 says 28\n" + //year 2021
                "Sprockell 0 says 28\n" + //year 2022
                "Sprockell 0 says 28\n" + //year 2023
                "Sprockell 0 says 29\n"; //year 2024
        assertEquals(expectedResult, result);
    }

    @Test
    public void testPrime() throws IOException, ParseException {
        // the 7th Fibonacci number is 21
        String result = haskell.buildAndRunJafar("prime");
        String expectedResult = "Sprockell 0 says 2\n" + // 2 | 12 => 12 is not prime
                "Sprockell 0 says 1\n" + // 7 is prime
                "Sprockell 0 says 7\n" + // 7 | 77 => 77 is not prime
                "Sprockell 0 says 1\n" + // 23 is prime
                "Sprockell 0 says 5\n"; // 35 is not prime
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGCD() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("gcd");
        String expectedResult = "Sprockell 0 says 12\n"; //12 is gcd(60,36)
        assertEquals(expectedResult, result);
    }

    @Test
    public void testArraySort() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("arrSort");
        //sort array [11,3,8,7,1]
        String expectedResult = "Sprockell 0 says 1\n" +
                "Sprockell 0 says 3\n" +
                "Sprockell 0 says 7\n" +
                "Sprockell 0 says 8\n" +
                "Sprockell 0 says 11\n"; //array after sorting is [1,3,7,8,11]
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFibbonacciFunctionRecursion() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("fibFunc");
        String expectedResult = "Sprockell 0 says 21\n"; // the 7th Fibonacci number is 21
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDivision() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("divSimple");
        String expectedResult = "Sprockell 0 says 50\n" + //100/2
                "Sprockell 0 says 49\n" + // 99/2
                "Sprockell 0 says 1\n" + // 17/17
                "Sprockell 0 says 6\n" + // 91/14
                "Sprockell 0 says 5\n" + // 60/12
                "Sprockell 0 says 11\n" + // 121/11
                "Sprockell 0 says 0\n" + // 0/87
                "Sprockell 0 says 0\n"; // 15/16
        assertEquals(expectedResult, result);
    }

    @Test
    public void testModulo() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("modSimple");
        String expectedResult = "Sprockell 0 says 1\n" + // 6 mod 5
                "Sprockell 0 says 2\n" + // 7 mod 5
                "Sprockell 0 says 3\n" + // 8 mod 5
                "Sprockell 0 says 4\n" + // 9 mod 5
                "Sprockell 0 says 0\n" + // 10 mod 5
                "Sprockell 0 says 1\n" + // 13 mod 6
                "Sprockell 0 says 5\n"; // 21 mod 8
        assertEquals(expectedResult, result);
    }

//    @Test (timeout = 1000)
//    public void testSomething() {
//        while (true);
//    }


}
