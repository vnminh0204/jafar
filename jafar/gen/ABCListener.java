// Generated from /Users/andrea/Desktop/jafar/src/main/antlr4/ut/pp/parser/ABC.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ABCParser}.
 */
public interface ABCListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ABCParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ABCParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABCParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ABCParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABCParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(ABCParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABCParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(ABCParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABCParser#thread}.
	 * @param ctx the parse tree
	 */
	void enterThread(ABCParser.ThreadContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABCParser#thread}.
	 * @param ctx the parse tree
	 */
	void exitThread(ABCParser.ThreadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link ABCParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(ABCParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link ABCParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(ABCParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABCParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(ABCParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABCParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(ABCParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABCParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ABCParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABCParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ABCParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssStat(ABCParser.AssStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssStat(ABCParser.AssStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(ABCParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(ABCParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(ABCParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(ABCParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(ABCParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(ABCParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code threadStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterThreadStat(ABCParser.ThreadStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code threadStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitThreadStat(ABCParser.ThreadStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterInStat(ABCParser.InStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitInStat(ABCParser.InStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code outStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterOutStat(ABCParser.OutStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code outStat}
	 * labeled alternative in {@link ABCParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitOutStat(ABCParser.OutStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idTarget}
	 * labeled alternative in {@link ABCParser#target}.
	 * @param ctx the parse tree
	 */
	void enterIdTarget(ABCParser.IdTargetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idTarget}
	 * labeled alternative in {@link ABCParser#target}.
	 * @param ctx the parse tree
	 */
	void exitIdTarget(ABCParser.IdTargetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayTarget}
	 * labeled alternative in {@link ABCParser#target}.
	 * @param ctx the parse tree
	 */
	void enterArrayTarget(ABCParser.ArrayTargetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayTarget}
	 * labeled alternative in {@link ABCParser#target}.
	 * @param ctx the parse tree
	 */
	void exitArrayTarget(ABCParser.ArrayTargetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIndexExpr(ABCParser.IndexExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIndexExpr(ABCParser.IndexExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParExpr(ABCParser.ParExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParExpr(ABCParser.ParExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpr(ABCParser.ArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpr(ABCParser.ArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTrueExpr(ABCParser.TrueExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTrueExpr(ABCParser.TrueExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(ABCParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(ABCParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prfExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPrfExpr(ABCParser.PrfExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prfExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPrfExpr(ABCParser.PrfExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFalseExpr(ABCParser.FalseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFalseExpr(ABCParser.FalseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(ABCParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(ABCParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(ABCParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(ABCParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumExpr(ABCParser.NumExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumExpr(ABCParser.NumExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPlusExpr(ABCParser.PlusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPlusExpr(ABCParser.PlusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(ABCParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link ABCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(ABCParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABCParser#prfOp}.
	 * @param ctx the parse tree
	 */
	void enterPrfOp(ABCParser.PrfOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABCParser#prfOp}.
	 * @param ctx the parse tree
	 */
	void exitPrfOp(ABCParser.PrfOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABCParser#multOp}.
	 * @param ctx the parse tree
	 */
	void enterMultOp(ABCParser.MultOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABCParser#multOp}.
	 * @param ctx the parse tree
	 */
	void exitMultOp(ABCParser.MultOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABCParser#plusOp}.
	 * @param ctx the parse tree
	 */
	void enterPlusOp(ABCParser.PlusOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABCParser#plusOp}.
	 * @param ctx the parse tree
	 */
	void exitPlusOp(ABCParser.PlusOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABCParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void enterBoolOp(ABCParser.BoolOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABCParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void exitBoolOp(ABCParser.BoolOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABCParser#compOp}.
	 * @param ctx the parse tree
	 */
	void enterCompOp(ABCParser.CompOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABCParser#compOp}.
	 * @param ctx the parse tree
	 */
	void exitCompOp(ABCParser.CompOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link ABCParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(ABCParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link ABCParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(ABCParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link ABCParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(ABCParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link ABCParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(ABCParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link ABCParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(ABCParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link ABCParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(ABCParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ABCParser#bound}.
	 * @param ctx the parse tree
	 */
	void enterBound(ABCParser.BoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link ABCParser#bound}.
	 * @param ctx the parse tree
	 */
	void exitBound(ABCParser.BoundContext ctx);
}