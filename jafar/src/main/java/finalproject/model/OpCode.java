package finalproject.model;

import static finalproject.model.Operand.Type.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Code defining the type of a (non-control flow) operation.
 */

public enum OpCode {

	/**
	 * Compute operator r0 r1 r2: go to "alu"
	 */
	Compute(3, OPERATOR, REG, REG, REG),
	// Jump and Branch
	/**
	 * Jump to target address
	 * */
	Jump(0, TARGET),

	/**
	 * Branching
	 * */
	Branch(1, REG, TARGET),
	// Memory operations:
	/**
	 * Load numeric value to register
	 * */
	Load(1, ADDR, REG),
	/**
	 * Store register to mem
	 * */
	Store(1, REG, ADDR),

	/**
	 * Push value from register to stack memory
	 */
	Push(0, REG),

	/**
	 * Pop top of the stack to r and adapts stack pointer
	 */
	Pop(0, REG),


	// System operations
	/**
	 * Send read request for shMem address a
	 */
	ReadInstr(0, ADDR),

	/**
	 * Wait for reply and save it in register r
	 */
	Receive(0, REG),

	/**
	 * Write content of reg r to shMem address a
	 */
	WriteInstr(1, REG, ADDR),

	/**
	 * Request a test on address for 0 and sets it to 1 if it is.
	 * Reply will contain 1 on success, and 0 on failure.
	 */
	TestAndSet(0, ADDR),

	/**
	 * end program
	 */
	EndProg(0),

	/**
	 * nop / sleep/ do nothing
	 */
	Nop(0);



	/** The source operand types. */
	private final List<Operand.Type> sourceSig;

	/** The target operand types. */
	private final List<Operand.Type> targetSig;

	/** The operand types. */
	private final List<Operand.Type> sig;

	/** Constructs a opcode with a given sourceCount and arguments. */
	private OpCode(int sourceCount, Operand.Type... sig) {
		this.sourceSig = new ArrayList<>(sourceCount);
		for (int i = 0; i < sourceCount; i++) {
			this.sourceSig.add(sig[i]);
		}
		this.targetSig = new ArrayList<>(sig.length - sourceCount);
		for (int i = sourceCount; i < sig.length; i++) {
			this.targetSig.add(sig[i]);
		}
		this.sig = new ArrayList<>(Arrays.asList(sig));
	}

	/** Returns the number of operands. */
	public int getSigSize() {
		return getSourceCount() + getTargetCount();
	}

	/** Returns the list of expected operand types. */
	public List<Operand.Type> getSig() {
		return this.sig;
	}

	/** Returns the number of source operands. */
	public int getSourceCount() {
		return getSourceSig().size();
	}

	/** Returns the list of expected source operand types. */
	public List<Operand.Type> getSourceSig() {
		return this.sourceSig;
	}

	/** Returns the number of target operands. */
	public int getTargetCount() {
		return getTargetSig().size();
	}

	/** Returns the list of expected target operand types. */
	public List<Operand.Type> getTargetSig() {
		return this.targetSig;
	}


}
