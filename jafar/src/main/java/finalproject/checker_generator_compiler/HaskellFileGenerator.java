package finalproject.checker_generator_compiler;

import finalproject.exception.ParseException;
import finalproject.model.Program;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class HaskellFileGenerator {
    private final static HaskellFileGenerator instance = new HaskellFileGenerator();

    public static HaskellFileGenerator instance() {return instance;}

    private final Checker checker;

    private final Generator generator;
    private final JafarCompiler compiler;

    private final static String BASE_DIR = "src/main/java/finalproject/sample";
    private final static String BASE_DIR_OUT = "src/main/java/finalproject/out";
    private final static String EXT = ".jafar";
    private final static String EXT_OUT = ".hs";

    private HaskellFileGenerator() {
        this.checker = new Checker();
        this.generator = new Generator();
        this.compiler = JafarCompiler.instance();
    }

    public Program compile (String filename) throws IOException, ParseException {
        return this.compiler.compile(new File(BASE_DIR, filename + EXT));
    }

    public String generateHaskellContent (String filename) throws IOException, ParseException {
        Program p = this.compile(filename);
        StringBuilder builder = new StringBuilder();
        builder.append("module Main where\n");
        builder.append("import Sprockell\n");
        builder.append("prog :: [Instruction]\n");
        builder.append("prog = [\n");
        builder.append("    " + p.getOps().get(0));
        for (int i = 0; i < p.getOps().size(); i++) {
            builder.append("\n    , " + p.getOps().get(i));
        }
        builder.append("\n    ]\n");
        builder.append("main = runWithDebugger (debuggerSimplePrint myShow) [").append("prog").append("]\n");
        return builder.toString();

    }

    public void write (String fileContent, String filename) throws IOException {
        PrintWriter writer = new PrintWriter(new File(BASE_DIR_OUT, filename + EXT_OUT));
        writer.println(fileContent);
        writer.flush();
    }

    public void genHaskellFrom(String filename) throws IOException, ParseException {
        String content = generateHaskellContent(filename);
        write(content, filename);
    }
    public static void main(String[] args) throws IOException, ParseException {
        HaskellFileGenerator hs = new HaskellFileGenerator();
        hs.genHaskellFrom("basic");
    }
}
