package finalproject.codegeneration;

import finalproject.grammar.JAFARBaseVisitor;
import finalproject.grammar.JAFARLexer;
import finalproject.grammar.JAFARParser;
import finalproject.model.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static finalproject.model.Operator.Type.*;

/** Class to generate SPRIL code for Jafar. */
public class Generator extends JAFARBaseVisitor<Op> {

	/** The representation of the boolean value <code>false</code>. */
	public final static Address FALSE_VALUE = new Address(Address.Type.ImmValue, 0);
	/** The representation of the boolean value <code>true</code>. */
	public final static Address TRUE_VALUE = new Address(Address.Type.ImmValue, 1);

	/** Value to keep track of variable initialized inside that need to handle offset differently */
	private String currentFuncDecl = null;

	/** Keep track of declared function start line to jump to */
	public static HashMap<String, Integer> funcToStartLine = new HashMap<>();

	/** The outcome of the checker phase. */
	protected CheckerResult checkResult;


	/** Association of statement nodes to labels. */
	protected ParseTreeProperty<Label> labels;

	/** The program being built. */
	protected Program prog;

	/** List of all programs including spawn threads */
	protected ArrayList<Program> programs;

	/** Constant variables indicate at which address of Shared memory, the wait begin, wait end and wait lock start
	 * BEGIN : 0 -> 5
	 * END : 6 -> 11
	 * LOCK : 12 -> 16
	 * THEY ARE USED FOR CONCURRENCY IMPLEMENTATION.
	 * */
	private final int BEGINZONE = 0;
	private final int ENDZONE = 6;
	private final int LOCKZONE = 12;


	/** Generates SPRIL code for a given parse tree,
	 * given a pre-computed checker result.
	 */
	public Program generate(ParseTree tree, CheckerResult checkResult) {
		this.init(tree, checkResult);
		return this.prog;
	}

	/**
	 * Generates SPRIL code in form of multiple prog instance to support concurrency.
	 * @param tree : given parse tree
	 * @param checkResult : given pre-computed checker result
	 * @return : list of program instance.
	 */
	public ArrayList<Program> gen(ParseTree tree, CheckerResult checkResult) {
		this.init(tree, checkResult);
		return this.programs;
	}

	/**
	 * Initialize the program and navigate through the tree.
	 * @param tree given parse tree
	 * @param checkResult given check result
	 */
	public void init(ParseTree tree, CheckerResult checkResult) {
		this.programs = new ArrayList<>();
		for (int i = 0; i <= checkResult.getNoThreads(); i ++) {
			Program p = new Program(i);
			programs.add(p);
		}
		this.prog = new Program(0);
		this.programs.set(0, this.prog);
		this.checkResult = checkResult;
		this.labels = new ParseTreeProperty<>();
		tree.accept(this);
		for (Program p : this.programs) {
			String endProgLabel = p.getEndLabel();
			emit(p, endProgLabel, OpCode.EndProg);
			p.convert();
		}
	}

	@Override public Op visitIndexExpr(JAFARParser.IndexExprContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		String arrId = ctx.arrayID().ID().getText();
		Type.Array arrayType;
		if ((this.currentFuncDecl != null) && (this.checkResult.getFuncOffSetData(this.currentFuncDecl).containsKey(arrId))) {
			arrayType = (Type.Array) this.checkResult.getFuncTypeData(this.currentFuncDecl).get(arrId);

			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 0), new Reg(Reg.Type.regD)); //regD store final array index offset
			for (JAFARParser.ExprContext index :ctx.expr()) {
				visit(index);
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); // index value
				int elemSize = arrayType.getElemType().size();
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, elemSize), new Reg(Reg.Type.regB)); //regB store elemSize
				emit(pCtx, OpCode.Compute, new Operator(Mul), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regA), new Reg(Reg.Type.regD), new Reg(Reg.Type.regD));

				if (arrayType.getElemType() instanceof Type.Array) {
					arrayType = (Type.Array) arrayType.getElemType();
				}
			}

			LinkedHashMap<String, Integer> funcOffSetData = this.checkResult.getFuncOffSetData(this.currentFuncDecl);
			int idOffSetINFuncScope = funcOffSetData.get(arrId);
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, -funcOffSetData.size() + idOffSetINFuncScope), new Reg(Reg.Type.regB));
			emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regB)); //regB: func scope variable offset

			emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD), new Reg(Reg.Type.regD)); //regD will be offset in mem

			Type finalType = this.checkResult.getType(ctx);
			if (!(finalType instanceof  Type.Array)) {
				emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regD)), new Reg(Reg.Type.regD)); //return value instead of offset
			}
			emit(pCtx, OpCode.Push, new Reg(Reg.Type.regD));
		} else { //variable is in parent scope

			int arrStartOffSet;
			if (checkResult.isShared(arrId)) {
				arrayType = (Type.Array) this.checkResult.getSharedType(arrId);
				arrStartOffSet = this.checkResult.getSharedOffset(arrId);
			} else {
				arrayType = (Type.Array) this.checkResult.getType(ctx.arrayID());
				arrStartOffSet = this.checkResult.getOffset(ctx);
			}
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 0), new Reg(Reg.Type.regD)); //regD store final array index offset
			for (JAFARParser.ExprContext index :ctx.expr()) {
				visit(index);
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); // index value
				int elemSize = arrayType.getElemType().size();
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, elemSize), new Reg(Reg.Type.regB)); //regB store elemSize
				emit(pCtx, OpCode.Compute, new Operator(Mul), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regA), new Reg(Reg.Type.regD), new Reg(Reg.Type.regD));
				if (arrayType.getElemType() instanceof Type.Array) {
					arrayType = (Type.Array) arrayType.getElemType();
				}
			}
			if (checkResult.isShared(arrId)) {
				emit(pCtx, OpCode.ReadInstr, new Address(Address.Type.DirAddr, arrStartOffSet));
				emit(pCtx, OpCode.Receive, new Reg(Reg.Type.regA));
			} else {
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, arrStartOffSet), new Reg(Reg.Type.regA));
			}
			emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regA), new Reg(Reg.Type.regD), new Reg(Reg.Type.regD));
			Type finalType = this.checkResult.getType(ctx);
			if (!(finalType instanceof Type.Array)) {
				emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regD)), new Reg(Reg.Type.regD)); //return value instead of offset
			}
			emit(pCtx, OpCode.Push, new Reg(Reg.Type.regD));
		}
		return null;
	}

	@Override public Op visitArrayExpr(JAFARParser.ArrayExprContext ctx) {
		for (JAFARParser.ExprContext index : ctx.expr()) {
			visit(index);
		}
		return null;
	}

	@Override public Op visitIdExpr(JAFARParser.IdExprContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		String id = ctx.ID().getText();
		Type idType = this.checkResult.getType(ctx);
		if ((this.currentFuncDecl != null) && (this.checkResult.getFuncOffSetData(this.currentFuncDecl).containsKey(id))) {
			LinkedHashMap<String, Integer> funcOffSetData = this.checkResult.getFuncOffSetData(this.currentFuncDecl);
			int idOffSetINFuncScope = funcOffSetData.get(ctx.ID().getText());
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, -funcOffSetData.size()+idOffSetINFuncScope), new Reg(Reg.Type.regA));
			emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA)); //regB: param offset
			if (!(idType instanceof Type.Array)) {
				emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regA)), new Reg(Reg.Type.regA));
			}
			emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
		} else { //variable is in parent scope
			if (checkResult.isShared(id)) {
				int sharedMem = this.checkResult.getSharedOffset(ctx.ID().getText());
				if (!(idType instanceof Type.Array)) {
					emit(pCtx, OpCode.ReadInstr, new Address(Address.Type.DirAddr, sharedMem));
					emit(pCtx, OpCode.Receive, new Reg(Reg.Type.regA));
				} else {
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, sharedMem), new Reg(Reg.Type.regA));
				}
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
			} else {
				int idOffSet = this.checkResult.getOffset(ctx);
				if (!(idType instanceof Type.Array)) {
					emit(pCtx, OpCode.Load, new Address(Address.Type.DirAddr, idOffSet), new Reg(Reg.Type.regA));
				} else {
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, idOffSet), new Reg(Reg.Type.regA));
				}
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
			}
		}
		return null;
	}

	@Override public Op visitIdTarget(JAFARParser.IdTargetContext ctx) {
		//return offset of target
		Program pCtx = getProgramByNode(ctx);
		String targetID;
		targetID = ctx.ID().getText();
		if ((this.currentFuncDecl != null) && (this.checkResult.getFuncOffSetData(this.currentFuncDecl).containsKey(targetID))) {
			LinkedHashMap<String, Integer> funcOffSetData = this.checkResult.getFuncOffSetData(this.currentFuncDecl);
			int idOffSetINFuncScope = funcOffSetData.get(targetID);
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, -funcOffSetData.size() + idOffSetINFuncScope), new Reg(Reg.Type.regA));
			emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA)); //regA: param offset
		} else {
			int targetOffset;
			if (checkResult.isShared(targetID)) {
				targetOffset = this.checkResult.getSharedOffset(targetID);
			} else {
				targetOffset = this.checkResult.getOffset(ctx);
			}
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, targetOffset), new Reg(Reg.Type.regA));
		}
		emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}

	@Override public Op visitArrayTarget(JAFARParser.ArrayTargetContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		String arrId = ctx.arrayID().ID().getText();
		Type.Array arrayType;
		if ((this.currentFuncDecl != null) && (this.checkResult.getFuncOffSetData(this.currentFuncDecl).containsKey(arrId))) {
			arrayType = (Type.Array) this.checkResult.getFuncTypeData(this.currentFuncDecl).get(arrId);
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 0), new Reg(Reg.Type.regE)); //regE store final array index offset
			emit(pCtx, OpCode.Push, new Reg(Reg.Type.regE));
			for (JAFARParser.ExprContext index :ctx.expr()) {
				visit(index);
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); // index value
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regE)); // current sum index value in array
				int elemSize = arrayType.getElemType().size();
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, elemSize), new Reg(Reg.Type.regB)); //regB store elemSize
				emit(pCtx, OpCode.Compute, new Operator(Mul), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regA), new Reg(Reg.Type.regE), new Reg(Reg.Type.regE));
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regE));
				if (arrayType.getElemType() instanceof Type.Array) {
					arrayType = (Type.Array) arrayType.getElemType();
				}
			}

			LinkedHashMap<String, Integer> funcOffSetData = this.checkResult.getFuncOffSetData(this.currentFuncDecl);
			int idOffSetINFuncScope = funcOffSetData.get(arrId);
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, -funcOffSetData.size() + idOffSetINFuncScope), new Reg(Reg.Type.regB));
			emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regB)); //regB: func scope variable offset
			emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regE)); // final index value in array
			emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regE), new Reg(Reg.Type.regE)); //regE will be offset in mem

			emit(pCtx, OpCode.Push, new Reg(Reg.Type.regE));
		} else { //variable is in parent scope

			int arrStartOffSet;
			if (checkResult.isShared(arrId)) {
				arrayType = (Type.Array) this.checkResult.getSharedType(arrId);
				arrStartOffSet = this.checkResult.getSharedOffset(arrId);
			} else {
				arrayType = (Type.Array) this.checkResult.getType(ctx.arrayID());
				arrStartOffSet = this.checkResult.getOffset(ctx);
			}
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 0), new Reg(Reg.Type.regE)); //regE store final array index offset
			emit(pCtx, OpCode.Push, new Reg(Reg.Type.regE));
			for (JAFARParser.ExprContext index :ctx.expr()) {
				visit(index);
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); // index value
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regE)); // current sum index value in array
				int elemSize = arrayType.getElemType().size();
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, elemSize), new Reg(Reg.Type.regB)); //regB store elemSize
				emit(pCtx, OpCode.Compute, new Operator(Mul), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regA), new Reg(Reg.Type.regE), new Reg(Reg.Type.regE));
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regE));
				if (arrayType.getElemType() instanceof Type.Array) {
					arrayType = (Type.Array) arrayType.getElemType();
				}
			}
			if (checkResult.isShared(arrId)) {
				emit(pCtx, OpCode.ReadInstr, new Address(Address.Type.DirAddr, arrStartOffSet));
				emit(pCtx, OpCode.Receive, new Reg(Reg.Type.regA));
			} else {
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, arrStartOffSet), new Reg(Reg.Type.regA));
			}
			emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regE)); // final index value in array
			emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regA), new Reg(Reg.Type.regE), new Reg(Reg.Type.regE));
			emit(pCtx, OpCode.Push, new Reg(Reg.Type.regE));
		}
		return null;
	}

	public Op visitAssStat(JAFARParser.AssStatContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		String targetID;
		visit(ctx.target());
		JAFARParser.TargetContext target = ctx.target();

		JAFARParser.ExprContext assignExpr = ctx.expr();

		OpCode storeType;

		Type assignType = this.checkResult.getType(ctx.expr());

		if (target instanceof JAFARParser.IdTargetContext) {
			JAFARParser.IdTargetContext targetN = (JAFARParser.IdTargetContext) target;
			targetID = targetN.ID().getText();
		} else if (target instanceof JAFARParser.ArrayTargetContext) {
			JAFARParser.ArrayTargetContext targetNode = (JAFARParser.ArrayTargetContext) target;
			targetID = targetNode.arrayID().ID().getText();
		} else {
			targetID = "WRONG";
		}

		if ((this.currentFuncDecl != null) && (this.checkResult.getFuncOffSetData(this.currentFuncDecl).containsKey(targetID))) {
			storeType = OpCode.Store;
		} else {
			//variable is in parent scope
			if (checkResult.isShared(targetID)) {
				storeType = OpCode.WriteInstr;
			} else {
				storeType = OpCode.Store;
			}
		}

		if (((assignExpr instanceof JAFARParser.IndexExprContext) && (assignType instanceof Type.Array))
				|| ((assignExpr instanceof JAFARParser.IdExprContext) &&(assignType instanceof Type.Array)))
		{

			visit(ctx.expr());
			emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //regA store array[index]'s offset
			emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regD)); // target offset
			for (int i = assignType.size() - 1; i >= 0; i--) {
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, i), new Reg(Reg.Type.regB)); //regB will store iteration value
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA)); // update A(assign) offset based on inter value B
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD), new Reg(Reg.Type.regD)); // update E(target) offset based on inter value B
				if (checkResult.isShared(targetID)) {
					emit(pCtx, OpCode.ReadInstr, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regA)));
					emit(pCtx, OpCode.Receive, new Reg(Reg.Type.regC));
				} else {
					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regA)), new Reg(Reg.Type.regC)); //regC will store assign value at index (regA)
				}
				emit(pCtx, storeType, new Reg(Reg.Type.regC), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regD)));
				emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA)); // reset A(assign) offset based on inter value B
				emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regD), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD)); // reset E(target) offset based on inter value B
			}
		} else if ((assignExpr instanceof JAFARParser.ArrayExprContext) || ((assignExpr instanceof JAFARParser.FuncExprContext) &&(assignType instanceof Type.Array))) {
			visit(ctx.expr());
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, assignType.size()), new Reg(Reg.Type.regD));
			emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regD), new Reg(Reg.Type.regSP), new Reg(Reg.Type.regD));
			emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regD)), new Reg(Reg.Type.regD));
			for (int i = assignType.size()-1; i >=0; i--) {
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //regA store array[index]'s value
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, i), new Reg(Reg.Type.regB)); //regB will compute and store (target at index) offset
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD), new Reg(Reg.Type.regB));
				emit(pCtx, storeType, new Reg(Reg.Type.regA), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)));
			}
			emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regD));
		}  else {
			visit(ctx.expr());
			emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
			emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regD)); // target offset
			emit(pCtx, storeType, new Reg(Reg.Type.regA), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regD)));
		}
		return null;
	}

	@Override public Op visitOutStat(JAFARParser.OutStatContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		Type exprType = this.checkResult.getType(ctx.expr());
		if (exprType instanceof Type.Array)
		{
			JAFARParser.ExprContext exprContext = ctx.expr();
			boolean isOffsetCase = (exprContext instanceof JAFARParser.IndexExprContext)    || (exprContext instanceof JAFARParser.IdExprContext);
			boolean isStackCase = (exprContext instanceof JAFARParser.ArrayExprContext) || (exprContext instanceof JAFARParser.FuncExprContext);
			if (isOffsetCase) {
				visit(ctx.expr());
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //regA is offset at the start of array
				for (int i = 0; i < exprType.size(); i++) {
					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regA)), new Reg(Reg.Type.regB)); //regD will store array value at offset (regA)
					emit(pCtx, OpCode.WriteInstr, new Reg(Reg.Type.regB), new Address(Address.Type.numberIO, -1));
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 1), new Reg(Reg.Type.regB));
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA)); // update A offset based on inter value
				}
			} else if (isStackCase) {
				for (int i = 0; i < exprType.size(); i++) {
					// sp + size-1 -> c : location of first elem of array
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, exprType.size()-1), new Reg(Reg.Type.regA)); // calculate stack pointer of opposite array value
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regA), new Reg(Reg.Type.regSP), new Reg(Reg.Type.regA));
					// rb: value
					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regA)), new Reg(Reg.Type.regA)); //get opposite array value
					// sp = sp +1;
				}
				for (int i = 0; i < exprType.size(); i++) {
					emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
				}
			}
		} else {
			if (ctx.expr() instanceof JAFARParser.IdExprContext) {
				JAFARParser.IdExprContext idExpr = (JAFARParser.IdExprContext) ctx.expr();
				String id = idExpr.ID().getText();
				if (checkResult.isShared(id)) {
					visit(ctx.expr());
					emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
					int offset = checkResult.getSharedOffset(id);
					emit(pCtx, OpCode.ReadInstr, new Address(Address.Type.DirAddr, offset));
					emit(pCtx, OpCode.Receive, new Reg(Reg.Type.regA));
					emit(pCtx, OpCode.WriteInstr, new Reg(Reg.Type.regA), new Address(Address.Type.numberIO, -1));

				} else {
					visit(ctx.expr());
					emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
					emit(pCtx, OpCode.WriteInstr, new Reg(Reg.Type.regA), new Address(Address.Type.numberIO, -1));
					return null;
				}
			} else {
				visit(ctx.expr());
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
				emit(pCtx, OpCode.WriteInstr, new Reg(Reg.Type.regA), new Address(Address.Type.numberIO, -1));
			}

		}
		return null;
	}

	@Override public Op visitFuncDecl(JAFARParser.FuncDeclContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		String funcId = ctx.ID().getText();
		this.currentFuncDecl = funcId;

		String funcLabel = pCtx.getNewLabel();
		int funStartLine = pCtx.getLineNum();

		funcToStartLine.put(funcId, funStartLine);
		emit(pCtx, funcLabel, OpCode.Nop);
		for (JAFARParser.StatContext stat:ctx.stat()) {
			visit(stat);
		}

		LinkedHashMap<String, Integer> funcOffSetData = this.checkResult.getFuncOffSetData(funcId);
		if (ctx.expr() != null) {
			visit(ctx.expr());
			JAFARParser.ExprContext returnExpr = ctx.expr();
			Type returnType = this.checkResult.getType(ctx.expr());
			//get return value offest = SP - funcsize - returnadd=1 store in register C
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, -funcOffSetData.size() - returnType.size()), new Reg(Reg.Type.regC));
			emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regC), new Reg(Reg.Type.regC));
			//registerC store target offset
			if (((returnType instanceof Type.Array)&&(returnExpr instanceof JAFARParser.IdExprContext))
					|| ((returnType instanceof Type.Array)&&(returnExpr instanceof JAFARParser.IndexExprContext)))
			{
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //assign offset
				for (int i = returnType.size() - 1; i >= 0; i--) {
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, i), new Reg(Reg.Type.regB)); //regB will store iteration value
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA)); // update A(assign) offset based on inter value B
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regC), new Reg(Reg.Type.regC)); // update E(target) offset based on inter value B

					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regA)), new Reg(Reg.Type.regD)); //regC will store assign value at index (regA)
					emit(pCtx, OpCode.Store, new Reg(Reg.Type.regD), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regC)));
					emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA)); // reset A(assign) offset based on inter value B
					emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regC), new Reg(Reg.Type.regB), new Reg(Reg.Type.regC)); // reset E(target) offset based on inter value B
				}
			} else if ((returnExpr instanceof JAFARParser.ArrayExprContext) || ((returnExpr instanceof JAFARParser.FuncExprContext) &&(returnType instanceof Type.Array))) {
				for (int i = returnType.size()-1; i >=0; i--) {
					emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //assign value
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, i), new Reg(Reg.Type.regB)); //regB will compute and store (target at index) offset
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regC), new Reg(Reg.Type.regB));
					emit(pCtx, OpCode.Store, new Reg(Reg.Type.regA), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)));
				}
			} else {
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //return value
				emit(pCtx, OpCode.Store, new Reg(Reg.Type.regA), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regC)));
			}
		}
		//get return address = SP - funcsize store in register B

		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, -funcOffSetData.size()), new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regB)); //get return address offset
		emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)), new Reg(Reg.Type.regB)); //store return line in register B


		emit(pCtx, OpCode.Jump, new Target(Target.TargetType.Ind, new Reg(Reg.Type.regB)));
		this.currentFuncDecl = null;
		return null;
	}

	@Override public Op visitFuncStat(JAFARParser.FuncStatContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		String funcId = ctx.ID().getText();

		LinkedHashMap<String, Integer> funcOffSetData = this.checkResult.getFuncOffSetData(funcId);
		LinkedHashMap<String, Type> funcTypeData = this.checkResult.getFuncTypeData(funcId);
		Type returnType = this.checkResult.getType(ctx);


		// Set up params before extend ARP
		for (int i = 0; i < ctx.expr().size(); i++) {
			visit(ctx.expr(i));
			String paramID = (String) funcOffSetData.keySet().toArray()[i];
			int paramOffSet = funcOffSetData.get(paramID);
			Type paramType = funcTypeData.get(paramID);
			JAFARParser.ExprContext assignExpr = ctx.expr(i);

			if (((assignExpr instanceof JAFARParser.IndexExprContext) && (paramType instanceof Type.Array))
					|| ((assignExpr instanceof JAFARParser.IdExprContext) &&(paramType instanceof Type.Array)))
			{
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //regA store assign array[index]'s offset
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, returnType.size()+1+paramOffSet), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regD), new Reg(Reg.Type.regD)); //regD: param offset in ARP
				for (int j = paramType.size() - 1; j >= 0; j--) {
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, j), new Reg(Reg.Type.regB)); //regB will store iteration value
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA)); // update A(assign) offset based on inter value B
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD), new Reg(Reg.Type.regD)); // update D(target) offset based on inter value B
					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regA)), new Reg(Reg.Type.regC)); //regC will store assign value at index (regA)
					emit(pCtx, OpCode.Store, new Reg(Reg.Type.regC), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regD)));
					emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA)); // reset A(assign) offset based on inter value B
					emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regD), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD)); // reset D(target) offset based on inter value B
				}
			} else if ((assignExpr instanceof JAFARParser.ArrayExprContext) || ((assignExpr instanceof JAFARParser.FuncExprContext) &&(paramType instanceof Type.Array))) {
				//Pop in reverse order
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, returnType.size()+1+paramOffSet), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regD), new Reg(Reg.Type.regD)); //regD: param offset in ARP
				Type.Array arrayType = (Type.Array) paramType;
				for (int j = arrayType.size()-1; j >=0; j--) {
					emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //regA store array[index]'s value
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, j), new Reg(Reg.Type.regB)); //regB will compute and store (target at index) offset
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD), new Reg(Reg.Type.regB));
					emit(pCtx, OpCode.Store, new Reg(Reg.Type.regA), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)));
				}
			}  else {
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, returnType.size()+1+paramOffSet), new Reg(Reg.Type.regB));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regB)); //regB: param offset
				emit(pCtx,OpCode.Pop, new Reg(Reg.Type.regA)); //value of param
				emit(pCtx,OpCode.Store, new Reg(Reg.Type.regA), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)));
			}
		}

		// extend ARP
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, funcOffSetData.size() + returnType.size() + 1), new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regF));

		int returnLine = pCtx.getLineNum()+5; //point to NOP instr
		//set up return address
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, returnLine), new Reg(Reg.Type.regA)); //store return line in register A
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, -funcOffSetData.size()), new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regB)); //regB: return address's memory offset
		emit(pCtx, OpCode.Store, new Reg(Reg.Type.regA), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)));
		//jump to function
		int funcStartLine = funcToStartLine.get(funcId);
		emit(pCtx, OpCode.Jump, new Target(Target.TargetType.Abs, funcStartLine));
		emit(pCtx, OpCode.Nop);

		//destroy arp
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, -funcOffSetData.size() - returnType.size()-1), new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regF));

		return null;
	}

	@Override public Op visitFuncExpr(JAFARParser.FuncExprContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		String funcId = ctx.ID().getText();

		LinkedHashMap<String, Integer> funcOffSetData = this.checkResult.getFuncOffSetData(funcId);
		LinkedHashMap<String, Type> funcTypeData = this.checkResult.getFuncTypeData(funcId);
		Type returnType = this.checkResult.getType(ctx);

		// Set up params before extend ARP
		for (int i = 0; i < ctx.expr().size(); i++) {
			visit(ctx.expr(i));
			String paramID = (String) funcOffSetData.keySet().toArray()[i];
			int paramOffSet = funcOffSetData.get(paramID);
			Type paramType = funcTypeData.get(paramID);
			JAFARParser.ExprContext assignExpr = ctx.expr(i);

			if (((assignExpr instanceof JAFARParser.IndexExprContext) && (paramType instanceof Type.Array))
					|| ((assignExpr instanceof JAFARParser.IdExprContext) &&(paramType instanceof Type.Array)))
			{
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //regA store assign array[index]'s offset
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, returnType.size()+1+paramOffSet), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regD), new Reg(Reg.Type.regD)); //regD: param offset in ARP
				for (int j = paramType.size() - 1; j >= 0; j--) {
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, j), new Reg(Reg.Type.regB)); //regB will store iteration value
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA)); // update A(assign) offset based on inter value B
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD), new Reg(Reg.Type.regD)); // update D(target) offset based on inter value B
					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regA)), new Reg(Reg.Type.regC)); //regC will store assign value at index (regA)
					emit(pCtx, OpCode.Store, new Reg(Reg.Type.regC), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regD)));
					emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA)); // reset A(assign) offset based on inter value B
					emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regD), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD)); // reset D(target) offset based on inter value B
				}
			} else if ((assignExpr instanceof JAFARParser.ArrayExprContext) || ((assignExpr instanceof JAFARParser.FuncExprContext) &&(paramType instanceof Type.Array))) {
				//Pop in reverse order
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, returnType.size()+1+paramOffSet), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regD), new Reg(Reg.Type.regD)); //regD: param offset in ARP
				Type.Array arrayType = (Type.Array) paramType;
				for (int j = arrayType.size()-1; j >=0; j--) {
					emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //regA store array[index]'s value
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, j), new Reg(Reg.Type.regB)); //regB will compute and store (target at index) offset
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD), new Reg(Reg.Type.regB));
					emit(pCtx, OpCode.Store, new Reg(Reg.Type.regA), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)));
				}
			}  else {
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, returnType.size()+1+paramOffSet), new Reg(Reg.Type.regB));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regB)); //regB: param offset
				emit(pCtx,OpCode.Pop, new Reg(Reg.Type.regA)); //value of param
				emit(pCtx,OpCode.Store, new Reg(Reg.Type.regA), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)));
			}
		}

		// extend ARP
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, funcOffSetData.size() + returnType.size() + 1), new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regF));

		int returnLine = pCtx.getLineNum()+5; //point to NOP instr
		//set up return address
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, returnLine), new Reg(Reg.Type.regA)); //store return line in register A
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, -funcOffSetData.size()), new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regB)); //regB: return address's memory offset
		emit(pCtx, OpCode.Store, new Reg(Reg.Type.regA), new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)));
		//jump to function
		int funcStartLine = funcToStartLine.get(funcId);
		emit(pCtx, OpCode.Jump, new Target(Target.TargetType.Abs, funcStartLine));
		emit(pCtx, OpCode.Nop);

		//get return value
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, -funcOffSetData.size() -(returnType.size() -1) -1), new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regB));//regB will

		if (returnType instanceof Type.Array) {
			for (int i = 0; i<returnType.size(); i++) {
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, i), new Reg(Reg.Type.regC)); //regC will store iteration value
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regC), new Reg(Reg.Type.regB)); // update B(assign) offset based on inter value C
				emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)), new Reg(Reg.Type.regA)); //regA will store return value at index in (regB)
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
				emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regB), new Reg(Reg.Type.regC), new Reg(Reg.Type.regB)); // reset B(assign) offset based on inter value C
			}
		} else {
			emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)), new Reg(Reg.Type.regA));
			emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
		}


		//destroy arp
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, -funcOffSetData.size() - returnType.size()-1), new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regF), new Reg(Reg.Type.regB), new Reg(Reg.Type.regF));

		return null;
	}

	@Override public Op visitBlock(JAFARParser.BlockContext ctx) {
		for (ParseTree stat : ctx.stat ()) {
			visit(stat);
		}
		return null;
	}

	@Override public Op visitBlockStat(JAFARParser.BlockStatContext ctx) {
		return visit(ctx.block());
	}

	@Override public Op visitBody(JAFARParser.BodyContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		String mainLabel = pCtx.getNewLabel();
		emit(pCtx, OpCode.Jump, new Target(Target.TargetType.Abs, mainLabel));
		for (ParseTree func : ctx.func()) {
			visit(func);
		}
		emit(pCtx, mainLabel, OpCode.Nop);
		super.visit(ctx.block());
		return null;
	}

	@Override
	public Op visitThreadBlock(JAFARParser.ThreadBlockContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		String beginLabel = pCtx.getNewLabel();
		emit(pCtx, beginLabel, OpCode.Nop);
		emit(pCtx, OpCode.ReadInstr, new Address(Address.Type.DirAddr, checkResult.getThreadId(ctx) + BEGINZONE));
		emit(pCtx, OpCode.Receive,  new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 1), new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Compute, new Operator(Xor), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.Branch, new Reg(Reg.Type.regA), new Target(Target.TargetType.Abs, beginLabel));
		Op result = visit(ctx.block());
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 1), new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.WriteInstr, new Reg(Reg.Type.regA), new Address(Address.Type.DirAddr, checkResult.getThreadId(ctx) + ENDZONE));
		return result;
	}

	@Override
	public Op visitThreadStat(JAFARParser.ThreadStatContext ctx) {
		Op result = null;
		Program pCtx = getProgramByNode(ctx);
		String beginLine = pCtx.getNewLabel();
		emit(pCtx, beginLine, OpCode.Nop);

		for (ParseTree threadCtx : ctx.threadBlock()) {
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 1), new Reg(Reg.Type.regA));
			emit(pCtx, OpCode.WriteInstr, new Reg(Reg.Type.regA), new Address(Address.Type.DirAddr, checkResult.getThreadId(threadCtx) + BEGINZONE));
		}

		for (ParseTree threadCtx : ctx.threadBlock()) {
			result =  visit(threadCtx);
			String waitLine = pCtx.getNewLabel();
			emit(pCtx, waitLine, OpCode.Nop);
			emit(pCtx, OpCode.ReadInstr, new Address(Address.Type.DirAddr, checkResult.getThreadId(threadCtx) + ENDZONE));
			emit(pCtx, OpCode.Receive, new Reg(Reg.Type.regA));
			emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 1), new Reg(Reg.Type.regB));
			emit(pCtx, OpCode.Compute, new Operator(Xor), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
			emit(pCtx, OpCode.Branch, new Reg(Reg.Type.regA), new Target(Target.TargetType.Abs, waitLine));
		}
		return result;
	}

	@Override
	public Op visitLockStat(JAFARParser.LockStatContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		int parentTID = checkResult.getParentID(checkResult.getThreadId(ctx));
		String waitLabel = pCtx.getNewLabel();
		emit(pCtx, waitLabel, OpCode.TestAndSet, new Address(Address.Type.DirAddr, parentTID + LOCKZONE));
		emit(pCtx, OpCode.Receive, new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.Compute, new Operator(Equal), new Reg(Reg.Type.regA), new Reg(Reg.Type.reg0), new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.Branch, new Reg(Reg.Type.regA), new Target(Target.TargetType.Abs, waitLabel));
		return null;
	}

	@Override
	public Op visitUnlockStat(JAFARParser.UnlockStatContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		int parentTID = checkResult.getParentID(checkResult.getThreadId(ctx));
		emit(pCtx, OpCode.WriteInstr, new Reg(Reg.Type.reg0), new Address(Address.Type.DirAddr, parentTID + LOCKZONE));
		return null;
	}

	@Override public Op visitIfStat(JAFARParser.IfStatContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		Op result = visit(ctx.expr());
		emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.Compute, new Operator(Equal), new Reg(Reg.Type.regA), new Reg(Reg.Type.reg0), new Reg(Reg.Type.regA));
		String endLine = pCtx.getNewLabel();

		if (ctx.ELSE() == null) {
			emit(pCtx, OpCode.Branch, new Reg(Reg.Type.regA), new Target(Target.TargetType.Abs, endLine));
			visit(ctx.block(0));
		} else {
			String elseLine = pCtx.getNewLabel();
			emit(pCtx, OpCode.Branch, new Reg(Reg.Type.regA), new Target(Target.TargetType.Abs, elseLine));
			visit(ctx.block(0));
			emit(pCtx, OpCode.Jump, new Target(Target.TargetType.Abs, endLine));
			emit(pCtx, elseLine, OpCode.Nop);
			visit(ctx.block(1));
		}
		emit(pCtx, endLine, OpCode.Nop);
		return result ;
	}

	@Override
	public Op visitWhileStat(JAFARParser.WhileStatContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		String body = pCtx.getNewLabel();
		String cond = pCtx.getNewLabel();

		emit(pCtx, OpCode.Jump, new Target(Target.TargetType.Abs, cond));
		emit(pCtx, body, OpCode.Nop);
		visit(ctx.block());

		emit(pCtx, cond, OpCode.Nop);
		Op result = visit(ctx.expr());
		emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 1), new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Compute, new Operator(Equal), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.Branch, new Reg(Reg.Type.regA), new Target(Target.TargetType.Abs, body));

		return result;
	}

	@Override public Op visitPrfExpr(JAFARParser.PrfExprContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		visit(ctx.expr());
		emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
		switch (ctx.prfOp().getStart().getType()) {
			case JAFARLexer.MINUS :
				emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.reg0), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA));
				break;
			case JAFARLexer.NOT :
				emit(pCtx, OpCode.Load, TRUE_VALUE, new Reg(Reg.Type.regB));
				emit(pCtx, OpCode.Compute, new Operator(Xor) ,new Reg(Reg.Type.regB), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA));
				break;
			default:
				assert false;
		}
		emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}


	@Override public Op visitProgram(JAFARParser.ProgramContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, this.checkResult.getMaxOffset()-1), new Reg(Reg.Type.regF));
		super.visit(ctx.body());
		return null;
	}

	@Override public Op visitParExpr(JAFARParser.ParExprContext ctx) {
		visit(ctx.expr());
		return null;
	}

	@Override public Op visitPlusExpr(JAFARParser.PlusExprContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		visit(ctx.expr(0));
		visit(ctx.expr(1));
		emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
		Operator operator;
		switch (ctx.plusOp().getStart().getType()) {
			case JAFARLexer.PLUS:
				operator = new Operator(Add);
				break;
			case JAFARLexer.MINUS:
				operator = new Operator(Sub);
				break;
			default:
				assert false;
				operator = null;
		}
		emit(pCtx, OpCode.Compute, operator, new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}

	@Override
	public Op visitMultExpr(JAFARParser.MultExprContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		String endProgLab = pCtx.getEndLabel();
		visit(ctx.expr(0));
		visit(ctx.expr(1));
		emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
		Operator operator;
		switch (ctx.multOp().getStart().getType()) {
			case JAFARLexer.STAR:
				operator = new Operator(Mul);
				emit(pCtx, OpCode.Compute, operator, new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
				break;
			case JAFARLexer.SLASH:
				emit(pCtx, OpCode.Compute, new Operator(Equal), new Reg(Reg.Type.regB), new Reg(Reg.Type.reg0), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Branch, new Reg(Reg.Type.regD), new Target(Target.TargetType.Abs, endProgLab));
				String body = pCtx.getNewLabel();
				String cond = pCtx.getNewLabel();
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 0), new Reg(Reg.Type.regC));
				emit(pCtx, OpCode.Jump, new Target(Target.TargetType.Abs, cond));
				emit(pCtx, body, OpCode.Nop);
				emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 1), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regC), new Reg(Reg.Type.regD), new Reg(Reg.Type.regC));
				emit(pCtx, cond, OpCode.Nop);
				emit(pCtx, OpCode.Compute, new Operator(GtE), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Branch, new Reg(Reg.Type.regD), new Target(Target.TargetType.Abs, body));
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regC));
				break;
			case JAFARLexer.MOD:
				emit(pCtx, OpCode.Compute, new Operator(LtE), new Reg(Reg.Type.regB), new Reg(Reg.Type.reg0), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Branch, new Reg(Reg.Type.regD), new Target(Target.TargetType.Abs, endProgLab));
				String bodyMod = pCtx.getNewLabel();
				String condMod = pCtx.getNewLabel();
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 0), new Reg(Reg.Type.regC));
				emit(pCtx, OpCode.Jump, new Target(Target.TargetType.Abs, condMod));
				emit(pCtx, bodyMod, OpCode.Nop);
				emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 1), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regC), new Reg(Reg.Type.regD), new Reg(Reg.Type.regC));
				emit(pCtx, condMod, OpCode.Nop);
				emit(pCtx, OpCode.Compute, new Operator(GtE), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Branch, new Reg(Reg.Type.regD), new Target(Target.TargetType.Abs, bodyMod));
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
				break;
			default:
				assert false;
				operator = null;
		}

		return null;
	}

	@Override public Op visitTrueExpr(JAFARParser.TrueExprContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		emit(pCtx, OpCode.Load , TRUE_VALUE , new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}

	@Override public Op visitFalseExpr(JAFARParser.FalseExprContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		emit(pCtx, OpCode.Load , FALSE_VALUE , new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}

	@Override public Op visitNumExpr(JAFARParser.NumExprContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue
				, Integer.parseInt(ctx.NUM().getText())), new Reg(Reg.Type.regA));
		emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}

	@Override public Op visitCompExpr(JAFARParser.CompExprContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		Type leftType = checkResult.getType(ctx.expr(0));
		Type rightType = checkResult.getType(ctx.expr(1));
		boolean isArrayL = (leftType instanceof Type.Array);
		boolean isArrayR = (rightType instanceof Type.Array);
		JAFARParser.ExprContext leftExpr = ctx.expr(0);
		JAFARParser.ExprContext rightExpr = ctx.expr(1);
		boolean isArrayOffsetCaseL = (leftExpr instanceof JAFARParser.IndexExprContext)	|| (leftExpr instanceof JAFARParser.IdExprContext);
		boolean isArrayOffsetCaseR = (rightExpr instanceof JAFARParser.IndexExprContext)|| (rightExpr instanceof JAFARParser.IdExprContext);
		boolean isArrayStackCaseL = (leftExpr instanceof JAFARParser.ArrayExprContext) || (leftExpr instanceof JAFARParser.FuncExprContext);
		boolean isArrayStackCaseR = (rightExpr instanceof JAFARParser.ArrayExprContext) || (rightExpr instanceof JAFARParser.FuncExprContext);
		if ((ctx.compOp().getStart().getType() == JAFARLexer.EQ) && isArrayL && isArrayR) {
			if (leftType.size() != rightType.size()) {
				emit(pCtx, OpCode.Load, FALSE_VALUE, new Reg(Reg.Type.regC));
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regC));
			} else if (isArrayOffsetCaseL && isArrayOffsetCaseR) {
				// 2 array with same size
				visit(ctx.expr(0));
				visit(ctx.expr(1));
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regB));
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
				emit(pCtx, OpCode.Load, TRUE_VALUE, new Reg(Reg.Type.regC));
				for (int i = 0; i < leftType.size(); i++) {
					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regA)), new Reg(Reg.Type.regD)); //regD will store array value at offset (regA)
					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)), new Reg(Reg.Type.regE)); //regE will store array value at offset (regB)
					emit(pCtx, OpCode.Compute, new Operator(Equal), new Reg(Reg.Type.regD), new Reg(Reg.Type.regE), new Reg(Reg.Type.regD));
					emit(pCtx, OpCode.Compute, new Operator(And), new Reg(Reg.Type.regC), new Reg(Reg.Type.regD), new Reg(Reg.Type.regC));
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 1), new Reg(Reg.Type.regD));
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regA), new Reg(Reg.Type.regD), new Reg(Reg.Type.regA)); // update A offset based on inter value
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD), new Reg(Reg.Type.regB)); // update B offset based on inter value
				}
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regC));
			} else if (isArrayStackCaseL && isArrayStackCaseR) {
				visit(ctx.expr(0));
				visit(ctx.expr(1));
				emit(pCtx, OpCode.Load, TRUE_VALUE, new Reg(Reg.Type.regC));
				// looping one by one through variables
				for (int i = 0; i < leftType.size(); i++) {
					// sp : location of right expr -> ra: value
					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regSP)), new Reg(Reg.Type.regA)); //get array value at stack pointer
					// sp + size -> c : location of left expr
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, leftType.size()), new Reg(Reg.Type.regB)); // calculate stack pointer of opposite array value
					emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regB), new Reg(Reg.Type.regSP), new Reg(Reg.Type.regB));
					// rb: value
					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regB)), new Reg(Reg.Type.regB)); //get opposite array value
					emit(pCtx, OpCode.Compute, new Operator(Equal), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
					emit(pCtx, OpCode.Compute, new Operator(And), new Reg(Reg.Type.regA), new Reg(Reg.Type.regC), new Reg(Reg.Type.regC));
					// sp = sp +1;
					emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
				}
				// restore stack pointer because we still have value of right expr
				for (int i = 0; i < leftType.size(); i++) {
					emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
				}
				// push back result to stack
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regC));
			} else if (isArrayStackCaseL && isArrayOffsetCaseR) {
				visit(ctx.expr(0));
				visit(ctx.expr(1));
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //regA will be offset first elem of right array
				emit(pCtx, OpCode.Load, TRUE_VALUE, new Reg(Reg.Type.regC));
				// update A offset to last elem of array because when pop from stack left array will be in reverse order
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, leftType.size()-1), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regA), new Reg(Reg.Type.regD), new Reg(Reg.Type.regA));
				for (int i = 0; i < leftType.size(); i++) {
					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regA)), new Reg(Reg.Type.regD)); //regD will store array value at offset (regA)
					emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regB)); //regB will be left array's value take from stack in reverse order
					emit(pCtx, OpCode.Compute, new Operator(Equal), new Reg(Reg.Type.regD), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD));
					emit(pCtx, OpCode.Compute, new Operator(And), new Reg(Reg.Type.regC), new Reg(Reg.Type.regD), new Reg(Reg.Type.regC));
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue,1), new Reg(Reg.Type.regD));
					emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regA), new Reg(Reg.Type.regD), new Reg(Reg.Type.regA)); // update A offset based on inter value A=A-1
				}
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regC));
			} else if (isArrayOffsetCaseL && isArrayStackCaseR) {
				visit(ctx.expr(1));
				visit(ctx.expr(0));
				emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA)); //regA will be offset of first elem left array
				emit(pCtx, OpCode.Load, TRUE_VALUE, new Reg(Reg.Type.regC));
				// update A offset to last elem of array because when pop from stack left array will be in reverse order
				emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, leftType.size()-1), new Reg(Reg.Type.regD));
				emit(pCtx, OpCode.Compute, new Operator(Add), new Reg(Reg.Type.regA), new Reg(Reg.Type.regD), new Reg(Reg.Type.regA));
				for (int i = 0; i < leftType.size(); i++) {
					emit(pCtx, OpCode.Load, new Address(Address.Type.IndAddr, new Reg(Reg.Type.regA)), new Reg(Reg.Type.regD)); //regD will store array value at offset (regA)
					emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regB)); //regB will be right array's value take from stack in reserve order
					emit(pCtx, OpCode.Compute, new Operator(Equal), new Reg(Reg.Type.regD), new Reg(Reg.Type.regB), new Reg(Reg.Type.regD));
					emit(pCtx, OpCode.Compute, new Operator(And), new Reg(Reg.Type.regC), new Reg(Reg.Type.regD), new Reg(Reg.Type.regC));
					emit(pCtx, OpCode.Load, new Address(Address.Type.ImmValue, 1), new Reg(Reg.Type.regD));
					emit(pCtx, OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.regA), new Reg(Reg.Type.regD), new Reg(Reg.Type.regA)); // update A offset based on inter value
				}
				emit(pCtx, OpCode.Push, new Reg(Reg.Type.regC));
			}
		} else {
			visit(ctx.expr(0));
			visit(ctx.expr(1));
			emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regB));
			emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
			Operator operator ;
			switch (ctx.compOp().getStart().getType()) {
				case JAFARLexer.EQ :
					operator = new Operator(Equal);
					break;
				case JAFARLexer.NE :
					operator = new Operator(NEq);
					break;
				case JAFARLexer.LT :
					operator = new Operator(Lt);
					break;
				case JAFARLexer.LE :
					operator = new Operator(LtE);
					break;
				case JAFARLexer.GT :
					operator = new Operator(Gt);
					break;
				case JAFARLexer.GE :
					operator = new Operator(GtE);
					break;
				default:
					assert false;
					operator = null;
			}
			emit(pCtx, OpCode.Compute, operator, new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
			emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
		}
		return null;
	}

	@Override public Op visitBoolExpr(JAFARParser.BoolExprContext ctx) {
		Program pCtx = getProgramByNode(ctx);
		visit(ctx.expr(0));
		visit (ctx.expr(1));
		emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regB));
		emit(pCtx, OpCode.Pop, new Reg(Reg.Type.regA));
		Operator operator;
		switch (ctx.boolOp().getStart().getType()) {
			case JAFARLexer.AND:
				operator = new Operator(And);
				break;
			case JAFARLexer.OR:
				operator = new Operator(Or);
				break;
			default:
				assert false;
				operator = null;
		}
		emit(pCtx, OpCode.Compute, operator, new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
		return emit(pCtx, OpCode.Push, new Reg(Reg.Type.regA));
	}

	/**
	 * emit SPRIL code to a program without a label to the line of code
	 * @param program : the program / thread intended to use the code
	 * @param opCode operator code
	 * @param args arguments
	 * @return not used
	 */
	protected Op emit(Program program, OpCode opCode, Operand... args) {
		return emit(program, null, opCode, args);
	}

	// if we have if else then we put a string label to emit, else the line will increase but no label will be put in

	/**
	 * emit SPRIL code to a program with label to the line of code
	 * @param prog : the program / thread intended to use the code
	 * @param label label of the line of code
	 * @param opCode operator code
	 * @param args arguments
	 * @return not used
	 */
	protected Op emit(Program prog, String label, OpCode opCode, Operand... args) {
		if(label != null) {
			prog.putLabel(label);
		}
		prog.increaseLine();
		Op result = new Op(label, opCode, args);
		prog.addInstr(result);
		return result;
	}

	/**
	 * Get the program using a node
	 * @param ctx : Parse tree node
	 * @return program using ctx
	 */
	protected Program getProgramByNode(ParseTree ctx) {
		return programs.get(checkResult.getThreadId(ctx));
	}

}
