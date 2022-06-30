package finalproject.tests;

import static org.junit.Assert.assertEquals;

import finalproject.parser.MyLangLexer;
import finalproject.parser.MyLangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

public class TestParser {
    @Test
    public void oneHello() {
        String input = "Hello";
        MyLangLexer myLangLexer = new MyLangLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(myLangLexer);
        MyLangParser parser = new MyLangParser(tokens);
        ParseTree tree = parser.hellos();
        assertEquals(2, tree.getChildCount()); // 1 for Hello, 1 for EOF
    }

    @Test
    public void helloNewlineHello() {
        String input = "Hello\nHello";
        MyLangLexer myLangLexer = new MyLangLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(myLangLexer);
        MyLangParser parser = new MyLangParser(tokens);
        ParseTree tree = parser.hellos();
        assertEquals(3, tree.getChildCount()); // 2 for Hello, 1 for EOF
    }

    @Test
    public void helloWorld() {
        // Fails by design, "World" is not allowed
        String input = "Hello World";
        MyLangLexer myLangLexer = new MyLangLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(myLangLexer);
        MyLangParser parser = new MyLangParser(tokens);
        ParseTree tree = parser.hellos();
        assertEquals(3, tree.getChildCount());
    }
}
