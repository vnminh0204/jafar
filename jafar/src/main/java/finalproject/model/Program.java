package finalproject.model;

import java.util.*;

/** SPRIL program. */
public class Program {
	/** Indexed list of all instructions in the program. */
	private final List<Instr> instrList;
	/**
	 * Indexed list of all operations in the program.
	 * This is the flattened list of instructions.
	 */
	private final List<Op> opList;
	/** Mapping from labels defined in the program to corresponding
	 * index locations.
	 */
	private final Map<Label, Integer> labelMap;
	/** (Partial) mapping from symbolic constants used in the program
	 * to corresponding numeric values. */
	private HashMap<String, Label> labelHashMap;

	/** Current line number, start from 0 */
	protected int lineNum;

	/** Current label count (tag), start from 0 */
	protected int labelCount;

	/** Program's thread id */
	private int tid;

	/** Creates a program with an initially empty instruction list. */
	public Program(int tid) {
		this.instrList = new ArrayList<>();
		this.opList = new ArrayList<>();
		this.labelMap = new LinkedHashMap<>();
		this.lineNum = 0;
		this.labelCount = 0;
		this.labelHashMap = new HashMap<>();
		this.tid = tid;
	}

	/** @param m : updated hashmap for label */
	public void setLabelHashMap(HashMap<String, Label> m) {
		this.labelHashMap = m;
	}
	/** Adds an instruction to the instruction list of this program.
	 * @throws IllegalArgumentException if the instruction has a known label
	 */
	public void addInstr(Instr instr) {
		instr.setProgram(this);
		instr.setLine(this.opList.size());
		if (instr.hasLabel()) {
			registerLabel(instr);
		}
		this.instrList.add(instr);
		for (Op op : instr) {
			this.opList.add(op);
		}
	}

	/** Convert all Operator with Target to a label to make it Target to a line number */
	public void convert() {
		for (int i = 0; i < this.getInstr().size(); i++) {
			Instr ins = this.getInstr().get(i);
			if (ins instanceof Op) {
				String l = ((Op) ins).getsLabel();
//				System.out.println(l + "" + ((Op) ins).getArgs());
				List<Operand> args = ((Op) ins).getArgs();
				for (int j = 0; j < args.size(); j++) {
					Operand a = args.get(j);
					if (a instanceof Target) {
						String al = ((Target) a).getLabel();
						if (((Target) a).getLabel() != null) {
							args.set(j, new Target(Target.TargetType.Abs, this.labelHashMap.get(al).getLineNumber()));
						}
					}
				}
			}
		}
	}

	/** Registers the label of a given instruction. */
	void registerLabel(Instr instr) {
		Label label = instr.getLabel();
		Integer loc = this.labelMap.get(label);
		if (loc != null) {
			throw new IllegalArgumentException(String.format(
					"Label %s already occurred at location %d", label, loc));
		}
		this.labelMap.put(label, instr.getLine());
	}

	/** Returns the current list of instructions of this program. */
	public List<Instr> getInstr() {
		return Collections.unmodifiableList(this.instrList);
	}

	/** Returns the flattened list of operations in this program. */
	public List<Op> getOps() {
		return Collections.unmodifiableList(this.opList);
	}

	/** Returns the operation at a given line number. */
	public Op getOpAt(int line) {
		return this.opList.get(line);
	}

	/** Returns the size of the program, in number of operations. */
	public int size() {
		return this.opList.size();
	}

	/** Get Label instance from a label name */
	public Label getLabel(String lb) {
		return labelHashMap.get(lb);
	}

	/** Create and return a new label name in form of "Label i" with i is an integer start from 0 */
	public String getNewLabel() {
		return "Label: " + (labelCount++);
	}

	/** Put label to a label -> Label instance HashMap */
	public void putLabel(String label) {
		this.labelHashMap.put(label, new Label(label, lineNum));
	}

	/** Increase line count */
	public void increaseLine() {
		this.lineNum++;
	}

	/** Get current line number */
	public int getLineNum() {
		return this.lineNum;
	}

	/** Pretty print, in form of "prog1" "prog2" .. to make it suitable with SPRIL" */
	public String pprint() {
		return "prog" + this.tid;
	}

}
