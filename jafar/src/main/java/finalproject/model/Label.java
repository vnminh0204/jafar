package finalproject.model;

/** Label operand.
 */
public class Label extends Operand {
	/** line number corresponding to the label */
	private Integer lineNumber;

	/** String representation of label */
	private final String value;

	/** Constructs a label object with a given label text.
	 * @param value : Label string value
	 * @param line : Line counter corresponding to label
	 * */
	public Label(String value, int line) {
		super(Type.LABEL);
		this.value = value;
		this.lineNumber = line;
	}

	/** Returns the value of this label. */
	public String getValue() {
		return this.value;
	}

	/** Return current line numer. */
	public Integer getLineNumber() {
		return this.lineNumber;
	}


	@Override
	public String toString() {
		return this.value + " @line: " + this.lineNumber;
	}

	@Override
	public int hashCode() {
		return getValue().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Label)) {
			return false;
		}
		Label other = (Label) obj;
		if (!getValue().equals(other.getValue())) {
			return false;
		}
		return true;
	}

}