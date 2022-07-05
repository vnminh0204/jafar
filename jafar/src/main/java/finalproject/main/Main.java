package finalproject.main;

import finalproject.grammar.JAFARLexer;
import finalproject.grammar.JAFARParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
//    mvn exec:java -Dexec.mainClass="Main"
    public static void main(String[] args) {
        System.out.println("Hello, Programming Paradigms");

        String input = "Program a; {{i:=i+3;}}}";

        JAFARLexer myLangLexer = new JAFARLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(myLangLexer);
        JAFARParser parser = new JAFARParser(tokens);
        ParseTree tree = parser.program();

        System.out.println("Children: " + tree.getChildCount() + ", parsed text: " + tree.getText());
    }
}