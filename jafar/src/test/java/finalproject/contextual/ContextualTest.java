package finalproject.contextual;

import finalproject.compilation.CheckerResult;
import finalproject.compilation.Compiler;
import finalproject.compilation.Type;
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
	public void testBasicTypes() throws IOException, ParseException {
		ParseTree tree = parse("basicType1");
		CheckerResult result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		ParseTree assX = body.getChild(1);
		assertEquals(Type.INT, result.getType(assX.getChild(0)));
		assertEquals(Type.INT, result.getType(assX.getChild(2)));
		tree = parse("basicType2");
		result = check(tree);
		body = tree.getChild(3).getChild(1);
		assX = body.getChild(1);
		assertEquals(Type.BOOL, result.getType(assX.getChild(0)));
		assertEquals(Type.BOOL, result.getType(assX.getChild(2)));
		checkFail("typeErr1", "Expected type 'Integer' but found 'Boolean'");
		checkFail("typeErr2", "Expected type 'Integer' but found 'Boolean'");
		checkFail("typeErr3", "Expected type 'Integer' but found 'Boolean'");
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
		checkFail("arrTypeErr1", "Expected type '[2][1]Integer' but found '[2][1]Boolean'");
		checkFail("arrTypeErr2", "Expected type 'Integer' but found 'Boolean'");
		checkFail("arrTypeErr3", "Array 'x' is accessed with higher dimension than declared");
		checkFail("arrTypeErr4", "Index is not of type INT");
		checkFail("arrTypeErr5", "Array is declared with elements are not of the same type");
		checkFail("arrTypeErr6", "Expected type '[1]Integer' but found '[2]Integer'");
	}

	@Test
	public void testArrayIndexContext() throws IOException, ParseException {
		ParseTree tree = parse("arrIndex");
		CheckerResult result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		Type indexX0 = result.getType(body.getChild(2).getChild(0).getChild(2));
		assertEquals(Type.INT, indexX0);
		Type indexXi = result.getType(body.getChild(3).getChild(0).getChild(2));
		assertEquals(Type.INT, indexXi);
		checkFail("arrIndexError1", "Index is not of type INT");
	}

	@Test
	public void testArrayIndexOffset() throws IOException, ParseException {
		ParseTree tree = parse("arrOffSet");
		CheckerResult result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		int indexX110 = result.getOffset(body.getChild(3).getChild(2));
		assertEquals(1, indexX110); //all identifier array accessing even with index should return the offset of parent array's 1st element
		int indexX121 = result.getOffset(body.getChild(4).getChild(0));
		assertEquals(1, indexX121);
	}

	@Test
	public void testIfWhileContext() throws IOException, ParseException {
		ParseTree tree = parse("ifwhile1");
		CheckerResult result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		Type whileExpr = result.getType(body.getChild(3).getChild(1));
		assertEquals(Type.BOOL, whileExpr);
		Type ifExpr = result.getType(body.getChild(3).getChild(3).getChild(1).getChild(1));
		assertEquals(Type.BOOL, ifExpr);
		checkFail("ifwhileErr1", "Expected type 'Boolean' but found 'Integer'"); //whlie condition should be boolean
		checkFail("ifwhileErr2", "Expected type 'Boolean' but found '[2]Integer'"); //if condition should be boolean
	}

	@Test
	public void testPrefixContext() throws IOException, ParseException {
		check(parse("prefix"));
		//prefix ! only for BOOL
		checkFail("prefixErr1", "Expected type 'Boolean' but found 'Integer'");
		//prefix ! only for INT
		checkFail("prefixErr2", "Expected type 'Integer' but found 'Boolean'");
	}

	@Test
	public void testOperationContext() throws IOException, ParseException {
		//Comparision
		//Eq allow any type of comparision if the left and right are of same type
		ParseTree tree = parse("compEq");
		CheckerResult result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		Type comExpr = result.getType(body.getChild(2).getChild(2));
		assertEquals(Type.BOOL, comExpr); // return TYPE of comparision is of type BOOL
		//<=, <, >, >=  only allow INT type
		check(parse("compLtLtEGtGtE"));
		checkFail("compLtLtEGtGtEErr", "Expected type 'Integer' but found '[2]Integer'");
		// +,-,*,/,mod only allow INT type
		tree = parse("arithmeticOperation");
		result = check(tree);
		body = tree.getChild(3).getChild(1);
		Type artmExpr = result.getType(body.getChild(3).getChild(2));
		assertEquals(Type.INT, artmExpr);
		checkFail("arithmeticOperationErr", "Expected type 'Integer' but found 'Boolean'"); // those arithmetic operation only available for integer
		// and,or only allow BOOL type
		tree = parse("booleanOperation");
		result = check(tree);
		body = tree.getChild(3).getChild(1);
		Type boolExpr = result.getType(body.getChild(3).getChild(2));
		assertEquals(Type.BOOL, boolExpr);
		checkFail("booleanOperationErr", "Expected type 'Boolean' but found 'Integer'"); // those boolean operation only available for boolean
	}

	@Test
	public void testFunctionScope() throws IOException, ParseException {
		check(parse("funcScope1")); // able to return variable declared globally
		checkFail("funcScope2", "Variable 'b' not declared"); // NOT able to access variable declared in lower scope
		check(parse("funcScope3")); // able to return variable declared inside function
		check(parse("funcScope4")); // able to return function's parameter
		checkFail("funcScope5", "Function 'add ' already defined in this scope");
		checkFail("funcScope6", "Variable 'sum' not declared"); // shouldn't able to access variable declared in different function
	}

	@Test
	public void testFunctionReturnType() throws IOException, ParseException {
		checkFail("funcReturn1", "expecting {BOOLEAN, INTEGER, VOID}"); // function expect return type to be declared
		checkFail("funcReturn2", "Function 'add' expects return value");
		checkFail("funcReturn3", "Function 'add' expected return type 'Integer' but actual is 'Boolean'");
		checkFail("funcReturn4", "Cannot call function with return type 'Integer ' without assignment");
		checkFail("procedure1", "Expected type 'Integer' but found 'Void'");
		checkFail("procedure2", "Function 'add' of type VOID shoudln't return value");
	}

	@Test
	public void testFunctionOffset() throws IOException, ParseException {
		ParseTree tree = parse("funcOffSet");
		CheckerResult result = check(tree);
		LinkedHashMap<String, Integer> actual = result.getFuncOffSetData("fib");
		LinkedHashMap<String, Integer> expected = new LinkedHashMap<>();
		expected.put("n", 1);
		expected.put("res", 2);
		assertEquals(actual, expected);
		LinkedHashMap<String, Integer> actual2 = result.getFuncOffSetData("sum");
		LinkedHashMap<String, Integer> expected2 = new LinkedHashMap<>();
		expected2.put("a", 1);
		expected2.put("b", 4);
		assertEquals(actual2, expected2);
	}

	@Test
	public void testBasicOffsetsInScope() throws IOException, ParseException {
		ParseTree tree = parse("basicOffSet");
		CheckerResult result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		ParseTree assX = body.getChild(1);
		assertEquals(1, result.getOffset(assX.getChild(0)));
		ParseTree assZ = body.getChild(3);
		assertEquals(2, result.getOffset(assZ.getChild(0)));
		ParseTree assY = body.getChild(2);
		assertEquals(5, result.getOffset(assY.getChild(0)));
	}

	@Test
	public void testNestedScope() throws IOException, ParseException {
		check(parse("scope1"));
		check(parse("scope2"));
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
	public void testComment() throws IOException, ParseException {
		check(parse("comment"));
	}

	@Test
	public void testDeclarationErr() throws IOException, ParseException {
		checkFail("decErr1", "Variable 'x ' already defined in this scope");
		checkFail("decErr2", "Variable 'y ' not declared");
		checkFail("decErr3", "Variable 'y' not declared");
		checkFail("decErr4", "Variable 'y' not declared");
	}

	@Test
	public void testSharedVariable() throws IOException, ParseException {
		// extraneous input 'shared' since we can only declare shared on top of program.
		checkFail("declSharedErr1", "extraneous input 'shared'");
		checkFail("declSharedErr2", "extraneous input 'shared'");
		check(parse("declShared1"));
		check(parse("sharedArray"));
		checkFail("sharedMissuseErr1", "extraneous input '}'");
		checkFail("sharedMissuseErr2", "Expected type 'Integer' but found 'Boolean'");
		checkFail("sharedMissuseErr3", "Variable 'x ' already defined as shared variable");
	}

	@Test
	public void testNonSharedVariableInConcurrency() throws IOException {
		checkFail("nonSharedMissUseErr1", "Variable 'x' is not shared but accessed inside thread");
		checkFail("nonSharedMissUseErr2", "Variable 'x' is not shared but accessed inside thread");
	}

	@Test
	public void testThreadCreationErr() throws IOException {
		checkFail("threadCreationErr1", "MAX_THREADS (6) reached, please reduce number of threads");
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
}
