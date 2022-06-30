package finalproject.model;

/** Abstract supertype of all kinds of operands. */
abstract public class Operand {
	private final Type type;

	/** Constructs an operand of a given type. */
	protected Operand(Type type) {
		this.type = type;
	}

	/** Returns the type of this operand. */
	public Type getType() {
		return this.type;
	}

	/** Enumeration of all available operand types. */
	public enum Type {
		/** Register-type operand; class {@link Reg}. */
		REG,
		/** Label operand; class {@link Label}. */
		LABEL,
		/** Target operand; class {@link Target}.
		 */
		TARGET,
		/**
		 * Address operand; class {@link Address}.
		 */
		ADDR,
		/**
		 * Operator operand; class {@link Operator}.
		 */
		OPERATOR;
	}
}
