package finalproject.tests;

import finalproject.codegeneration.Compiler;
import finalproject.exception.ParseException;
import finalproject.model.Instr;
import finalproject.model.Program;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


@SuppressWarnings("javadoc")
public class GeneratorTest {
	private final static String BASE_DIR = "src/main/java/finalproject/sample";
	private final static String EXT = ".jafar";
	private final Compiler compiler = Compiler.instance();

	@Test
	public void testGCD() throws IOException, ParseException {
		Program prog = compile("gcd");
	}

	@Test
	public void testSum() throws IOException, ParseException {
		Program prog = compile("sum");
	}

	@Test
	public void testFib() throws IOException, ParseException {
		Program prog = compile("fibFunc");
	}

	@Test
	public void testBasic2() throws IOException, ParseException {
		Program prog = compile("basic2");
	}

	@Test
	public void testSampleIf() throws IOException, ParseException {
		Program prog = compile("sampleif");
	}

	@Test
	public void testWhile() throws IOException, ParseException {
		Program prog = compile("sampleif");

	}
	private Program compile(String filename) throws IOException, ParseException {
		return this.compiler.compile(new File(BASE_DIR, filename + EXT));
	}

}
