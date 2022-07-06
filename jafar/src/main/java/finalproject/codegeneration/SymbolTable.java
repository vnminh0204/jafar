package finalproject.codegeneration;

import java.util.*;

/** Class combining the information of a single scope level. */
public class SymbolTable {
	/** Current size of this scope (in bytes).
	 * Used to calculate offsets of newly declared variables. */
	private final Stack<Integer> size;
	/** Map from declared variables to their types. */
	private final Stack<LinkedHashMap<String, Type>> types;
	/** Map from declared variables to their offset within the allocation
	 * record of this scope. */
	private final Stack<LinkedHashMap<String, Integer>> offsets;
	/** Map from declared functions to their number of args. */
	private final Map<String, ArrayList<Type>> functionsParamType;

	/** Point to the highest occupied offset in memory */
	private int maxOffSet;

	/** Keep track of variable is initialized inside function or not */
	private String startFuncName;

	/** Constructs a fresh, initially start/global scope. */
	public SymbolTable() {
		this.types = new Stack<>();
		this.types.push(new LinkedHashMap<>());
		this.offsets = new Stack<>();
		this.offsets.push(new LinkedHashMap<>());
		this.size = new Stack<>();
		this.size.push(1);
		this.functionsParamType = new LinkedHashMap<>();
		this.startFuncName = null;
		this.maxOffSet = 0;
	}

	/** @return offset hashmap of the deepest scope. */
	public LinkedHashMap<String, Integer> getScopeOffsetInfo() {
		return this.offsets.peek();
	}

	/** @return type hashmap of the deepest scope. */
	public LinkedHashMap<String, Type> getScopeTypeInfo() {
		return this.types.peek();
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

	/** @return highest occupied offset in memory
	 */
	public int getMaxOffSet() {
		return this.maxOffSet;
	}

	/** Declares an identifier with a given type in the deepest scope level, if the identifier
	 * is not yet in this scope.
	 * @return <code>true</code> if the identifier was added into this cope;
	 * <code>false</code> if it was already declared in this cope.
	 */
	public boolean put(String id, Type type) {
		boolean result = !this.types.peek().containsKey(id);
		if (result) {
			if (this.startFuncName == null) {
				this.types.peek().put(id, type);
				int offsetInScope = this.size.pop();
				int offsetInMem = offsetInScope;
				for (int i =0; i < this.size.size(); i++) {
					offsetInMem += (this.size.get(i)-1);
				}
				this.offsets.peek().put(id, offsetInMem);
				this.size.push(offsetInScope +  type.size());
				if (offsetInMem + type.size() > this.maxOffSet) {
					this.maxOffSet = offsetInMem + type.size();
				}
//				System.out.println("Id " + id +" scope" + this.offsets.size() + " localoffset " + offsetInScope + ", memOffSet" + offsetInMem);
			} else {
				this.types.peek().put(id, type);
				int offsetInScope = this.size.pop();
				this.offsets.peek().put(id, offsetInScope);
				this.size.push(offsetInScope +  type.size());
//				System.out.println("Id " + id +" in func '" + this.startFuncName + "' scope " + this.offsets.size() + " localoffset " + offsetInScope);
			}
		}
		return result;
	}

	/**
	 * Set function type
	 * @param id : function identifier
	 * @param type : function type
	 */
	public void setFuncType(String id, Type type) {
		this.types.peek().replace(id, type);
	}

	/**
	 * @param id function identifier
	 * Set and inform that current scope is function scope.
	 */
	public void setStartFuncName(String id) {
		this.startFuncName = id;
	}

	/**
	 * @param id function identifier
	 * @param paramsType list of parameters' type
	 * Set the params of a given (presumably declared) function identifier.
	 */
	public void setParamType(String id, ArrayList<Type> paramsType) {
		this.functionsParamType.put(id,paramsType);
	}

	/**
	 * @param id function identifier
	 * @return the params of a given (presumably declared) function identifier.
	 */
	public ArrayList<Type> paramsType(String id) {
		return this.functionsParamType.get(id);
	}

	/**
	 * @param id function identifier
	 * @return the type of a given (presumably declared) identifier.
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

	/**
	 * @param id identifier of variable
	 * Returns the offset of a given (presumably declared) identifier.
	 * with respect to the beginning of this scope's activation record.
	 * Offsets are assigned in order of declaration.
	 */
	public Integer getOffSet(String id) {
		Integer res = null;
		for (int i = offsets.size()-1; i >=0; i--) {
			if (this.offsets.get(i).containsKey(id)) {
				res = this.offsets.get(i).get(id);
				break;
			}
		}
		return res;
	}

	public boolean isDeclared(String id) {
		return this.types.peek().containsKey(id);
	}
}