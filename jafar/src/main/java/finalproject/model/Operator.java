package finalproject.model;

public class Operator extends Operand{
    private Type type;

    /**
     *
     * @param t : should be of Operator Type
     */
    public Operator(Type t) {
        super(Operand.Type.OPERATOR);
        this.type = t;
    }

    public enum Type {
        /** Addition (reg0 + reg1 => reg2). */
        Add,
        /** Subtraction (reg0 - reg1 => reg2). */
        Sub,
        /** Multiplication (reg0 * reg1 => reg2). */
        Mul,

        //Comparison operations
        /**
         * comparison r0 == r1 => r2
         */
        Equal,
        /**
         * comparison r0 != r1 => r2
         */
        NEq,
        /**
         * comparison r0 > r1 => r2
         */
        Gt,
        /**
         * comparison r0 >= r1 => r2
         */
        GtE,
        /**
         * comparison r0 < r1 => r2
         */
        Lt,
        /**
         * comparison r0 <= r1 => r2
         */
        LtE,

        //Logical operations
        /**
         * Logical And: r0 && r1 => r2
         */
        And,
        /**
         * Logical Or: r0 || r1 => r2
         */
        Or,
        /**
         * Logical Xor: r0 xor r1 => r2
         */
        Xor,
        /**
         * r0 shifts r1 bit to the left: r0 shiftleft r1 => r2
         */
        LShift,
        /**
         * r0 shift r2 bit to the right: r0 shiftright r1 => r2
         */
        RShift;
    }

    /**
     *
     * @return string representation of all attributes of reg obj
     */
    @Override
    public String toString() {
        return (""+this.type);
    }
}
