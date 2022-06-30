package finalproject.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import finalproject.checker_generator_compiler.Result;
import finalproject.checker_generator_compiler.Type;
import finalproject.checker_generator_compiler.JafarCompiler;
import finalproject.exception.ParseException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;


@SuppressWarnings("javadoc")
public class CheckerTest {
	private final static String BASE_DIR = "src/main/java/finalproject/sample";
	private final static String EXT = ".jafar";
	private final JafarCompiler compiler = JafarCompiler.instance();

	@Test
	public void testBasicTypes() throws IOException, ParseException {
		ParseTree tree = parse("basic");
		Result result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		ParseTree assX = body.getChild(1);
		assertEquals(Type.INT, result.getType(assX.getChild(0)));
		assertEquals(Type.INT, result.getType(assX.getChild(2)));
	}

	@Test
	public void testBasicEntries() throws IOException, ParseException {
		ParseTree tree = parse("basic");
		Result result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		ParseTree assX = body.getChild(1);
		assertEquals(assX.getChild(2), result.getEntry(assX));
		assertEquals(assX.getChild(2), result.getEntry(body));
	}

	@Test
	public void testBasicOffsets() throws IOException, ParseException {
		ParseTree tree = parse("basic");
		Result result = check(tree);
		ParseTree body = tree.getChild(3).getChild(1);
		ParseTree assX = body.getChild(1);
		assertEquals(1, result.getOffset(assX.getChild(0)));
		ParseTree assY = body.getChild(2);
		assertEquals(2, result.getOffset(assY.getChild(0)));
	}

	@Test
	public void testNestedScopeOffsets() throws IOException, ParseException {
		ParseTree tree = parse("scope2");
		Result result = check(tree);
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
	public void testGCD() throws IOException, ParseException {
		check(parse("gcd"));
	}

	@Test
	public void testPrime() throws IOException, ParseException {
		check(parse("prime"));
	}

	@Test
	public void testScope() throws IOException, ParseException {
		check(parse("scope1"));
		check(parse("scope2"));
	}

	@Test
	public void testComment() throws IOException, ParseException {
		check(parse("comment"));
	}

	@Test
	public void testDecErr() throws IOException, ParseException {
		checkFail("decErr1");
		checkFail("decErr2");
		checkFail("decErr3");
		checkFail("decErr4");
	}

	@Test
	public void testTypeErr() throws IOException, ParseException {
		checkFail("typeErr1");
		checkFail("typeErr2");
		checkFail("typeErr3");
	}

	private void checkFail(String filename) throws IOException {
		try {
			check(parse(filename));
			fail(filename + " shouldn't check but did");
		} catch (ParseException exc) {
			exc.print(filename);
		}
	}

	private ParseTree parse(String filename) throws IOException, ParseException {
		return this.compiler.parse(new File(BASE_DIR, filename + EXT));
	}

	private Result check(ParseTree tree) throws ParseException {
		return this.compiler.check(tree);
	}
}
