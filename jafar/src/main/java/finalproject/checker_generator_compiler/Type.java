package finalproject.checker_generator_compiler;

/** Jafar data type. */
abstract public class Type {
	/** The singleton instance of the {@link Bool} type. */
	public static final Type BOOL = new Bool();
	/** The singleton instance of the {@link Int} type. */
	public static final Type INT = new Int();
	/** The singleton instance of the {@link Void} type. */
	public static final Type VOID = new Void();
	public static final int INT_SIZE = 1;
	public static final int VOID_SIZE = 0;
	private final TypeKind kind;

	/** Constructor for subclasses. */
	protected Type(TypeKind kind) {
		this.kind = kind;
	}

	/** Returns the kind of this type. */
	public TypeKind getKind() {
		return this.kind;
	}

	/** returns the size (in bytes) of a value of this type. */
	abstract public int size();

	/** Representation of the Jafar Void type. */
	static public class Void extends Type {
		private Void() {
			super(TypeKind.VOID);
		}

		@Override
		public int size() {
			return VOID_SIZE;
		}

		@Override
		public String toString() {
			return "Void";
		}
	}

	/** Representation of the Jafar Boolean type. */
	static public class Bool extends Type {
		private Bool() {
			super(TypeKind.BOOL);
		}

		@Override
		public int size() {
			return INT_SIZE;
		}

		@Override
		public String toString() {
			return "Boolean";
		}
	}

	/** Representation of the Jafar Integer type. */
	static public class Int extends Type {
		private Int() {
			super(TypeKind.INT);
		}

		@Override
		public int size() {
			return INT_SIZE;
		}

		@Override
		public String toString() {
			return "Integer";
		}
	}

	/** Representation of Jafar Array types. */
	static public class Array extends Type {
		private final int lower;
		private final int upper;
		private final int numElems;
		private final Type elemType;
		private int numDimens;

		public Array(int numElems, Type elemType) {
			super(TypeKind.ARRAY);
			assert numElems >= 0;
			this.upper = numElems-1;
			this.lower = 0;
			this.numElems = numElems;
			this.elemType = elemType;
			this.numDimens = 1;
			Type temp = elemType;
			while (temp instanceof Type.Array) {
				numDimens++;
				temp = ((Array) temp).elemType;
			}
		}

		/** Returns the lower bound of this array type. */
		public int getLower() {
			return this.lower;
		}

		/** Returns the upper bound of this array type. */
		public int getUpper() {
			return this.upper;
		}

		/** Return the size of this array type. */
		public int getNumDimens() {
			return this.numDimens;
		}

		/** Return the size of this array type. */
		public int getNumElems() {
			return this.numElems;
		}

		/** Returns the element bound of this array type. */
		public Type getElemType() {
			return this.elemType;
		}

		@Override
		public int size() {
			return this.numElems*this.elemType.size();
		}

		@Override
		public String toString() {
			return "[" + this.size() + "]"
					+ this.elemType;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + this.elemType.hashCode();
			result = prime * result + this.lower;
			result = prime * result + this.upper;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Array)) {
				return false;
			}
			Array other = (Array) obj;
			if (!this.elemType.equals(other.elemType)) {
				return false;
			}
			if (this.lower != other.lower) {
				return false;
			}
			if (this.upper != other.upper) {
				return false;
			}
			return true;
		}

	}
}
