package finalproject.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * SPRIL operation
 */
public class Op extends Instr {
	/** Operand separator. */
	private final static String OP_SEP = " ";

	/** The operation code. */
	private final OpCode opCode;
	/** The list of arguments of this operation. */
	private final List<Operand> args;
	/** The optional comment for this operation. */
	private String comment;

	/** The string representation of label */
	private String sLabel;

	/** Constructs an unlabelled operation with a given opcode and arguments. */
	public Op(OpCode opCode, Operand... args) {
		this((String) null, opCode, args);
	}

	/** Constructs an unlabelled operation with a given opcode and arguments. */
	public Op(OpCode opCode, List<Operand> args) {
		this((String) null, opCode, args);
	}

	/** Constructs a labelled operation with a given opcode and arguments. */
	public Op(Label label, OpCode opCode, Operand... args) {
		this(label, opCode, Arrays.asList(args));
	}

	public Op(String sLabel, OpCode opCode, Operand... args) { this(sLabel, opCode, Arrays.asList(args)); }

	/** Constructs a labelled operation with a given opcode and arguments.
	 * @throws IllegalArgumentException if one of the arguments
	 * is not of the expected type
	 */
	public String getsLabel() {
		return this.sLabel;
	}

	/** Constructs a labelled operation with a given label, opcode and arguments. */
	public Op(String sLabel, OpCode opCode, List<Operand> args) {
		this.sLabel = sLabel;
		this.opCode = opCode;
		int argsCount = opCode.getSigSize();
		if (args.size() != argsCount) {
			throw new IllegalArgumentException(String.format(
					"Operation '%s' expects %d arguments but has %d", opCode,
					argsCount, args.size()));
		}
		for (int i = 0; i < argsCount; i++) {
			Operand arg = args.get(i);
			Operand.Type expected = opCode.getSig().get(i);
			if (arg.getType() != expected) {
				throw new IllegalArgumentException(
						String.format(
								"Operation '%s' argument %d should be '%s' but is '%s'",
								this.opCode, i, expected, arg.getType()));
			}
		}
		this.args = new ArrayList<>(args);
	}

	/** Constructs a labelled operation with a given label, opcode and arguments. */
	public Op(Label label, OpCode opCode, List<Operand> args)
			throws IllegalArgumentException {
		if (label != null) {
			super.setLabel(label);
		}
		this.opCode = opCode;
		int argsCount = opCode.getSigSize();
		if (args.size() != argsCount) {
			throw new IllegalArgumentException(String.format(
					"Operation '%s' expects %d arguments but has %d", opCode,
					argsCount, args.size()));
		}
		for (int i = 0; i < argsCount; i++) {
			Operand arg = args.get(i);
			Operand.Type expected = opCode.getSig().get(i);
			if (arg.getType() != expected) {
				throw new IllegalArgumentException(
						String.format(
								"Operation '%s' argument %d should be '%s' but is '%s'",
								this.opCode, i, expected, arg.getType()));
			}
		}
		this.args = new ArrayList<>(args);
	}

	/** Returns the opcode of this operation. */
	public OpCode getOpCode() {
		return this.opCode;
	}

	/** Returns the list of all (source + target) arguments. */
	public List<Operand> getArgs() {
		return this.args;
	}

	/** Convenience method to retrieve a given argument as {@link Reg}. */
	public Reg reg(int i) {
		return (Reg) this.args.get(i);
	}

	/** Convenience method to retrieve a given operand as {@link Label}. */
	public Label label(int i) {
		return (Label) this.args.get(i);
	}

	/** Indicates if this operation has a comment. */
	public boolean hasComment() {
		return getComment() != null;
	}

	/** Returns the optional comment for this operation. */
	public String getComment() {
		return this.comment;
	}

	/** Sets a comment for this operation. */
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public Iterator<Op> iterator() {
		return Collections.singleton(this).iterator();
	}


	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(toLabelString());
		result.append(getOpCode());
		if (getOpCode().getSourceCount() > 0) {
			result.append(' ');
			result.append(toSourceString());
		}
		if (getOpCode().getTargetCount() > 0) {
			result.append(' ');
			result.append(toTargetString());
		}
		result.append(' ');
		return result.toString();
	}


	/** @return the string representation of the source operands. */
	public String toSourceString() {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (int i = 0; i < getOpCode().getSourceCount(); i++) {
			Operand o = getArgs().get(i);
			if (first) {
				first = false;
			} else {
				result.append(OP_SEP);
			}
			result.append(o);
		}
		return result.toString();
	}

	/** @return the string representation of the target operands. */
	public String toTargetString() {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (int i = getOpCode().getSourceCount(); i < getOpCode()
				.getSigSize(); i++) {
			Operand o = getArgs().get(i);
			if (first) {
				first = false;
			} else {
				result.append(OP_SEP);
			}
			result.append(o);
		}
		return result.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + super.hashCode();
		result = prime * result
				+ ((this.comment == null) ? 0 : this.comment.hashCode());
		result = prime * result + this.opCode.hashCode();
		result = prime * result + this.args.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		Op other = (Op) obj;
		if (!hasComment()) {
			if (other.hasComment()) {
				return false;
			}
		} else if (!getComment().equals(other.getComment())) {
			return false;
		}
		if (this.opCode != other.opCode) {
			return false;
		}
		if (!this.args.equals(other.args)) {
			return false;
		}
		return true;
	}
}
