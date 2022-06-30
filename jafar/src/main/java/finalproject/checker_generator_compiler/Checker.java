package finalproject.checker_generator_compiler;

import finalproject.exception.ParseException;
import finalproject.grammar.JAFARBaseListener;
import finalproject.grammar.JAFARParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import finalproject.grammar.JAFARParser.*;

import java.util.ArrayList;
import java.util.List;

/** Class to type check and calculate flow entries and variable offsets. */
public class Checker extends JAFARBaseListener {
	/** Result of the latest call of {@link #check}. */
	private Result result;
	/** Variable scope for the latest call of {@link #check}. */
	private SymbolTable symbolTable;
	/** List of errors collected in the latest call of {@link #check}. */
	private List<String> errors;

	/** Runs this checker on a given parse tree,
	 * and returns the checker result.
	 * @throws ParseException if an error was found during checking.
	 */
	public Result check(ParseTree tree) throws ParseException {
		this.symbolTable = new SymbolTable();
		this.result = new Result();
		this.errors = new ArrayList<>();
		new ParseTreeWalker().walk(this, tree);
		if (hasErrors()) {
			throw new ParseException(getErrors());
		}
		return this.result;
	}

//TODO my implementation
	@Override public void exitAssStat(JAFARParser.AssStatContext ctx) {
		Type type = this.result.getType(ctx.target());
		checkType(ctx.expr() , type);
		setEntry(ctx, entry(ctx.expr()));
	}

	@Override public void enterBlock(JAFARParser.BlockContext ctx) {
		this.symbolTable.openScope();
	}

	@Override public void exitBlock(JAFARParser.BlockContext ctx) {
		setEntry(ctx, entry(ctx.stat(0)));
		this.symbolTable.closeScope();
	}

	@Override public void exitBlockStat(JAFARParser.BlockStatContext ctx) {
		setEntry(ctx, entry(ctx.block()));
	}

	@Override public void exitBoolType(JAFARParser.BoolTypeContext ctx) {
		setType(ctx, Type.BOOL);
	}

	@Override public void exitIdTarget(JAFARParser.IdTargetContext ctx) {
		String id = ctx.ID().getText();
		Type type = this.symbolTable.type(id);
		if (type == null) {
			addError(ctx, " Variable ’%s ’ not declared " , id);
		} else {
			setType(ctx, type);
			setOffset(ctx, this.symbolTable.offset(id));
		}
	}

	@Override public void exitIfStat(JAFARParser.IfStatContext ctx) {
		checkType(ctx.expr(), Type.BOOL);
		setEntry(ctx, entry(ctx.expr()));
	}

	@Override public void exitIntType(JAFARParser.IntTypeContext ctx) {
		setType(ctx , Type.INT);
	}

	@Override public void exitVar(JAFARParser.VarContext ctx) {
		Type type = getType(ctx.type());
		for ( TerminalNode idNode : ctx.ID()) {
			String id = idNode.getText();
			boolean fresh = this.symbolTable.put(id, type);
			if (!fresh) {
				addError(idNode.getSymbol(),"Variable ’%s ’ already defined in this scope", id);
			}
		}
	}

	@Override public void exitWhileStat(JAFARParser.WhileStatContext ctx) {
		checkType(ctx.expr(), Type.BOOL);
		setEntry(ctx, entry(ctx.expr()));
	}

	// Override the listener methods for the statement nodes
	@Override
	public void exitBoolExpr(JAFARParser.BoolExprContext ctx) {
		checkType(ctx.expr(0), Type.BOOL);
		checkType(ctx.expr(1), Type.BOOL);
		setType(ctx, Type.BOOL);
		setEntry(ctx, entry(ctx.expr(0)));
	}

	@Override
	public void exitCompExpr(CompExprContext ctx) {
		checkType(ctx.expr(0), Type.INT);
		checkType(ctx.expr(1), Type.INT);
		setType(ctx, Type.BOOL);
		setEntry(ctx, entry(ctx.expr(0)));
	}

	@Override
	public void exitFalseExpr(FalseExprContext ctx) {
		setType(ctx, Type.BOOL);
		setEntry(ctx, ctx);
	}

	@Override
	public void exitIdExpr(IdExprContext ctx) {
		String id = ctx.ID().getText();
		Type type = this.symbolTable.type(id);
		if (type == null) {
			addError(ctx, "Variable '%s' not declared", id);
		} else {
			setType(ctx, type);
			setOffset(ctx, this.symbolTable.offset(id));
			setEntry(ctx, ctx);
		}
	}

	@Override
	public void exitMultExpr(MultExprContext ctx) {
		checkType(ctx.expr(0), Type.INT);
		checkType(ctx.expr(1), Type.INT);
		setType(ctx, Type.INT);
		setEntry(ctx, entry(ctx.expr(0)));
	}

	@Override
	public void exitNumExpr(NumExprContext ctx) {
		setType(ctx, Type.INT);
		setEntry(ctx, ctx);
	}

	@Override
	public void exitParExpr(ParExprContext ctx) {
		setType(ctx, getType(ctx.expr()));
		setEntry(ctx, entry(ctx.expr()));
	}

	@Override
	public void exitPlusExpr(PlusExprContext ctx) {
		checkType(ctx.expr(0), Type.INT);
		checkType(ctx.expr(1), Type.INT);
		setType(ctx, Type.INT);
		setEntry(ctx, entry(ctx.expr(0)));
	}

	@Override
	public void exitPrfExpr(PrfExprContext ctx) {
		Type type;
		if (ctx.prfOp().MINUS() != null) {
			type = Type.INT;
		} else {
			assert ctx.prfOp().NOT() != null;
			type = Type.BOOL;
		}
		checkType(ctx.expr(), type);
		setType(ctx, type);
		setEntry(ctx, entry(ctx.expr()));
	}

	@Override
	public void exitTrueExpr(TrueExprContext ctx) {
		setType(ctx, Type.BOOL);
		setEntry(ctx, ctx);
	}

	/** Indicates if any errors were encountered in this tree listener. */
	public boolean hasErrors() {
		return !getErrors().isEmpty();
	}

	/** Returns the list of errors collected in this tree listener. */
	public List<String> getErrors() {
		return this.errors;
	}

	/** Checks the inferred type of a given parse tree,
	 * and adds an error if it does not correspond to the expected type.
	 */
	private void checkType(ParserRuleContext node, Type expected) {
		Type actual = getType(node);
		if (actual == null) {
			return;
//			TODO throw new IllegalArgumentException("Missing inferred type of " + node.getText());
		}

		if (!actual.equals(expected)) {
			addError(node, "Expected type '%s' but found '%s'", expected,
					actual);
		}
	}

	/** Records an error at a given parse tree node.
	 * @param ctx the parse tree node at which the error occurred
	 * @param message the error message
	 * @param args arguments for the message, see {@link String#format}
	 */
	private void addError(ParserRuleContext node, String message,
			Object... args) {
		addError(node.getStart(), message, args);
	}

	/** Records an error at a given token.
	 * @param token the token at which the error occurred
	 * @param message the error message
	 * @param args arguments for the message, see {@link String#format}
	 */
	private void addError(Token token, String message, Object... args) {
		int line = token.getLine();
		int column = token.getCharPositionInLine();
		message = String.format(message, args);
		message = String.format("Line %d:%d - %s", line, column, message);
		this.errors.add(message);
	}

	/** Convenience method to add an offset to the result. */
	private void setOffset(ParseTree node, Integer offset) {
		this.result.setOffset(node, offset);
	}

	/** Convenience method to add a type to the result. */
	private void setType(ParseTree node, Type type) {
		this.result.setType(node, type);
	}

	/** Returns the type of a given expression or type node. */
	private Type getType(ParseTree node) {
		return this.result.getType(node);
	}

	/** Convenience method to add a flow graph entry to the result. */
	private void setEntry(ParseTree node, ParserRuleContext entry) {
		if (entry == null) {
			return;
//			TODO throw new IllegalArgumentException("Null flow graph entry");
		}
		this.result.setEntry(node, entry);
	}

	/** Returns the flow graph entry of a given expression or statement. */
	private ParserRuleContext entry(ParseTree node) {
		return this.result.getEntry(node);
	}
}
