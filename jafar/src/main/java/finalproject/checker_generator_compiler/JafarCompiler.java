package finalproject.checker_generator_compiler;

import finalproject.exception.ErrorListener;
import finalproject.exception.ParseException;
import finalproject.grammar.JAFARLexer;
import finalproject.grammar.JAFARParser;
import finalproject.model.Program;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JafarCompiler {
	/** The singleton instance of this class. */
	private final static JafarCompiler instance = new JafarCompiler();
	/** Debug flag. */
	private final static boolean SHOW = true;

	/** Returns the singleton instance of this class. */
	public static JafarCompiler instance() {
		return instance;
	}


	/** The fixed checker of this compiler. {@link Checker} */
	private final Checker checker;
	/** The fixed generator of this compiler. {@link Generator}*/
	private final Generator generator;

	private JafarCompiler() {
		this.checker = new Checker();
		this.generator = new Generator();
	}

	/** Typechecks a given Jafar string. */
	public Result check(String text) throws ParseException {
		return check(parse(text));
	}

	/** Typechecks a given Jafar file. */
	public Result check(File file) throws ParseException, IOException {
		return check(parse(file));
	}

	/** Typechecks a given Jafar parse tree. */
	public Result check(ParseTree tree) throws ParseException {
		return this.checker.check(tree);
	}

	/** Compiles a given Jafar string into an SPRIL program. */
	public Program compile(String text) throws ParseException {
		return compile(parse(text));
	}

	/** Compiles a given Jafar file into an SPRIL program. */
	public Program compile(File file) throws ParseException, IOException {
		return compile(parse(file));
	}

	/** Compiles a given Jafar parse tree into an SPRIL program. */
	public Program compile(ParseTree tree) throws ParseException {
		Result checkResult = this.checker.check(tree);
		return this.generator.generate(tree, checkResult);
	}

	/** Compiles a given Jafar string into a parse tree. */
	public ParseTree parse(String text) throws ParseException {
		return parse(CharStreams.fromString(text));
	}

	/** Compiles a given Jafar string into a parse tree. */
	public ParseTree parse(File file) throws ParseException, IOException {
		return parse(CharStreams.fromPath(file.toPath()));
	}

	/** Compile a given Jafar file to an SPRIL program with multiple prog instance */
	public ArrayList<Program> compileMultiple(File file) throws ParseException, IOException {
		return compileMultiple(parse(file));
	}

	/** Compile a given Jafar parse tree to an SPRIL program with multiple prog instance */
	public ArrayList<Program> compileMultiple(ParseTree tree) throws ParseException {
		Result checkResult = this.checker.check(tree);
		return this.generator.gen(tree, checkResult);
	}

	/** Compiles a given a char stream into a parse tree. */
	private ParseTree parse(CharStream chars) throws ParseException {
		ErrorListener listener = new ErrorListener();
		Lexer lexer = new JAFARLexer(chars);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);
		TokenStream tokens = new CommonTokenStream(lexer);
		JAFARParser parser = new JAFARParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		ParseTree result = parser.program();
		listener.throwException();
		return result;
	}
}
