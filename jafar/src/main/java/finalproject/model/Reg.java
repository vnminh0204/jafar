package finalproject.model;

/** Register operand
 */
public class Reg extends Operand {
	private Type regType;

	/**
	 * @param type : type of register
	 */
	public Reg(Type type) {
		super(Operand.Type.REG);
		this.regType = type;
	}

	public enum Type {
		/** register 0 is always 0 */
		reg0,
		/** contains the sprockellID */
		regSprID,
		regA,
		regB,
		regC,
		regD,
		regE,
		/** register F is used to store ARP of function */
		regF,
		/** register for stack pointer */
		regSP,
		/** register for program counter */
		regPC;
	}

	/**
	 * @return string representation of all attributes of reg obj
	 */
	@Override
	public String toString() {
		return (""+this.regType);
	}
}