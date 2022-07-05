package finalproject.checker_generator_compiler;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/** Class holding the results of the Simple Jafar checker. */
public class Result {
	/** Mapping from function to parameter. */
	private final HashMap<String,Integer> numParams = new HashMap<>();
	/** Mapping from function to hashmap of parameter and local data offset. */
	private final HashMap<String, LinkedHashMap<String, Integer>> funcOffSetData = new HashMap<>();
	/** Mapping from function to hashmap of parameter and local data type. */
	private final HashMap<String, LinkedHashMap<String, Type>> funcTypeData = new HashMap<>();
	/** Mapping from expressions to types. */
	private final ParseTreeProperty<Type> types = new ParseTreeProperty<>();

	/** Mapping from variables to coordinates. */
	private final ParseTreeProperty<Integer> offsets = new ParseTreeProperty<>();

	/** Highest occupied offset in memory */
	private int maxOffSet;

	/** Mapping from parse tree node to thread number */
	private ParseTreeProperty<Integer> threadMap = new ParseTreeProperty<>();

	/** Mapping from a threadId to its opener threadId*/
	private HashMap<Integer, Integer> parentIDMap = new HashMap<>();

	/** Mapping from a shared variable name to its shared memory location */
	private HashMap<String, Integer> sharedOffset = new HashMap<>();
	/** Mapping from a shared variable name to its type */
	private HashMap<String, Type> sharedType = new HashMap<>();
	/** Current shared Adress, start from 13*/
	private int sharedAddress;

	/** Current number of Threads*/
	private int noThreads;

	/** MAX number of threads*/
	private final int MAX_THREADS = 6;

	/** Adds an association from function to its data offset. */
	public void setFuncTypeData(String funcID, LinkedHashMap<String, Type> scopeInfo) {
		this.funcTypeData.put(funcID, scopeInfo);
	}

	/** Constructor for Result with initilized start shared memory offset and number of threads */
	public Result() {
		this.noThreads = 0;
		this.sharedAddress = 17;
	}

	/**
	 * @param maxOffSet current highest offset
	 * Update max offset */
	public void setMaxOffset(int maxOffSet) {
		this.maxOffSet = maxOffSet;
	}

	/**
	 * @return current highest offset */
	public int getMaxOffset() {
		return this.maxOffSet;
	}

	/** Adds an association from a parse tree node containing a
	 * variable reference to the offset
	 * of that variable. */
	public void setOffset(ParseTree node, int offset) {
		this.offsets.put(node, offset);
	}

	/** Returns the declaration offset of the variable
	 * accessed in a given parse tree node. */
	public int getOffset(ParseTree node) {
		return this.offsets.get(node);
	}

	/** Returns the function scope info associated
	 * with a given functionID. */
	public LinkedHashMap<String, Type> getFuncTypeData(String funcID) {
		return this.funcTypeData.get(funcID);
	}

	/** Adds an association from function to its data offset. */
	public void setFuncOffSetData(String funcID, LinkedHashMap<String, Integer> scopeInfo) {
		this.funcOffSetData.put(funcID, scopeInfo);
	}

	/** Returns the function scope info associated
	 * with a given functionID. */
	public LinkedHashMap<String, Integer> getFuncOffSetData(String funcID) {
		return this.funcOffSetData.get(funcID);
	}

	/** Adds an association from parse tree node of function to its parameters. */
	public void setParams(String funcID, ArrayList<Type> paramsType) {
		this.numParams.put(funcID, paramsType.size());
	}

	/** Returns the list of parameters' type associated
	 * with a given parse tree node. */
	public Integer getParams(String funcID) {
		return this.numParams.get(funcID);
	}

	/** Adds an association from a parse tree expression, type,
	 * or assignment target node to the corresponding (inferred) type. */
	public void setType(ParseTree node, Type type) {
		this.types.put(node, type);
	}

	/** Returns the type associated with a given parse tree node. */
	public Type getType(ParseTree node) {
		return this.types.get(node);
	}

	/** Add an association from a parse tree node to the corresponding threadID */
	public void setThreadId (ParseTree node, int id) {
		this.threadMap.put(node, id);
	}
	/** Return thread id associated with a given parse tree node */
	public int getThreadId (ParseTree node) {
		return this.threadMap.get(node);
	}

	/** Create a new thread number, do not allow if it exceeds max amount of thread
	 *
	 * @return current thread number after increased
	 */
	public int createNewThread() {
		if (this.noThreads >= MAX_THREADS) {
			System.out.println("max thread reached");
		}
		this.noThreads++;
		return this.noThreads;
	}

	/**
	 * Get current number of threads.
	 */
	public int getNoThreads() {
		return this.noThreads;
	}

	/**
	 * Set a parent of thread with number is id to pid
	 * @param id Integer
	 * @param pid Integer
	 */
	public void setParentID (int id, int pid) {
		this.parentIDMap.put(id, pid);
	}

	/**
	 * Get parent of thread with number id.
	 * @param id Integer, thread id
	 * @return Integer, thread id of the parent
	 */
	public int getParentID (int id) {
		if (this.parentIDMap.keySet().contains(id))
			return this.parentIDMap.get(id);
		else
			return 0;
	}

	/** Get shared memory location reserved for a shared variable */
	public int getSharedOffset(String id) {
		return this.sharedOffset.get(id);
	}

	/** Get type of a shared variable */
	public Type getSharedType(String id) {
		return this.sharedType.get(id);
	}

	/** Create new shared variable and reserve a shared memory location for it */
	public boolean putSharedVar(String id, Type type) {
		if (this.sharedOffset.containsKey(id)){
			return false;
		}
		sharedOffset.put(id, sharedAddress);
		sharedType.put(id, type);
		sharedAddress += type.size();

		return true;
	}

	/** Check if a variable is shared variable */
	public boolean isShared(String id) {
		return this.sharedOffset.containsKey(id);
	}

	public boolean isDeclared(String id) {
		return this.sharedOffset.containsKey(id);
	}

}
