package finalproject.syntax;
import finalproject.exception.ErrorListener;
import finalproject.exception.ParseException;
import finalproject.grammar.JAFARLexer;
import finalproject.grammar.JAFARParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Objects;
public class SyntaxTest {
    @Test
    public void commentTest(){
        String bigstring = " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque nisl eros, " +
                "pulvinar facilisis justo mollis, auctor consequat urna. Morbi a bibendum metus. " +
                "Donec scelerisque sollicitudin enim eu venenatis. Duis tincidunt laoreet ex, " +
                "in pretium orci vestibulum eget. Class aptent taciti sociosqu ad litora torquent" +
                "per conubia nostra, per inceptos himenaeos. Duis pharetra luctus lacus ut " +
                "vestibulum. Maecenas ipsum lacus, lacinia quis posuere ut, pulvinar vitae dolor." +
                "Integer eu nibh at nisi ullamcorper sagittis id vel leo. Integer feugiat " +
                "faucibus libero, at maximus nisl suscipit posuere. Morbi nec enim nunc. " +
                "Phasellus bibendum turpis ut ipsum egestas, sed sollicitudin elit convallis. " +
                "Cras pharetra mi tristique sapien vestibulum lobortis. Nam eget bibendum metus," +
                "non dictum mauris. Nulla at tellus sagittis, viverra est a, bibendum metus.";
        pass("{x:=x+1; //this is a comment \n}");
        fails("{x:=x+1; //this is a comment }","line 1:9 - extraneous input '/' expecting {BEGIN, END, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}");
        pass("{x:=x+1; //this is a very long message"+bigstring +"\n }");
        fails( "{x:=x+1; /this is a comment}","line 1:9 - extraneous input '/' expecting {BEGIN, END, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}");
    }
    @Test
    public void semicolondeclTest() throws ParseException {
        pass("shared int val;{x:=x+1;}");
        pass("int val; {val:=0;}");
        fails("int val; {val:=0;;}","line 1:17 - extraneous input ';' expecting {BEGIN, END, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}");
        fails("int val? {val:=0;}","line 1:7 - token recognition error at: '?'line 1:9 - missing ';' at '{'");
        fails("int val {val:=0;}","line 1:8 - missing ';' at '{'");
        assertEquals( "int", Objects.requireNonNull(checkSyntax("int val;{val:=0;}")).getChild(0).getChild(0).getChild(0).getText());
        pass("bool val;{val:=True;}");
        fails("bool val!{val:=True;}","line 1:8 - mismatched input '!' expecting {',', ';'}");
        fails("bool val {val:=True;}","line 1:9 - missing ';' at '{'");
        assertEquals( "bool", Objects.requireNonNull(checkSyntax("bool val;{val:=True;}")).getChild(0).getChild(0).getChild(0).getText());
        fails("bool;{val := 0;}","line 1:4 - missing ID at ';'");
    }
    @Test
    public void parenthesisTest()  {
        pass("{lock();}");
        fails("{lock;}","line 1:5 - mismatched input ';' expecting '('");
        pass("{unlock();}");
        fails("{unlock;}","line 1:7 - mismatched input ';' expecting '('");
        pass("{print(x);}");
        pass("{print(23);}");
        fails("{print x;}","line 1:7 - missing '(' at 'x'line 1:8 - missing ')' at ';'");
        pass("{add(4,3);}");
        fails("{add 4,3;}","line 1:5 - no viable alternative at input 'add4'");
        pass("func add(int x,y):int {return (x+y);} {int x; x:= add(3,5);}");
        fails("func add int x,y:int {return (x+y);} {int x; x:= add(3,5);}","line 1:9 - missing '(' at 'int'line 1:16 - missing ')' at ':'");

    }
    @Test
    public void typingErrorTest() throws ParseException {
        fails("{a===b;}","line 1:4 - token recognition error at: '=b'line 1:2 - no viable alternative at input 'a=='");
        fails("{a>>b;}","line 1:2 - no viable alternative at input 'a>'");
        fails("funv add(int x,y):int {return (x+y);} {int x; x:= add(3,5);}","line 1:0 - mismatched input 'funv' expecting {BEGIN, BOOLEAN, INTEGER, VOID, SHARED, FUNC}");
        fails("{(a:=a+3;}","line 1:1 - extraneous input '(' expecting {BEGIN, BOOLEAN, INTEGER, VOID, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}");
        fails("{i f(val>0):{val:=val+3;} else {val:=val+1;}}","line 1:3 - no viable alternative at input 'if'line 1:11 - mismatched input ':' expecting ';'line 1:26 - extraneous input 'else' expecting {BEGIN, END, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}");
        fails("{a=b;}","line 1:2 - token recognition error at: '=b'line 1:4 - no viable alternative at input 'a;'");
    }

    @Test
    public void statementTest() throws ParseException {
        pass("{x:=1;}");
        fails("{x=1;}","line 1:2 - token recognition error at: '=1'line 1:4 - no viable alternative at input 'x;'");
        pass("{if(val>0):{val:=val+3;} else {val:=val+1;}}");
        fails("{if(val>0){val:=val+3;} else {val:=val+1;}}}","line 1:10 - missing ':' at '{'");
        pass("{while(a == i+4): {i := i+2;}}");
        fails("{while(a == i+4) {i := i+2;}}","line 1:17 - missing ':' at '{'");
        pass("{{{i:=i+3;}}}");
        fails("{x:=1; int x;}","line 1:7 - extraneous input 'int' expecting {BEGIN, END, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}");
        fails("{{{i:=i+3;}}","line 1:12 - extraneous input '<EOF>' expecting {BEGIN, END, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}");
        pass("{parbegin:{i:=i+3;} {i:=i+2;} {i:=i+7;} parend;}");
        fails("{{x:=x+3;};{y:=y+5;}}","line 1:10 - extraneous input ';' expecting {BEGIN, END, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}");//semicolon after block
        assertEquals("val:=val+3;", Objects.requireNonNull(checkSyntax("{if(val>0):{val:=val+3;}}")).getChild(0).getChild(1).getChild(3).getChild(1).getText());
        assertEquals("val:=val+1;", Objects.requireNonNull(checkSyntax("{if(val>0):{val:=val+3;} else {val:=val+1;}}")).getChild(0).getChild(1).getChild(5).getChild(1).getText());
    }
    @Test
    public void functionTest() throws ParseException {
        pass("func add(int x,y):int {return (x+y);} {int x; x:= add(3,5);}");
        pass("shared int fact; func factorial(int x) : void {int n; n := 1; while(n<=x) : {fact :=fact*n; n:=n+1;} return;} { factorial(4); print(fact); }");
        fails("func add : void {return;} {int x; x:=1;}","line 1:9 - mismatched input ':' expecting '('line 1:17 - mismatched input 'return' expecting {BEGIN, BOOLEAN, INTEGER, VOID, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}"); // function should have paranthesis
        fails("func add : {return;} {int x; x:=1;}","line 1:9 - mismatched input ':' expecting '('line 1:12 - mismatched input 'return' expecting {BEGIN, BOOLEAN, INTEGER, VOID, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}"); // does not have return type
        fails("func add(int x,y):int {return (x+y); return (y+x);} {int x; x:= add(3,5);}","line 1:37 - mismatched input 'return' expecting {AND, OR, MOD, '==', '>=', '>', '<=', '<', '-', '!=', '+', ')', '/', '*'}");
        fails("func add : int {} {int x; x:=1;}","line 1:9 - mismatched input ':' expecting '('line 1:16 - extraneous input '}' expecting {BEGIN, BOOLEAN, INTEGER, VOID, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}line 1:32 - extraneous input '<EOF>' expecting {BEGIN, END, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}");
        assertEquals( "int",Objects.requireNonNull(checkSyntax("func add(int x,y):int {return (x+y);} {int x; x:= add(3,5);}")).getChild(0).getChild(6).getText());
    }
    @Test
    public void arrayTest() {
        pass("int[4][2] x; {x[1][1] := 4;}");
        pass("bool[3] y; {y[2]:=True;}");
        fails("int[] y","line 1:4 - missing NUM at ']'line 1:7 - mismatched input '<EOF>' expecting {'[', ID}");
        pass("int[1][2][3][4][5] x; {x[1][1][1][1][1]:=1;}");
        fails("int[4][2] x; {x[1][1] := {2};}","line 1:25 - extraneous input '{' expecting {FALSE, NOT, TRUE, '(', '-', '[', ID, NUM}line 1:27 - extraneous input '}' expecting ';'"); // NOT EXPR
    }


    private void fails(String s,String exp) {
        try {
            checkSyntax(s);
            assert false;
        } catch (ParseException e) {
            assert true;
            assertEquals(exp, e.print());
        }
    }

    private void pass(String s) {
        try {
            checkSyntax(s);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("input was not accepted");
            assert false;
        }
    }

    private ParseTree checkSyntax(String s) throws ParseException {
        CharStream stream = CharStreams.fromString(s);
        JAFARLexer lex = new JAFARLexer(stream);
        ErrorListener err = new ErrorListener();
        lex.removeErrorListeners();
        lex.addErrorListener(err);
        TokenStream token = new CommonTokenStream(lex);
        JAFARParser parser = new JAFARParser(token);
        parser.removeErrorListeners();
        parser.addErrorListener(err);
        ParseTree ptree = parser.body();
        if (err.hasErrors()) {
            throw new ParseException(err.getErrors());
        }
        return ptree;

    }
}
