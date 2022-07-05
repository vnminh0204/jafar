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
//token test
public class SyntaxTest {

    @Test
    public void semicolondeclTest() throws ParseException {
            pass("shared int val;{x:=x+1;}");
            pass("int val; {val:=0;}");
            fails("int val? {val:=0;}");
            fails("int val {val:=0;}");
            assertEquals( "int", Objects.requireNonNull(checkSyntax("int val;{val:=0;}")).getChild(0).getChild(0).getChild(0).getText());
            pass("bool val;{val:=True;}");
            fails("bool val!{val:=True;}");
            fails("bool val {val:=True;}");
            assertEquals( "bool", Objects.requireNonNull(checkSyntax("bool val;{val:=True;}")).getChild(0).getChild(0).getChild(0).getText());
            fails("bool;{val := 0;}");
    }
    @Test
    public void parenthesisTest() throws ParseException {
            pass("{lock();}");
            fails("{lock;}");
            pass("{unlock();}");
            fails("{unlock;}");
            pass("{print(x);}");
            fails("{print x;}");
            pass("{add(4,3);}");
            fails("{add 4,3;}");
            pass("func add(int x,y):int {return (x+y);} {int x; x:= add(3,5);}");
            fails("func add int x,y:int {return (x+y);} {int x; x:= add(3,5);}");
            assertEquals( "int",Objects.requireNonNull(checkSyntax("func add(int x,y):int {return (x+y);} {int x; x:= add(3,5);}")).getChild(0).getChild(6).getText());

    }
    @Test
    public void statementTest() throws ParseException {
            pass("{x:=1;}");
            fails("{x=1;}");
            pass("{if(val>0):{val:=val+3;} else {val:=val+1;}}");
            fails("{if(val>0){val:=val+3;} else {val:=val+1;}}}");
            pass("{while(a == i+4): {i := i+2;}}");
            pass("{{{i:=i+3;}}}");
            fails("{{{i:=i+3}}");
            pass("{parbegin:{i:=i+3;} {i:=i+2;} {i:=i+7;} parend;}");
            assertEquals("val:=val+3;", Objects.requireNonNull(checkSyntax("{if(val>0):{val:=val+3;}}")).getChild(0).getChild(1).getChild(3).getChild(1).getText());
            assertEquals("val:=val+1;", Objects.requireNonNull(checkSyntax("{if(val>0):{val:=val+3;} else {val:=val+1;}}")).getChild(0).getChild(1).getChild(5).getChild(1).getText());
    }
    @Test
    public void functionTest() {
            pass("func add(int x,y):int {return (x+y);} {int x; x:= add(3,5);}");
            pass("shared int fact; func factorial(int x) : void {int n; n := 1; while(n<=x) : {fact :=fact*n; n:=n+1;} return;} { factorial(4); print(fact); }");
            fails("func add : void {return;} {int x; x:=1;}"); // function should have paranthesis
            fails("func add : {return;} {int x; x:=1;}"); // does not have return type
    }
    @Test
    public void arrayTest() {
            pass("int[4][2] x; {x[1][1] := 4;}");
            pass("bool[3] y; {y[2]:=True;}");
            fails("int[] y");
            pass("int[1][2][3][4][5] x; {x[1][1][1][1][1]:=1;}");
            fails("int[4][2] x; {x[1][1] := {2};}"); // NOT EXPR
    }


    private void fails(String s) {
        try {
            checkSyntax(s);
            assert false;
        } catch (ParseException e) {
            assert true;
            e.print();
        }
    }

    private void pass(String s) {
        try {
            checkSyntax(s);
        } catch (ParseException e) {
            //e.printStackTrace();
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
