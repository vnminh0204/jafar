package finalproject.checker_generator_compiler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

/** Class combining the information of a single scope level. */
public class SymbolTable {
	/** Current size of this scope (in bytes). 
	 * Used to calculate offsets of newly declared variables. */
	private final Stack<Integer> size;
	/** Map from declared variables to their types. */
	private final Stack<Map<String, Type>> types;
	/** Map from declared variables to their offset within the allocation
	 * record of this scope. */
	private final Stack<Map<String, Integer>> offsets;

	/** Constructs a fresh, initially start/global scope. */
	public SymbolTable() {
		this.types = new Stack<>();
		this.types.push(new LinkedHashMap<>());
		this.offsets = new Stack<>();
		this.offsets.push(new LinkedHashMap<>());
		this.size = new Stack<>();
		this.size.push(1);
	}

	/** Tests if a given identifier is declared in this scope. */
	public boolean contains(String id) {
		return this.types.peek().containsKey(id);
	}

	/** Open new scope. */
	public void openScope() {
		types.push(new LinkedHashMap<>());
		offsets.push(new LinkedHashMap<>());
		size.push(1);
	}

	/** Close the current scope with all its information. */
	public void closeScope() {
		types.pop();
		offsets.pop();
		size.pop();
	}


	/** Declares an identifier with a given type in the deepest scope level, if the identifier
	 * is not yet in this scope.
	 * @return <code>true</code> if the identifier was added into this cope;
	 * <code>false</code> if it was already declared in this cope.
	 */
	public boolean put(String id, Type type) {
		boolean result = !this.types.peek().containsKey(id);
		if (result) {
			this.types.peek().put(id, type);
			int offsetInScope = this.size.pop();
			int offsetInMem = offsetInScope;
			for (int i =0; i < this.size.size(); i++) {
				offsetInMem += (this.size.get(i)-1);
			}
			this.offsets.peek().put(id, offsetInMem);
			this.size.push(offsetInScope +  type.size());
//			System.out.println("Id " + id +" scope" + this.offsets.size() + " localoffset " + offsetInScope + ", memOffSet" + offsetInMem);

		}
		return result;
	}

	/** Returns the type of a given (presumably declared) identifier.
	 */
	public Type type(String id) {
		Type res = null;
		for (int i = types.size()-1; i >=0; i--) {
			if (this.types.get(i).containsKey(id)) {
				res = this.types.get(i).get(id);
				break;
			}
		}
		return res;
	}

	/** Returns the offset of a given (presumably declared) identifier. 
	  * with respect to the beginning of this scope's activation record.
	  * Offsets are assigned in order of declaration. 
	  */
	public Integer offset(String id) {
		Integer res = null;
		for (int i = offsets.size()-1; i >=0; i--) {
			if (this.offsets.get(i).containsKey(id)) {
				res = this.offsets.get(i).get(id);
				break;
			}
		}
		return res;
	}
}