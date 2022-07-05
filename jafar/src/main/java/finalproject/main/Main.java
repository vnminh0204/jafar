package finalproject.main;

import finalproject.codegeneration.Compiler;
import finalproject.codegeneration.HaskellFileGenerator;
import finalproject.exception.ParseException;
import finalproject.model.Program;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;

public class Main {
    // run command    mvn exec:java -Dexec.mainClass="Main"
    public static void main(String[] args) throws ParseException, IOException {
        System.out.println("Hello, Programming Paradigms");
        String BASE_DIR = "src/main/java/finalproject/sample";
        String EXT = ".jafar";

        Compiler compiler = Compiler.instance();
        HaskellFileGenerator haskell = HaskellFileGenerator.instance();
        //files are inside src/main/java/finalproject/sample
        String filename = "arrEq3";  //TODO add the name of file you want to run Ex: "fibFunc" without.jafar
        if (filename.length() != 0) {
            //Compile file src/main/java/finalproject/sample/[filename].jafar
            ParseTree tree = compiler.parse(new File(BASE_DIR, filename + EXT));
            //prog: list of SPRIL instructions
            Program prog = compiler.compile(tree);
            //Program content in haskell
            String progInHaskell = haskell.generateHaskellContent(prog);
            //  uncomment the line below if you want to see program in haskell and SPRIL
            //  Or you can see the file at directory src/main/java/finalproject/sprockell/src/[filename].hs

            //  System.out.println(progInHaskell);
            String result = haskell.buildAndRunJafar(filename);
            System.out.println("RESULT: \n" +result);
        } else if (args.length == 0) {
            String jafarContent = "Program main; int i; {{i:=i+3;} print(i);}"; //String input of program in jafar
            haskell.writeJafar(jafarContent, "main");
            ParseTree tree = compiler.parse(jafarContent);
            //prog: list of SPRIL instructions
            Program prog = compiler.compile(tree);
            //Program content in haskell
            String progInHaskell = haskell.generateHaskellContent(prog);
            //  uncomment the line below if you want to see program in haskell and SPRIL
            //  Or you can see the file at directory src/main/java/finalproject/sprockell/src/main.hs

            //  System.out.println(progInHaskell);
            System.out.println(progInHaskell);
            String result = haskell.buildAndRunJafar("main");
            System.out.println("RESULT: \n" +result);
        } else {
            filename = args[0];//Ex: filename "fibFunc" in sample
            //Compile file src/main/java/finalproject/sample/fibFunc.jafar
            ParseTree tree = compiler.parse(new File(BASE_DIR, filename + EXT));
            //prog: list of SPRIL instructions
            Program prog = compiler.compile(tree);
            //Program content in haskell
            String progInHaskell = haskell.generateHaskellContent(prog);
        //  uncomment the line below if you want to see program in haskell and SPRIL
        //  Or you can see the file at directory src/main/java/finalproject/sprockell/src/[filename].hs

            //  System.out.println(progInHaskell);
            String result = haskell.buildAndRunJafar(filename);
            System.out.println("RESULT: \n" +result);
        }
    }
}