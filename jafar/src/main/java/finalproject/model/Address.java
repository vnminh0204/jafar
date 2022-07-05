package finalproject.model;

public class Address extends Operand {
    public enum Type {
        /**
         * Numerical value
         */
        ImmValue,

        /**
         * address in local/ shared memory
         */
        DirAddr,

        /**
         * register contain address
         */
        IndAddr,
        /**
         * IO output
         */
        numberIO;
    }

    /** Address Type */
    private Type type;

    /** Register value in case of IndAddr type */
    private Reg register;

    /** Numeric value in case of DirAddr/ImmValue type */
    private int value;

    /**
     *
     * @param type : IndAddr type
     * @param register : register containing address
     */
    public Address(Type type, Reg register) {
        super(Operand.Type.ADDR);
        this.type = type;
        this.register = register;
        this.value  = -1;
    }

    /**
     *
     * @param type : should be ImmValue or DirAddr
     * @param value : address or numerical value
     */
    public Address(Type type, int value) {
        super(Operand.Type.ADDR);
        this.type = type;
        this.register = null;
        this.value = value;
    }

    /**
     * @return string representation of all attributes of address obj
     */
    @Override
    public String toString() {
        String res;
        switch (type) {
            case ImmValue:
            case DirAddr:
                if (value >= 0) {
                    res = "(" + type + " " + value + ")";
                } else {
                    res = "(" + type + " (" + value + "))";
                }
                break;
            case IndAddr:
                res = "(" + type + " " + register + ")";
                break;
            case numberIO:
                res = "numberIO";
                break;
            default:
                res = "ERR: Null Address Type";
        }
        return res;
    }

    /**
     * get value
     * @return address's value
     */
    public int getValue() {
        return value;
    }
}
