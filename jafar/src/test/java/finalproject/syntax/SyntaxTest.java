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
    public void bracketTest(){
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
        pass("{x:=x+1; //this is a very long message"+bigstring +"\n }");
        fails("line 1:9 - extraneous input '/' expecting {BEGIN, END, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}", "{x:=x+1; /this is a comment}");
    }
    @Test
    public void semicolondeclTest() throws ParseException {
        pass("shared int val;{x:=x+1;}");
        pass("int val; {val:=0;}");
        fails("line 1:7 - token recognition error at: '?'line 1:9 - missing ';' at '{'","int val? {val:=0;}");
        fails("line 1:8 - missing ';' at '{'","int val {val:=0;}");
        assertEquals( "int", Objects.requireNonNull(checkSyntax("int val;{val:=0;}")).getChild(0).getChild(0).getChild(0).getText());
        pass("bool val;{val:=True;}");
        fails("line 1:8 - mismatched input '!' expecting {',', ';'}","bool val!{val:=True;}");
        fails("line 1:9 - missing ';' at '{'","bool val {val:=True;}");
        assertEquals( "bool", Objects.requireNonNull(checkSyntax("bool val;{val:=True;}")).getChild(0).getChild(0).getChild(0).getText());
        fails("line 1:4 - missing ID at ';'","bool;{val := 0;}");
    }
    @Test
    public void parenthesisTest() throws ParseException {
        pass("{lock();}");
        fails("line 1:5 - mismatched input ';' expecting '('","{lock;}");
        pass("{unlock();}");
        fails("line 1:7 - mismatched input ';' expecting '('","{unlock;}");
        pass("{print(x);}");
        fails("line 1:7 - missing '(' at 'x'line 1:8 - missing ')' at ';'","{print x;}");
        pass("{add(4,3);}");
        fails("line 1:5 - no viable alternative at input 'add4'","{add 4,3;}");
        pass("func add(int x,y):int {return (x+y);} {int x; x:= add(3,5);}");
        fails("line 1:9 - missing '(' at 'int'line 1:16 - missing ')' at ':'","func add int x,y:int {return (x+y);} {int x; x:= add(3,5);}");
        assertEquals( "int",Objects.requireNonNull(checkSyntax("func add(int x,y):int {return (x+y);} {int x; x:= add(3,5);}")).getChild(0).getChild(6).getText());

    }
    @Test
    public void statementTest() throws ParseException {
        pass("{x:=1;}");
        fails("line 1:2 - token recognition error at: '=1'line 1:4 - no viable alternative at input 'x;'","{x=1;}");
        pass("{if(val>0):{val:=val+3;} else {val:=val+1;}}");
        fails("line 1:10 - missing ':' at '{'","{if(val>0){val:=val+3;} else {val:=val+1;}}}");
        pass("{while(a == i+4): {i := i+2;}}");
        pass("{{{i:=i+3;}}}");
        fails("line 1:12 - extraneous input '<EOF>' expecting {BEGIN, END, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}","{{{i:=i+3;}}");
        pass("{parbegin:{i:=i+3;} {i:=i+2;} {i:=i+7;} parend;}");
        assertEquals("val:=val+3;", Objects.requireNonNull(checkSyntax("{if(val>0):{val:=val+3;}}")).getChild(0).getChild(1).getChild(3).getChild(1).getText());
        assertEquals("val:=val+1;", Objects.requireNonNull(checkSyntax("{if(val>0):{val:=val+3;} else {val:=val+1;}}")).getChild(0).getChild(1).getChild(5).getChild(1).getText());
    }
    @Test
    public void functionTest() {
        pass("func add(int x,y):int {return (x+y);} {int x; x:= add(3,5);}");
        pass("shared int fact; func factorial(int x) : void {int n; n := 1; while(n<=x) : {fact :=fact*n; n:=n+1;} return;} { factorial(4); print(fact); }");
        fails("line 1:9 - mismatched input ':' expecting '('line 1:17 - mismatched input 'return' expecting {BEGIN, BOOLEAN, INTEGER, VOID, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}","func add : void {return;} {int x; x:=1;}"); // function should have paranthesis
        fails("line 1:9 - mismatched input ':' expecting '('line 1:12 - mismatched input 'return' expecting {BEGIN, BOOLEAN, INTEGER, VOID, IF, PRINT, WHILE, PARBEGIN, LOCK, UNLOCK, ID}","func add : {return;} {int x; x:=1;}"); // does not have return type
    }
    @Test
    public void arrayTest() {
        pass("int[4][2] x; {x[1][1] := 4;}");
        pass("bool[3] y; {y[2]:=True;}");
        fails("line 1:4 - missing NUM at ']'line 1:7 - mismatched input '<EOF>' expecting {'[', ID}","int[] y");
        pass("int[1][2][3][4][5] x; {x[1][1][1][1][1]:=1;}");
        fails("line 1:25 - extraneous input '{' expecting {FALSE, NOT, TRUE, '(', '-', '[', ID, NUM}line 1:27 - extraneous input '}' expecting ';'","int[4][2] x; {x[1][1] := {2};}"); // NOT EXPR
    }


    private void fails(String exp,String s) {
        try {
            checkSyntax(s);
            assert false;
        } catch (ParseException e) {
            assert true;
            assertEquals(exp,e.print());
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
