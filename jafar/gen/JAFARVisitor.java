// Generated from C:/Users/Minh/Desktop/Maven Final Project/jafar/src/main/antlr4/finalproject/grammar\JAFAR.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JAFARParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JAFARVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JAFARParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(JAFARParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link JAFARParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(JAFARParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link JAFARParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(JAFARParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link JAFARParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(JAFARParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link JAFARParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(JAFARParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assStat}
	 * labeled alternative in {@link JAFARParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssStat(JAFARParser.AssStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link JAFARParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(JAFARParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link JAFARParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(JAFARParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link JAFARParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStat(JAFARParser.BlockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code threadStat}
	 * labeled alternative in {@link JAFARParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThreadStat(JAFARParser.ThreadStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lockStat}
	 * labeled alternative in {@link JAFARParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockStat(JAFARParser.LockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unlockStat}
	 * labeled alternative in {@link JAFARParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnlockStat(JAFARParser.UnlockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idTarget}
	 * labeled alternative in {@link JAFARParser#target}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdTarget(JAFARParser.IdTargetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link JAFARParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpr(JAFARParser.ParExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link JAFARParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueExpr(JAFARParser.TrueExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link JAFARParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(JAFARParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prfExpr}
	 * labeled alternative in {@link JAFARParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrfExpr(JAFARParser.PrfExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link JAFARParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseExpr(JAFARParser.FalseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link JAFARParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(JAFARParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link JAFARParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(JAFARParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numExpr}
	 * labeled alternative in {@link JAFARParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumExpr(JAFARParser.NumExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusExpr}
	 * labeled alternative in {@link JAFARParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusExpr(JAFARParser.PlusExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link JAFARParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(JAFARParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JAFARParser#prfOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrfOp(JAFARParser.PrfOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link JAFARParser#multOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultOp(JAFARParser.MultOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link JAFARParser#plusOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusOp(JAFARParser.PlusOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link JAFARParser#boolOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOp(JAFARParser.BoolOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link JAFARParser#compOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompOp(JAFARParser.CompOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link JAFARParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(JAFARParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link JAFARParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolType(JAFARParser.BoolTypeContext ctx);
}