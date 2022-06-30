package finalproject;

import finalproject.parser.MyLangLexer;
import finalproject.parser.MyLangParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Programming Paradigms");

        String input = "// Line one\n"
            + "Hello\n"
            + "// Line two\n"
            + "Hello Hello\n";

        MyLangLexer myLangLexer = new MyLangLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(myLangLexer);
        MyLangParser parser = new MyLangParser(tokens);
        ParseTree tree = parser.hellos();

        System.out.println("Children: " + tree.getChildCount() + ", parsed text: " + tree.getText());
    }
}
