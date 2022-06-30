package finalproject.checker_generator_compiler;

import finalproject.grammar.JAFARBaseVisitor;
import finalproject.grammar.JAFARLexer;
import finalproject.grammar.JAFARParser;
import finalproject.model.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import java.util.HashMap;
import static finalproject.model.Operator.Type.*;

/** Class to generate SPRIL code for Simple Pascal. */
public class Generator extends JAFARBaseVisitor<Op> {

	/** The representation of the boolean value <code>false</code>. */
	public final static Address FALSE_VALUE = new Address(Address.Type.ImmValue, 0);
	/** The representation of the boolean value <code>true</code>. */
	public final static Address TRUE_VALUE = new Address(Address.Type.ImmValue, 1);

	/** The outcome of the checker phase. */
	private Result checkResult;

	private int arp_offset;
	/** Association of statement nodes to labels. */
	private ParseTreeProperty<Label> labels;
	/** The program being built. */
	private Program prog;


	/** Mapping of lines to Op */
	private HashMap<String, Label> labelHashMap;

	private int lineNum;

	private int labelCount;

	/** Generates SPRIL code for a given parse tree,
	 * given a pre-computed checker result.
	 */
	public Program generate(ParseTree tree, Result checkResult) {
		this.prog = new Program();
		this.arp_offset = 0;
		this.checkResult = checkResult;
		this.labels = new ParseTreeProperty<>();
		this.lineNum = 0;
		this.labelCount = 0;
		labelHashMap = new HashMap<>();
		tree.accept(this);
		this.prog.setLabelHashMap(labelHashMap);
		emit(OpCode.EndProg);
		this.prog.convert();
		return this.prog;
	}

	@Override public Op visitIdExpr(JAFARParser.IdExprContext ctx) {
//		TODO calulate offset based on ARP
		int idOffSet = offset(ctx);
		emit(OpCode.Load, new Address(Address.Type.DirAddr, idOffSet), new Reg(Reg.Type.regA));
		emit(OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}

	@Override public Op visitIdTarget(JAFARParser.IdTargetContext ctx) {
//		TODO handle in array case
		return null;
	}


	public Op visitAssStat(JAFARParser.AssStatContext ctx) {
		visit(ctx.expr());
		emit(OpCode.Pop, new Reg(Reg.Type.regA));
		// TODO Change for array
		int targetOffset = offset(ctx.target());
		emit(OpCode.WriteInstr, new Reg(Reg.Type.regA), new Address(Address.Type.DirAddr, targetOffset));
		return null;
	}

	@Override public Op visitBlock(JAFARParser.BlockContext ctx) {
		for (ParseTree stat : ctx.stat ()) {
			Op statOp = visit(stat);
		}
		return null;
	}

	@Override public Op visitBlockStat(JAFARParser.BlockStatContext ctx) {
		return visit(ctx.block());
	}

	@Override public Op visitBody(JAFARParser.BodyContext ctx) {
		return super.visit(ctx.block());
	}

	@Override public Op visitIfStat(JAFARParser.IfStatContext ctx) {
		Op result = visit(ctx.expr());
		emit(OpCode.Pop, new Reg(Reg.Type.regA));
		emit(OpCode.Compute, new Operator(Equal), new Reg(Reg.Type.regA), new Reg(Reg.Type.reg0), new Reg(Reg.Type.regA));
		String endLine = getNewLabel();

		if (ctx.ELSE() == null) {
			emit(OpCode.Branch, new Reg(Reg.Type.regA), new Target(Target.TargetType.Abs, endLine));
			visit(ctx.block(0));
		} else {
			String elseLine = getNewLabel();
			emit(OpCode.Branch, new Reg(Reg.Type.regA), new Target(Target.TargetType.Abs, elseLine));
			visit(ctx.block(0));
			emit(OpCode.Jump, new Target(Target.TargetType.Abs, endLine));
			emit(elseLine, OpCode.Nop);
			visit(ctx.block(1));
		}
		emit(endLine, OpCode.Nop);
		return result ;
	}

	@Override
	public Op visitWhileStat(JAFARParser.WhileStatContext ctx) {
		String body = getNewLabel();
		String cond = getNewLabel();

		emit(OpCode.Jump, new Target(Target.TargetType.Abs, cond));
		emit(body, OpCode.Nop);
		visit(ctx.block());

		emit(cond, OpCode.Nop);
		Op result = visit(ctx.expr());
		emit(OpCode.Pop, new Reg(Reg.Type.regA));
		emit(OpCode.Load, new Address(Address.Type.ImmValue, 1), new Reg(Reg.Type.regB));
		emit(OpCode.Compute, new Operator(Equal), new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
		emit(OpCode.Branch, new Reg(Reg.Type.regA), new Target(Target.TargetType.Abs, body));

		return result;
	}

	@Override public Op visitPrfExpr(JAFARParser.PrfExprContext ctx) {
		visit(ctx.expr());
		emit(OpCode.Pop, new Reg(Reg.Type.regA));
		switch (ctx.prfOp().getStart().getType()) {
			case JAFARLexer.MINUS :
				emit(OpCode.Compute, new Operator(Sub), new Reg(Reg.Type.reg0), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA));
				break;
			case JAFARLexer.NOT :
				emit(OpCode.Load, TRUE_VALUE, new Reg(Reg.Type.regB));
				emit(OpCode.Compute, new Operator(Xor) ,new Reg(Reg.Type.regB), new Reg(Reg.Type.regA), new Reg(Reg.Type.regA));
				break;
			default:
				assert false;
		}
		emit(OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}


	@Override public Op visitProgram(JAFARParser.ProgramContext ctx) {
		super.visit(ctx.body());
		return null;
	}

	@Override public Op visitParExpr(JAFARParser.ParExprContext ctx) {
		visit(ctx.expr());
		return null;
	}

	@Override
	public Op visitMultExpr(JAFARParser.MultExprContext ctx) {
		visit(ctx.expr(0));
		visit(ctx.expr(1));
		emit(OpCode.Pop, new Reg(Reg.Type.regA));
		emit(OpCode.Pop, new Reg(Reg.Type.regB));
		Operator operator;
		switch (ctx.multOp().getStart().getType()) {
			case JAFARLexer.STAR:
				operator = new Operator(Mul);
				break;
//			case JAFARLexer.SLASH:
//				opCode = OpCode.div;
//				break;
			default:
				assert false;
				operator = null;
		}
		emit(OpCode.Compute, operator, new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
		emit(OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}

	@Override public Op visitTrueExpr(JAFARParser.TrueExprContext ctx) {
		emit(OpCode.Load , TRUE_VALUE , new Reg(Reg.Type.regA));
		emit(OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}

	@Override public Op visitFalseExpr(JAFARParser.FalseExprContext ctx) {
		emit(OpCode.Load , FALSE_VALUE , new Reg(Reg.Type.regA));
		emit(OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}

	@Override public Op visitNumExpr(JAFARParser.NumExprContext ctx) {
		 emit(OpCode.Load, new Address(Address.Type.ImmValue
				, Integer.parseInt(ctx.NUM().getText())), new Reg(Reg.Type.regA));
		emit(OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}


	@Override public Op visitCompExpr(JAFARParser.CompExprContext ctx) {
		Op result = visit(ctx.expr(0));
		visit(ctx.expr(1));
		emit(OpCode.Pop, new Reg(Reg.Type.regA));
		emit(OpCode.Pop, new Reg(Reg.Type.regB));
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
		emit(OpCode.Compute, operator, new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
		return emit(OpCode.Push, new Reg(Reg.Type.regA));
	}

	@Override public Op visitBoolExpr(JAFARParser.BoolExprContext ctx) {
		visit(ctx.expr(0));
		visit (ctx.expr(1));
		emit(OpCode.Pop, new Reg(Reg.Type.regA));
		emit(OpCode.Pop, new Reg(Reg.Type.regB));
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
		emit(OpCode.Compute, operator, new Reg(Reg.Type.regA), new Reg(Reg.Type.regB), new Reg(Reg.Type.regA));
		emit(OpCode.Push, new Reg(Reg.Type.regA));
		return null;
	}


	/** Constructs an operation from the parameters
         * and adds it to the program under construction. */
	private Op emit(Label label, OpCode opCode, Operand... args) {
		Op result = new Op(label, opCode, args);
		this.prog.addInstr(result);
		return result;
	}

	/** Constructs an operation from the parameters
	 * and adds it to the program under construction. */
	private Op emit(OpCode opCode, Operand... args) {
		return emit((String) null, opCode, args);
	}


	/** Retrieves the offset of a variable node from the checker result*/
	private int offset(ParseTree node) {
		return this.checkResult.getOffset(node);
	}

	private Label getLabel(String lb) {
		return labelHashMap.get(lb);
	}

	// if we have if else then we put a string label to emit, else the line will increase but no label will be put in
	private Op emit(String label, OpCode opCode, Operand... args) {
		if(label != null) {
			labelHashMap.put(label, new Label(label, lineNum));
		}
		lineNum++;
		Op result = new Op(label, opCode, args);
		this.prog.addInstr(result);
		return result;
	}

	private String getNewLabel() {
		return "Label: " + (labelCount++);
	}

}
