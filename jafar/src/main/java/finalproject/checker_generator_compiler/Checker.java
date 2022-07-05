package finalproject.checker_generator_compiler;

import finalproject.exception.ParseException;
import finalproject.grammar.JAFARBaseListener;
import finalproject.grammar.JAFARLexer;
import finalproject.grammar.JAFARParser;
import finalproject.grammar.JAFARParser.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Class to type check and calculate flow entries and variable offsets. */
public class Checker extends JAFARBaseListener {
	/** Result of the latest call of {@link Result}. */
	private Result result;
	/** Variable scope for the latest call of {@link SymbolTable}. */
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

	@Override public void exitProgram(JAFARParser.ProgramContext ctx) {
		this.result.setMaxOffset(this.symbolTable.getMaxOffSet());
	}

	@Override public void enterFuncDecl(JAFARParser.FuncDeclContext ctx) {
		String funcID = ctx.ID().getText();
		int typeToken = ctx.type().getStart().getType();
		Type returnType = null;
		switch (typeToken) {
			case JAFARLexer.BOOLEAN:
				returnType = Type.BOOL;
				break;
			case JAFARLexer.INTEGER:
				returnType = Type.INT;
				break;
			default:
				returnType = Type.VOID;
		}

		boolean fresh = this.symbolTable.put(funcID, returnType);
		if (!fresh) {
			addError(ctx,"Function ’%s ’ already defined in this scope", funcID);
		}
		if (ctx.params() == null) {
			this.symbolTable.setParamType(funcID, new ArrayList<>());
		}
		this.symbolTable.openScope();
		this.symbolTable.setStartFuncName(funcID);
	}

	@Override public void exitParamsDecl(JAFARParser.ParamsDeclContext ctx) {
		FuncDeclContext func = (FuncDeclContext) ctx.getParent();
		ArrayList<Type> paramsType = new ArrayList<>();
		String funcID = func.ID().getText();

		for (VarContext param : ctx.var()) {
			Type parType = getType(param.type());
			for (int i = 0; i < param.ID().size(); i++) {
				paramsType.add(parType);
			}
		}

		this.setParams(funcID, paramsType);
		this.symbolTable.setParamType(funcID, paramsType);
	}


	@Override public void exitFuncDecl(JAFARParser.FuncDeclContext ctx) {
		String funcID = ctx.ID().getText();
		Type returnType = this.getType(ctx.type());

		if ((returnType.equals(Type.VOID)) &&(ctx.expr() != null)) {
			addError(ctx,"Function ’%s ’ of type VOID shoudln't return value", funcID);
		}
		if ((!(returnType.equals(Type.VOID))) && (ctx.expr() == null)) {
			addError(ctx,"Function ’%s’ expects return value", funcID);
		} else if ((!(returnType.equals(Type.VOID))) && (!getType(ctx.expr()).equals(returnType))) {
			addError(ctx,"Function ’%s’ expected return type '%s' but actual is '%s'", funcID, returnType, getType(ctx.expr()));
		}


		this.result.setFuncOffSetData(funcID, this.symbolTable.getScopeOffsetInfo());
		this.result.setFuncTypeData(funcID, this.symbolTable.getScopeTypeInfo());
		HashMap<String, Integer> check = this.symbolTable.getScopeOffsetInfo();
		System.out.println("CHECKER: Func " + funcID + "scope info" + check);
		this.symbolTable.closeScope();
		this.symbolTable.setStartFuncName(null);
		setType(ctx,returnType);
		this.symbolTable.setFuncType(funcID, returnType);
	}

	@Override public void exitFuncStat(JAFARParser.FuncStatContext ctx) {
		String funcID = ctx.ID().getText();
		Type returnType = this.symbolTable.type(funcID);
		setType(ctx, returnType);

		if (returnType != Type.VOID) {
			addError(ctx, "Cannot call function with return type ’%s ’ without assignment", returnType);
		} else {
			ArrayList<Type> funcParams = this.symbolTable.paramsType(funcID);
			if (funcParams.size() != ctx.expr().size()) {
				addError(ctx, "Function ’%s ’ is called with NOT matched number of parameters", funcID);
			} else {
				for (int i = 0; i < funcParams.size(); i++) {
					Type expectedParamType = funcParams.get(i);
					checkType(ctx.expr(i), expectedParamType);
				}
			}
		}
	}

	@Override public void exitFuncExpr(JAFARParser.FuncExprContext ctx) {
		String funcID = ctx.ID().getText();
		Type returnType = this.symbolTable.type(funcID);
		setType(ctx, returnType);
		ArrayList<Type> funcParams = this.symbolTable.paramsType(funcID);
		if ((funcParams != null) && (ctx.expr() != null)) {
			if (funcParams.size() != ctx.expr().size()) {
				addError(ctx, "Function ’%s' is called with NOT matched number of parameters", funcID);
			} else {
				for (int i = 0; i < funcParams.size(); i++) {
					Type expectedParamType = funcParams.get(i);
					checkType(ctx.expr(i), expectedParamType);
				}
			}
		}

	}

	@Override public void exitAssStat(JAFARParser.AssStatContext ctx) {
		Type type = this.result.getType(ctx.target());
		checkType(ctx.expr() , type);
	}

	@Override public void enterBlock(JAFARParser.BlockContext ctx) {
		this.symbolTable.openScope();
	}

	@Override public void exitBlock(JAFARParser.BlockContext ctx) {
		this.symbolTable.closeScope();
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		int threadId = 0;
		if (ctx.parent != null) {
			int t = result.getThreadId(ctx.parent);
			threadId = t;
		}
		result.setThreadId(ctx, threadId);
	}

	@Override
	public void enterThreadBlock(ThreadBlockContext ctx) {
		int newThreadId = result.createNewThread();
		int parentId = result.getThreadId(ctx.parent);
		result.setParentID(newThreadId, parentId);
		result.setThreadId(ctx, newThreadId);
	}


	@Override public void exitArrayID(JAFARParser.ArrayIDContext ctx) {
		String id = ctx.ID().getText();
		Type arrayType = this.symbolTable.type(id);
		setType(ctx, arrayType);
	}



	@Override public void exitIdTarget(JAFARParser.IdTargetContext ctx) {
		String id = ctx.ID().getText();
		Type type = this.symbolTable.type(id);
		if (type == null) {
			type = result.getSharedType(id);
			if (type == null) {
				addError(ctx, " Variable ’%s ’ not declared ", id);
				return;
			}
			int offset = result.getSharedOffset(id);
			setType(ctx, type);
			setOffset(ctx, offset);
		} else {
			if (result.getThreadId(ctx) > 0) {
				addError(ctx, "Variable '%s' is not shared but accessed inside thread", id);
			}
			setType(ctx, type);
			setOffset(ctx, this.symbolTable.getOffSet(id));
		}
	}

	@Override public void exitIfStat(JAFARParser.IfStatContext ctx) {
		checkType(ctx.expr(), Type.BOOL);
	}

	@Override public void exitBoolType(JAFARParser.BoolTypeContext ctx) {
		setType(ctx, Type.BOOL);
	}

	@Override public void exitIntType(JAFARParser.IntTypeContext ctx) {
		setType(ctx , Type.INT);
	}

	@Override public void exitVoidType(JAFARParser.VoidTypeContext ctx) {
		setType(ctx , Type.VOID);
	}

	@Override public void exitIntegerArrayType(JAFARParser.IntegerArrayTypeContext ctx) {
		Type elemType = Type.INT;
		Type arrayType = null;
		for (int i = ctx.NUM().size()-1; i >= 0; i--) {
			int numElems = Integer.parseInt(ctx.NUM().get(i).getText());
			arrayType = new Type.Array(numElems, elemType);
			elemType = arrayType;
		}
		setType(ctx, arrayType);
	}

	@Override public void exitBooleanArrayType(JAFARParser.BooleanArrayTypeContext ctx) {
		Type elemType = Type.BOOL;
		Type arrayType = null;
		for (int i = ctx.NUM().size()-1; i >= 0; i--) {
			int numElems = Integer.parseInt(ctx.NUM().get(i).getText());
			arrayType = new Type.Array(numElems, elemType);
			elemType = arrayType;
		}
		setType(ctx, arrayType);
	}

	@Override public void exitVar(JAFARParser.VarContext ctx) {
		Type type = getType(ctx.type());
		for ( TerminalNode idNode : ctx.ID()) {
			String id = idNode.getText();
			if (result.isShared(id)) {
				addError(idNode.getSymbol(),"Variable ’%s ’ already defined as shared variable -- exitVar", id);
				return;
			}
			boolean fresh = this.symbolTable.put(id, type);
			System.out.println("ID: " + id + " " + type );
			if (!fresh) {
				addError(idNode.getSymbol(),"Variable ’%s ’ already defined in this scope -- exitVar", id);
				return;
			}
		}
		setType(ctx, type);
	}

	@Override
	public void exitSharedDecl(SharedDeclContext ctx) {
		Type type = getType(ctx.type());
		TerminalNode idNode = ctx.ID();
		String id = idNode.getText();
		boolean fresh = this.result.putSharedVar(id, type);
		System.out.println(id + " " + type + " " + this.result.getSharedOffset(id));
		if (!fresh) {
			addError(idNode.getSymbol(),"Variable ’%s ’ already defined as shared variable", id);
			return;
		}
		if (symbolTable.isDeclared(id)) {
			addError(idNode.getSymbol(),"Variable ’%s ’ already defined in this scope", id);
			return;
		}
	}

	@Override public void exitWhileStat(JAFARParser.WhileStatContext ctx) {
		checkType(ctx.expr(), Type.BOOL);
	}

	// Override the listener methods for the statement nodes

	@Override
	public void exitIdExpr(IdExprContext ctx) {
		String id = ctx.ID().getText();
		Type type = this.symbolTable.type(id);
		if (type == null) {
			type = result.getSharedType(id);
			if (type == null) {
				addError(ctx, "Variable '%s' not declared", id);
				return;
			}
			int offset = result.getSharedOffset(id);
			setType(ctx, type);
			setOffset(ctx, offset);
		} else {
			if (result.getThreadId(ctx) > 0) {
				addError(ctx, "Variable '%s' is not shared but accessed inside thread", id);
			}
			setType(ctx, type);
			setOffset(ctx, this.symbolTable.getOffSet(id));
		}
	}

	@Override public void exitArrayTarget(JAFARParser.ArrayTargetContext ctx) {
		String id = ctx.arrayID().ID().getText();
		for (ExprContext index : ctx.expr()) {
			Type indexType = getType(index);
			if (!indexType.equals(Type.INT)) {
				addError(index, "Index is not of type INT");
			}
		}

		Type arrayType = this.symbolTable.type(id);
		if (arrayType == null) {
			arrayType = result.getSharedType(id);
			if (arrayType == null) {
				addError(ctx, " Array ’%s ’ not declared ", id);
				return;
			}
			if (ctx.expr().size() > ((Type.Array) arrayType).getNumDimens()) {
				addError(ctx, "Array '%s' is accessed with higher dimension than declared", id);
				return;
			}
			Type exprType = arrayType;
			for (ExprContext dimension: ctx.expr()) {
				exprType = ((Type.Array) exprType).getElemType();
			}
			int offset = result.getSharedOffset(id);
			setType(ctx, exprType);
			setOffset(ctx, offset);
		} else {
			if (result.getThreadId(ctx) > 0) {
				addError(ctx, "Array '%s' is not shared but accessed inside thread", id);
			}
			if (ctx.expr().size() > ((Type.Array) arrayType).getNumDimens()) {
				addError(ctx, "Array '%s' is accessed with higher dimension than declared", id);
				return;
			}
			Type exprType = arrayType;
			for (ExprContext dimension: ctx.expr()) {
				exprType = ((Type.Array) exprType).getElemType();
			}
			setType(ctx, exprType);
			setOffset(ctx, this.symbolTable.getOffSet(id));
		}
	}

	@Override public void exitIndexExpr(JAFARParser.IndexExprContext ctx) {
		for (ExprContext elemIndex : ctx.expr()) {
			Type indexType = getType(elemIndex);
			if (!indexType.equals(Type.INT)) {
				addError(ctx, "Index is not of type INT");
			}
		}
		String arrayID = ctx.arrayID().ID().getText();
		Type arrayType = this.symbolTable.type(arrayID);
		if (arrayType == null) {
			arrayType = result.getSharedType(arrayID);
			if (arrayType == null) {
				addError(ctx, "Array '%s' not declared", arrayID);
				return;
			}
			if (ctx.expr().size() > ((Type.Array) arrayType).getNumDimens()) {
				addError(ctx, "Array '%s' is accessed with higher dimension than declared", arrayID);
				return;
			}
			Type exprType = arrayType;
			for (ExprContext dimension: ctx.expr()) {
				exprType = ((Type.Array) exprType).getElemType();
			}

			int offset = result.getSharedOffset(arrayID);
			setType(ctx, exprType);
			setOffset(ctx, offset);
		} else {
			if (result.getThreadId(ctx) > 0) {
				addError(ctx, "Array '%s' is not shared but accessed inside thread", arrayID);
			}
			if (ctx.expr().size() > ((Type.Array) arrayType).getNumDimens()) {
				addError(ctx, "Array '%s' is accessed with higher dimension than declared", arrayID);
				return;
			}
			Type exprType = arrayType;
			for (ExprContext dimension: ctx.expr()) {
				exprType = ((Type.Array) exprType).getElemType();
			}
			setType(ctx, exprType);
			System.out.println("CHECKER array type :" + exprType.toString());
			setOffset(ctx, this.symbolTable.getOffSet(arrayID));
		}
	}

	@Override public void exitArrayExpr(JAFARParser.ArrayExprContext ctx) {
		Type expectedElemType = getType(ctx.expr(0));
		for (ExprContext arrayElem : ctx.expr()) {
			Type actualType = getType(arrayElem);
			if (!getType(arrayElem).equals(expectedElemType)) {
				addError(ctx, "Array is declared with elements are not of the same type");
			};
		}
		setType(ctx, new Type.Array(ctx.expr().size(), expectedElemType));
	}

	@Override
	public void exitBoolExpr(JAFARParser.BoolExprContext ctx) {
		checkType(ctx.expr(0), Type.BOOL);
		checkType(ctx.expr(1), Type.BOOL);
		setType(ctx, Type.BOOL);
	}

	@Override
	public void exitCompExpr(CompExprContext ctx) {
		// case NE and EQ is for all type
		if ((ctx.compOp().getStart().getType() == JAFARLexer.EQ) || (ctx.compOp().getStart().getType() == JAFARLexer.NE)) {
			Type leftType = getType(ctx.expr(0));
			Type rightType = getType(ctx.expr(1));
			if ((leftType instanceof Type.Array) && (rightType instanceof Type.Array)) {
				while (((Type.Array) leftType).getElemType() instanceof  Type.Array) {
					leftType = ((Type.Array) leftType).getElemType();
				}
				while (((Type.Array) rightType).getElemType() instanceof  Type.Array) {
					rightType = ((Type.Array) rightType).getElemType();
				}
				if (!leftType.equals(rightType)) {
					addError(ctx, "Array of "+ leftType+" cannot compare with Array of" + rightType);
				}
			} else {
				if (!leftType.equals(rightType)) {
					addError(ctx, leftType+" cannot compare with " + rightType);
				}
			}
		} else { // other compOp only available for INT type
			checkType(ctx.expr(0), Type.INT);
			checkType(ctx.expr(1), Type.INT);
		}
		setType(ctx, Type.BOOL);
	}

	@Override
	public void exitFalseExpr(FalseExprContext ctx) {
		setType(ctx, Type.BOOL);
	}



	@Override
	public void exitMultExpr(MultExprContext ctx) {
		checkType(ctx.expr(0), Type.INT);
		checkType(ctx.expr(1), Type.INT);
		setType(ctx, Type.INT);
	}

	@Override
	public void exitNumExpr(NumExprContext ctx) {
		setType(ctx, Type.INT);
	}

	@Override
	public void exitParExpr(ParExprContext ctx) {
		setType(ctx, getType(ctx.expr()));
	}

	@Override
	public void exitPlusExpr(PlusExprContext ctx) {
		checkType(ctx.expr(0), Type.INT);
		checkType(ctx.expr(1), Type.INT);
		setType(ctx, Type.INT);
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
	}

	@Override
	public void exitTrueExpr(TrueExprContext ctx) {
		setType(ctx, Type.BOOL);
	}

	/** Convenience method to add an offset to the result. */
	private void setOffset(ParseTree node, Integer offset) {
		this.result.setOffset(node, offset);
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
		}

		if (!actual.equals(expected)) {
			addError(node, "Expected type '%s' but found '%s'", expected,
					actual);
		}
	}

	/** Records an error at a given parse tree node.
	 * @param node the parse tree node at which the error occurred
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

	/** Convenience method to add an params list to the result. */
	private void setParams(String funcID, ArrayList<Type> paramsType) {
		this.result.setParams(funcID, paramsType);
	}


	/** Convenience method to add a type to the result. */
	private void setType(ParseTree node, Type type) {
		this.result.setType(node, type);
	}

	/** Returns the type of a given expression or type node. */
	private Type getType(ParseTree node) {
		return this.result.getType(node);
	}
}
