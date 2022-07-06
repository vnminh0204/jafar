package finalproject.semantic;

import finalproject.compilation.HaskellFileGenerator;
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
    public void testMatrixMultiplication() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("matrixMultiplication");
        // matrix multiplication of A[2][3] = [[1,2,3], [4,5,6]] * B[3][2] = [[10,11],[20,21],[30,31]]
        String expectedResult = "Sprockell 0 says 140\n" +
                "Sprockell 0 says 146\n" +
                "Sprockell 0 says 320\n" +
                "Sprockell 0 says 335\n";
        //result is matrix C[2][2] = [[140,146],[320,335]];
        assertEquals(expectedResult, result);
    }

    @Test
    public void test3DArrayCompare() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("3dArrayEq");
        // 3d array x:= [[[1,3],[5,7],[8,9]],[[10,11],[8,9],[15,17]]]
        // 3d array y:= x with x[0][0][1] = 2 instead of 3
        String expectedResult = "Sprockell 0 says 1\n" + // expect True when compare x[1][1] == x[0][2]
                "Sprockell 0 says 0\n" + // expect False when compare x with y
                "Sprockell 0 says 1\n" + // expect True when compare y == [[[1,2],[5,7],[8,9]],[[10,11],[8,9],[15,17]]]
                "Sprockell 0 says 0\n" + // expect False when compare 2d array x[1] with 1d array [10,11]
                "Sprockell 0 says 1\n" + // expect True when compare [[1,2],[3,4]] == [[1,2],[3,4]]
                "Sprockell 0 says 0\n"; // expect False when compare y with 3d array with dimension [2][2][2]
        assertEquals(expectedResult, result);
    }


    @Test
    public void testFibSeq() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("fibSeq");
        //initialize array with [1,1,0,0,0,0,0,0,0,0,0,0] to calculate first 10th Fibonacci number
        String expectedResult = "Sprockell 0 says 1\n" +
                "Sprockell 0 says 1\n" +
                "Sprockell 0 says 2\n" +
                "Sprockell 0 says 3\n" +
                "Sprockell 0 says 5\n" +
                "Sprockell 0 says 8\n" +
                "Sprockell 0 says 13\n" +
                "Sprockell 0 says 21\n" +
                "Sprockell 0 says 34\n" +
                "Sprockell 0 says 55\n";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testArrayIndexOutOfBound() throws IOException, ParseException {
        String result1 = haskell.buildAndRunJafar("arrSortIndexOutOfBound");
        // condition while ((i >= 0) and (a[i]>temp)) will lead to index out of bound when i:=i-1;
        // i = -1 < 0
        String expectedResult1 = "";
        assertEquals(expectedResult1, result1);
        String result2 = haskell.buildAndRunJafar("arrIndexOutOfBound");
        // print(x[1][3] == x[0][2]); while x is array with dimension [2][3][2] -> index [1][3] out of bound
        // It will only print(15) and terminate program instead of continue to print (1000);
        String expectedResult2 = "Sprockell 0 says 15\n";
        assertEquals(expectedResult2, result2);
    }


    //This test show that function/procedure feature can work with array type
    @Test
    public void testVectorAddition() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("vectorAddition");
        String expectedResult = "Sprockell 0 says 11\n" +
                "Sprockell 0 says 7\n" +
                "Sprockell 0 says 20\n";
        // [9,3,5] + [2,4,15] = [11,7,20]
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSimpleSumProcedure() throws IOException, ParseException {
        String result = haskell.buildAndRunJafar("simpleSumProc");
        //the varaible result is declared globally and the procedure will write into it
        String expectedResult = "Sprockell 0 says 97\n"; // 24+73 == 97
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
    public void testDivisionByZero() throws IOException, ParseException {
        String result1 = haskell.buildAndRunJafar("divByZero1");
        String expectedResult1 = "";
        assertEquals(expectedResult1, result1); // call print(5/0); will terminate program instead of print anything
        String result2 = haskell.buildAndRunJafar("divByZero2");
        String expectedResult2 = "Sprockell 0 says 2\n"; // call print(a/0); with variable will only print(b/a) == 2 then terminate and skip print(b);
        assertEquals(expectedResult2, result2);
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

    @Test
    public void testModuloByZero() throws IOException, ParseException {
        String result1 = haskell.buildAndRunJafar("modByZero");
        String expectedResult1 = "";
        assertEquals(expectedResult1, result1); // call print(6 mod 0); will terminate program instead of print anything
    }

//    @Test (timeout = 1000)
//    public void testSomething() {
//        while (true);
//    }


}
