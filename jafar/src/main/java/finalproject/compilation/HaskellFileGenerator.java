package finalproject.compilation;

import finalproject.exception.ParseException;
import finalproject.model.Program;

import java.io.*;
import java.util.ArrayList;

public class HaskellFileGenerator {
    /**
     * return a instance of HaskellFileGenerator
     */
    private final static HaskellFileGenerator instance = new HaskellFileGenerator();

    /**
     *
     * @return a instance of itself
     */
    public static HaskellFileGenerator instance() {return instance;}

    /**
     * Tree checker
     */
    private final Checker checker;

    /**
     * Programs generator
     */
    private final Generator generator;

    /**
     * Jafar compiler
     */
    private final Compiler compiler;

    /**
     * In and out source folder and format extension of target file
     */
    private static String JAFAR_DIR = "src/main/java/finalproject/sample";
    private static String HASKELL_DIR = "src/main/java/finalproject/samplespril";
    private static String OUTPUT_DIR = "src/main/java/finalproject/sampleoutput";
    private final static String EXT = ".jafar";
    private final static String EXT_OUT = ".hs";

    /**
     * Initialize a new HaskellFileGenerator
     */
    private HaskellFileGenerator() {
        this.checker = new Checker();
        this.generator = new Generator();
        this.compiler = Compiler.instance();
    }

    /**
     * Change JAFAR_DIR
     */
    public void setJafarDir(String newDir) {
        this.JAFAR_DIR = newDir;
    }
    /**
     * Change HASKELL_DIR
     */
    public void setHaskellDir(String newDir) {
        this.HASKELL_DIR = newDir;
    }
    /**
     * Change OUTPUT_DIR
     */
    public void setOutputDir(String newDir) {
        this.OUTPUT_DIR = newDir;
    }

    /**
     * Compile a file from source folder to a program instance
     * @param filename : file name
     * @return program instance generated from the file
     * @throws IOException
     * @throws ParseException
     */
    public Program compile (String filename) throws IOException, ParseException {
        return this.compiler.compile(new File(JAFAR_DIR, filename + EXT));
    }

    /**
     * Compile a file from source folder to program instances
     * @param filename : file name
     * @return list of program instances generated from the file
     * @throws IOException
     * @throws ParseException
     */
    public ArrayList<Program> compileMultiple (String filename) throws IOException, ParseException {
        return this.compiler.compileMultiple(new File(JAFAR_DIR, filename + EXT));
    }

    /**
     * Generate a single String represent single-program SPRIL code.
     * @param filename source file name
     * @return A string represent haskell code
     * @throws IOException
     * @throws ParseException
     */
    public String generateHaskellContent (String filename) throws IOException, ParseException {
        Program p = this.compile(filename);
        StringBuilder builder = new StringBuilder();
        builder.append("module Main where\n");
        builder.append("import Sprockell\n").append("\n");
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
    /**
     * Generate a single String represent single-program SPRIL code.
     * @param p input program
     * @return A string represent haskell code
     * @throws IOException
     * @throws ParseException
     */
    public String generateHaskellContent (Program p) throws IOException, ParseException {
        StringBuilder builder = new StringBuilder();
        builder.append("module Main where\n");
        builder.append("import Sprockell\n").append("\n");
        builder.append("prog :: [Instruction]\n");
        builder.append("prog = [\n");
        builder.append("    " + p.getOps().get(0));
        for (int i = 0; i < p.getOps().size(); i++) {
            builder.append("\n    , " + p.getOps().get(i));
        }
        builder.append("\n    ]\n");
        builder.append("main = run [").append("prog").append("]\n");
        return builder.toString();
    }
    /**
     * Generate a single String represent multi-program SPRIL code.
     * @param filename source file name
     * @return A string represent haskell code
     * @throws IOException
     * @throws ParseException
     */
    public String generateMultiProgHaskellContent (String filename) throws IOException, ParseException {
        ArrayList<Program> ps = this.compileMultiple(filename);
        StringBuilder builder = new StringBuilder();
        builder.append("import Sprockell\n\n\n");
        for (int i = 0; i < ps.size(); i++) {
            Program p = ps.get(i);
            builder.append(p.pprint()).append(" :: [Instruction]\n");
            builder.append(p.pprint()).append(" = [\n");
            builder.append("    " + p.getOps().get(0));
            for (int j = 1; j < p.getOps().size(); j++) {
                builder.append("\n    , " + p.getOps().get(j));
            }
            builder.append("\n    ]\n\n");
        }
//        builder.append("main = runWithDebugger (debuggerSimplePrint myShow) [");
        builder.append("main = run [");
        for (int i = 0; i < ps.size(); i++) {
            Program p = ps.get(i);
            builder.append(p.pprint());
            if (i < ps.size() - 1) {
                builder.append(",");
            }
        }
        builder.append("]\n");
        return builder.toString();
    }

    /**
     * Write a string to a file, create if not exists
     * @param fileContent A string represent the content of file
     * @param filename A string represent the file name
     * @throws IOException
     */
    public void writeJafar (String fileContent, String filename) throws IOException {
        PrintWriter writer = new PrintWriter(new File(JAFAR_DIR, filename + ".jafar"));
        writer.println(fileContent);
        writer.flush();
        writer.close();
    }

    /**
     * Write a string to a file, create if not exists
     * @param fileContent A string represent the content of file
     * @param filename A string represent the file name
     * @throws IOException
     */
    public void write (String fileContent, String filename) throws IOException {
        PrintWriter writer = new PrintWriter(new File(HASKELL_DIR, filename + EXT_OUT));
        writer.println(fileContent);
        writer.flush();
        writer.close();
    }

    /**
     *  Generate a new SPROCKELL .hs program from a .jafar file
     * @param filename Name of the file
     * @throws IOException
     * @throws ParseException
     */
    public void genHaskellFrom(String filename) throws IOException, ParseException {
        String content = generateMultiProgHaskellContent(filename);
        write(content, filename);
    }

    //reference: https://stackoverflow.com/questions/54904549/simple-haskell-script-with-java

    /**
     * Compile, Generate haskell file and run a Jafar file, result in a String represents result of the Jafar program
     * @param filename Name of Jafar file, located in JAFAR_DIR
     * @return Run results from executing JAFAR file
     * @throws IOException
     * @throws ParseException
     */
    public String buildAndRunJafar(String filename) throws IOException, ParseException {
        genHaskellFrom(filename);
        ProcessBuilder builder = new ProcessBuilder("ghci", filename + ".hs");
        builder.directory(new File(HASKELL_DIR));
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        PrintStream writer = new PrintStream(process.getOutputStream());
        writer.println("main");
        writer.flush();
        writer.close();
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
            if (line.contains("Sprockell") && line.contains("says")) {
                if (line.contains("Main")) {
                    line = line.substring(7);
                }

                sb.append(line).append("\n");
//                System.out.println(line);
            }

        }
        PrintWriter fileWriter = new PrintWriter(new File(OUTPUT_DIR, filename + "_result.txt"));
        fileWriter.println(sb);
        fileWriter.flush();
        fileWriter.close();
        return sb.toString();
    }

    /**
     * Main method, used to generate file.
     */
    public static void main(String[] args) throws IOException, ParseException {
        HaskellFileGenerator hs = new HaskellFileGenerator();
        //hs.genHaskellFrom("arrSort");
        System.out.println(hs.buildAndRunJafar("extendedTest"));
    }

}
