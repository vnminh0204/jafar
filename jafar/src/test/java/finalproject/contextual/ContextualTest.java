<<<<<<< HEAD
//package finalproject.contextual;
//
//import finalproject.codegeneration.Compiler;
//import finalproject.codegeneration.CheckerResult;
//import finalproject.codegeneration.Type;
//import finalproject.exception.ParseException;
//import org.antlr.v4.runtime.tree.ParseTree;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.LinkedHashMap;
//
//import static org.junit.Assert.*;
//
//@SuppressWarnings("javadoc")
//public class ContextualTest {
//	private final static String BASE_DIR = "src/main/java/finalproject/sample";
//	private final static String EXT = ".jafar";
//	private final Compiler compiler = Compiler.instance();
//
//	@Test
//	public void testProcedure() throws IOException, ParseException {
//		checkFail("procErr1");
//		checkFail("procErr2");
//		checkFail("procErr3");
//		check(parse("sum"));
//	}
//
//	@Test
//	public void testFunction() throws IOException, ParseException {
//		checkFail("funcErr1");
//		checkFail("funcErr3");
//		checkFail("funcErr2");
//		checkFail("funcErr4");
//		checkFail("funcErr5");
//		checkFail("funcErr6");
//		check(parse("fibFunc"));
//	}
//
//	@Test
//	public void testBasicTypes() throws IOException, ParseException {
//		ParseTree tree = parse("basic");
//		CheckerResult result = check(tree);
//		ParseTree body = tree.getChild(3).getChild(1);
//		ParseTree assX = body.getChild(1);
//		assertEquals(Type.INT, result.getType(assX.getChild(0)));
//		assertEquals(Type.INT, result.getType(assX.getChild(2)));
//	}
//
//	@Test
//	public void testArrayTypes() throws IOException, ParseException {
//		ParseTree tree = parse("arr1");
//		CheckerResult result = check(tree);
//		ParseTree body = tree.getChild(3).getChild(1);
//		Type xType = result.getType(body.getChild(2).getChild(0));
//		Type yType = result.getType(body.getChild(3).getChild(0));
//		Type expectedXType = new Type.Array(2, new Type.Array(1, Type.INT));
//		assertEquals(expectedXType, xType);
//		Type expectedYType = new Type.Array(1, Type.INT);
//		assertEquals(expectedYType, yType);
//		ParseTree tree2 = parse("arr2");
//		CheckerResult result2 = check(tree2);
//		ParseTree body2 = tree2.getChild(3).getChild(1);
//		Type yType2 = result2.getType(body2.getChild(3).getChild(0));
//		assertEquals(Type.INT, yType2);
//		checkFail("arrErr1");
//		checkFail("arrErr2");
//		checkFail("arrErr3");
//		checkFail("arrErr4");
//		checkFail("arrErr5");
//		checkFail("arrErr6");
//	}
//
//	@Test
//	public void testFuncInfo() throws IOException, ParseException {
//		ParseTree tree = parse("funcInfo");
//		CheckerResult result = check(tree);
//		LinkedHashMap<String, Integer> actual = result.getFuncOffSetData("fib");
//		LinkedHashMap<String, Integer> expected = new LinkedHashMap<>();
//		expected.put("n", 1);
//		expected.put("res", 2);
//		assertEquals(actual, expected);
//		LinkedHashMap<String, Integer> actual2 = result.getFuncOffSetData("sum");
//		LinkedHashMap<String, Integer> expected2 = new LinkedHashMap<>();
//		expected2.put("a", 1);
//		expected2.put("b", 2);
//		assertEquals(actual2, expected2);
//	}
//
//	@Test
//	public void testBasicOffsetsInScope() throws IOException, ParseException {
//		ParseTree tree = parse("basic");
//		CheckerResult result = check(tree);
//		ParseTree body = tree.getChild(3).getChild(1);
//		ParseTree assX = body.getChild(1);
//		assertEquals(1, result.getOffset(assX.getChild(0)));
//		ParseTree assY = body.getChild(2);
//		assertEquals(2, result.getOffset(assY.getChild(0)));
//	}
//
//	@Test
//	public void testNestedScopeOffsets() throws IOException, ParseException {
//		ParseTree tree = parse("scope2");
//		CheckerResult result = check(tree);
//		ParseTree body = tree.getChild(3).getChild(1);
//		ParseTree assX = body.getChild(1).getChild(0).getChild(2).getChild(0).getChild(2);
//		ParseTree assA = body.getChild(1).getChild(0).getChild(2).getChild(0).getChild(3).getChild(0).getChild(1);
//		ParseTree assC = body.getChild(1).getChild(0).getChild(2).getChild(0).getChild(3).getChild(0).getChild(2);
//		ParseTree assB1 = body.getChild(1).getChild(0).getChild(2).getChild(0).getChild(3).getChild(0).getChild(3);
//		ParseTree assB2 = body.getChild(1).getChild(0).getChild(3).getChild(0).getChild(2);
//		assertEquals(1, result.getOffset(assX.getChild(0)));
//		assertEquals(2, result.getOffset(assA.getChild(0)));
//		assertEquals(6, result.getOffset(assC.getChild(0)));
//		assertEquals(5, result.getOffset(assB1.getChild(0)));
//		assertEquals(Type.INT, result.getType(assB1.getChild(0)));
//		assertEquals(4, result.getOffset(assB2.getChild(0)));
//		assertEquals(Type.BOOL, result.getType(assB2.getChild(0)));
//	}
//
//	@Test
//	public void testNestedScope() throws IOException, ParseException {
//		check(parse("scope1"));
//		check(parse("scope2"));
//	}
//
//	@Test
//	public void testComment() throws IOException, ParseException {
//		check(parse("comment"));
//	}
//
//	@Test
//	public void testDecErr() throws IOException, ParseException {
//		checkFail("decErr1");
//		checkFail("decErr2");
//		checkFail("decErr3");
//		checkFail("decErr4");
//	}
//
//	@Test
//	public void testTypeErr() throws IOException, ParseException {
//		checkFail("typeErr1");
//		checkFail("typeErr2");
//		checkFail("typeErr3");
//	}
//
//	private void checkFail(String filename, String expectedError) throws IOException {
//		try {
//			check(parse(filename));
//			fail(filename + " shouldn't check but did");
//		} catch (ParseException exc) {
//			assertTrue((exc.print(filename).contains(expectedError)));
//		}
//	}
//
//	private ParseTree parse(String filename) throws IOException, ParseException {
//		return this.compiler.parse(new File(BASE_DIR, filename + EXT));
//	}
//
//	private CheckerResult check(ParseTree tree) throws ParseException {
//		return this.compiler.check(tree);
//	}
//}
=======
package finalproject.contextual;

import finalproject.codegeneration.Compiler;
import finalproject.codegeneration.CheckerResult;
import finalproject.codegeneration.Type;
import finalproject.exception.ParseException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

@SuppressWarnings("javadoc")
public class ContextualTest {
	private final static String BASE_DIR = "src/test/java/finalproject/contextual/jafarprogram";
	private final static String EXT = ".jafar";
	private final Compiler compiler = Compiler.instance();

	@Test
	public void testProcedure() throws IOException, ParseException {
		checkFail("procErr1", "Expected type 'Integer' but found 'Void'");
		checkFail("procErr2", "Function ’add ’ of type VOID shoudln't return value");
		checkFail("procErr3", "Function ’add ’ of type VOID shoudln't return value");
		check(parse("sum"));
	}

	@Test
	public void testFunction() throws IOException, ParseException {
		checkFail("funcErr1", "Function ’add’ expects return value");
		checkFail("funcErr2", "extraneous input 'bool' expecting ID");
		checkFail("funcErr3", "Function ’add’ expects return value");
		checkFail("funcErr4", "Function ’add’ expected return type 'Integer' but actual is 'Boolean'");
		checkFail("funcErr5", "Function ’add’ expected return type 'Integer' but actual is 'Boolean'");
		checkFail("funcErr6", "mismatched input 'return'");
		check(parse("fibFunc"));
	}

	@Test
	public void testBasicTypes() throws IOException, ParseException {
		ParseTree tree = parse("basic");
		CheckerResult result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		ParseTree assX = body.getChild(1);
		assertEquals(Type.INT, result.getType(assX.getChild(0)));
		assertEquals(Type.INT, result.getType(assX.getChild(2)));
	}

	@Test
	public void testArrayTypes() throws IOException, ParseException {
		ParseTree tree = parse("arr1");
		CheckerResult result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		Type xType = result.getType(body.getChild(2).getChild(0));
		Type yType = result.getType(body.getChild(3).getChild(0));
		Type expectedXType = new Type.Array(2, new Type.Array(1, Type.INT));
		assertEquals(expectedXType, xType);
		Type expectedYType = new Type.Array(1, Type.INT);
		assertEquals(expectedYType, yType);
		ParseTree tree2 = parse("arr2");
		CheckerResult result2 = check(tree2);
		ParseTree body2 = tree2.getChild(3).getChild(1);
		Type yType2 = result2.getType(body2.getChild(3).getChild(0));
		assertEquals(Type.INT, yType2);
		checkFail("arrErr1", "Expected type '[2][1]Integer' but found '[2][1]Boolean'");
		checkFail("arrErr2", "Expected type 'Integer' but found 'Boolean'");
		checkFail("arrErr3", "Array 'x' is accessed with higher dimension than declared");
		checkFail("arrErr4", "Index is not of type INT");
		checkFail("arrErr5", "Array is declared with elements are not of the same type");
		checkFail("arrErr6", "Expected type '[1]Integer' but found '[2]Integer'");

	}

	@Test
	public void testFuncInfo() throws IOException, ParseException {
		ParseTree tree = parse("funcInfo");
		CheckerResult result = check(tree);
		LinkedHashMap<String, Integer> actual = result.getFuncOffSetData("fib");
		LinkedHashMap<String, Integer> expected = new LinkedHashMap<>();
		expected.put("n", 1);
		expected.put("res", 2);
		assertEquals(actual, expected);
		LinkedHashMap<String, Integer> actual2 = result.getFuncOffSetData("sum");
		LinkedHashMap<String, Integer> expected2 = new LinkedHashMap<>();
		expected2.put("a", 1);
		expected2.put("b", 2);
		assertEquals(actual2, expected2);
	}

	@Test
	public void testBasicOffsetsInScope() throws IOException, ParseException {
		ParseTree tree = parse("basic");
		CheckerResult result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		ParseTree assX = body.getChild(1);
		assertEquals(1, result.getOffset(assX.getChild(0)));
		ParseTree assY = body.getChild(2);
		assertEquals(2, result.getOffset(assY.getChild(0)));
	}

	@Test
	public void testNestedScopeOffsets() throws IOException, ParseException {
		ParseTree tree = parse("scope2");
		CheckerResult result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		ParseTree assX = body.getChild(1).getChild(0).getChild(2).getChild(0).getChild(2);
		ParseTree assA = body.getChild(1).getChild(0).getChild(2).getChild(0).getChild(3).getChild(0).getChild(1);
		ParseTree assC = body.getChild(1).getChild(0).getChild(2).getChild(0).getChild(3).getChild(0).getChild(2);
		ParseTree assB1 = body.getChild(1).getChild(0).getChild(2).getChild(0).getChild(3).getChild(0).getChild(3);
		ParseTree assB2 = body.getChild(1).getChild(0).getChild(3).getChild(0).getChild(2);
		assertEquals(1, result.getOffset(assX.getChild(0)));
		assertEquals(2, result.getOffset(assA.getChild(0)));
		assertEquals(6, result.getOffset(assC.getChild(0)));
		assertEquals(5, result.getOffset(assB1.getChild(0)));
		assertEquals(Type.INT, result.getType(assB1.getChild(0)));
		assertEquals(4, result.getOffset(assB2.getChild(0)));
		assertEquals(Type.BOOL, result.getType(assB2.getChild(0)));
	}

	@Test
	public void testNestedScope() throws IOException, ParseException {
		check(parse("scope1"));
		check(parse("scope2"));
	}

	@Test
	public void testComment() throws IOException, ParseException {
		check(parse("comment"));
	}

	@Test
	public void testDecErr() throws IOException, ParseException {
		checkFail("decErr1", "Variable ’x ’ already defined in this scope");
		checkFail("decErr2", "Variable ’y ’ not declared");
		checkFail("decErr3", "Variable 'y' not declared");
		checkFail("decErr4", "Variable 'y' not declared");
	}

	@Test
	public void testTypeErr() throws IOException, ParseException {
		checkFail("typeErr1", "Expected type 'Integer' but found 'Boolean'");
		checkFail("typeErr2", "Expected type 'Boolean' but found 'Integer'");
		checkFail("typeErr3", "Expected type 'Integer' but found 'Boolean'");
	}

	@Test
	public void testSharedErr() throws IOException {
		// extraneous input 'shared' since we can only declare shared on top of program.
		checkFail("declSharedErr1", "extraneous input 'shared'");
		checkFail("declSharedErr2", "extraneous input 'shared'");
		checkFail("declSharedErr3", "extraneous input 'shared'");
		checkFail("declSharedErr4", "mismatched input ',' expecting ';'");

	}

	@Test
	public void testSharedMissuseErr() throws IOException {
		checkFail("sharedMissuseErr1", "extraneous input '}'");
		checkFail("sharedMissuseErr2", "Expected type 'Integer' but found 'Boolean'");
		checkFail("sharedMissuseErr3", "Variable ’x ’ already defined as shared variable");
	}


	@Test
	public void testNonSharedMissuseErr() throws IOException {
		checkFail("nonSharedMissUseErr1", "Variable 'x' is not shared but accessed inside thread");
		checkFail("nonSharedMissUseErr2", "Variable 'x' is not shared but accessed inside thread");
	}

	//
	@Test
	public void testThreadCreationErr() throws IOException {
		checkFail("threadCreationErr1", "MAX_THREADS (6) reached, please reduce number of threads");
//		checkFail("nonSharedMissUseErr2", "Variable 'x' is not shared but accessed inside thread");
	}


	private void checkFail(String filename, String expectedError) throws IOException {
		try {
			check(parse(filename));
			fail(filename + " shouldn't check but did");
		} catch (ParseException exc) {
			assertTrue((exc.print(filename).contains(expectedError)));
		}
	}

	private ParseTree parse(String filename) throws IOException, ParseException {
		return this.compiler.parse(new File(BASE_DIR, filename + EXT));
	}

	private CheckerResult check(ParseTree tree) throws ParseException {
		return this.compiler.check(tree);
	}

	public static void main(String[] args) throws IOException, ParseException {
		ContextualTest t = new ContextualTest();
		t.compiler.check(t.parse("threadCreationErr1"));
	}
}

>>>>>>> 66706f7322ded0d0cd0daf04bf74adbadc5c896f
