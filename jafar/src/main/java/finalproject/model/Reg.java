package finalproject.model;

/** Register operand
 */
public class Reg extends Operand {
	private Type regType;

	/**
	 *
	 * @param t : should be of Reg Type
	 */
	public Reg(Type t) {
		super(Operand.Type.REG);
		this.regType = t;
	}

	public enum Type {
		reg0,
		regSprID,
		regA,
		regB,
		regC,
		regD,
		regE,
		regF,
		regSP,
		regPC;
	}

	/**
	 *
	 * @return string representation of all attributes of reg obj
	 */
	@Override
	public String toString() {
		return (""+this.regType);
	}
}