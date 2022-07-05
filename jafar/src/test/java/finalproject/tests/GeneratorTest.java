package finalproject.tests;

import finalproject.checker_generator_compiler.JafarCompiler;
import finalproject.exception.ParseException;
import finalproject.model.Instr;
import finalproject.model.Program;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


@SuppressWarnings("javadoc")
public class GeneratorTest {
	private final static String BASE_DIR = "src/finalproject/sample";
	private final static String EXT = ".jafar";
	private final JafarCompiler compiler = JafarCompiler.instance();

	@Test
	public void testGCD() throws IOException, ParseException {
		Program prog = compile("gcd");
	}

	@Test
	public void testSum() throws IOException, ParseException {
		Program prog = compile("sum");
		int lineCount = 1;
		for (Instr i: prog.getInstr()) {
			System.out.println("Line " + lineCount + ": " + i.toString());
			lineCount++;
		}
	}

	@Test
	public void testFib() throws IOException, ParseException {
		Program prog = compile("fib");
	}

	@Test
	public void testBasic2() throws IOException, ParseException {
		Program prog = compile("basic2");
		for (Instr i: prog.getInstr()) {
			System.out.println(i.toString());
		}
	}

	@Test
	public void testSampleIf() throws IOException, ParseException {
		Program prog = compile("sampleif");
		for (Instr i: prog.getInstr()) {
			System.out.println(i.toString());
		}
	}

	@Test
	public void testWhile() throws IOException, ParseException {
		Program prog = compile("sampleif");
		for (Instr i: prog.getInstr()) {
			System.out.println(i.toString());
		}
	}
	private Program compile(String filename) throws IOException, ParseException {
		return this.compiler.compile(new File(BASE_DIR, filename + EXT));
	}

}
