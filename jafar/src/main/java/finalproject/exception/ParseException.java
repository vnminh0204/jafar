package finalproject.exception;

import java.util.List;

/** Exception class wrapping a list of error messages. */
public class ParseException extends Exception {
	private final List<String> messages;

	public ParseException(List<String> messages) {
		super(messages.toString());
		this.messages = messages;
	}

	/** Returns the error messages wrapped in this exception. */
	public List<String> getMessages() {
		return this.messages;
	}

	/** Prints all error messages to stdout, line by line. */
	public String print(String filename) {
		String err = "";
		for (String error : getMessages()) {
			err = err + "File " + filename + ", " +error + "\n";
		}
		return err;
	}
	/** Prints all error messages to stdout, line by line. */
	public String print() {
		String err = "";
		for (String error : getMessages()) {
			err = err + error;
		}
		return err;
	}
}
