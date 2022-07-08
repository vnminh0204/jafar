package finalproject.main;

import finalproject.compilation.HaskellFileGenerator;
import finalproject.exception.ParseException;

import java.io.IOException;

// run command mvn exec:java

/**
 * Used to generate, compile and run Jafar file
 */
public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        System.out.println("Hello, Programming Paradigms");
        HaskellFileGenerator haskell = HaskellFileGenerator.instance();

        //TODO set the name of any files in "src/main/java/finalproject/sample"
        String filename = "vectorAddition";

        if (filename.length() != 0) {
            String result = haskell.buildAndRunJafar(filename);
            System.out.println("RESULT: \n" +result);
        } else if (args.length == 0) {
            // TODO set program content
            String jafarContent = "Program main; int i; {{i:=i+3;} print(i);}"; //String input of program in jafar

            haskell.writeJafar(jafarContent, "main");
            String result = haskell.buildAndRunJafar("main");
            System.out.println("RESULT: \n" +result);
        } else {
            //TODO set the name of any files in "src/main/java/finalproject/sample" as program argument
            filename = args[0];
            String result = haskell.buildAndRunJafar(filename);
            System.out.println("RESULT: \n" +result);
        }
    }
}