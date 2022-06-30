package finalproject.model;

public class Target extends Operand {
    public enum TargetType {
        /**
         * Ind type: value of new program counter in register r
         */
        Ind,

        /**
         * Rel type: increase current program counter with n
         */
        Rel,

        /**
         * Abs: instruction n
         */
        Abs;
    }

    private final TargetType type;
    private final Reg register;
    private final int value;
    private final String label;
    /**
     * target is ind
     * @param r : register storing pc
     */
    public Target(Reg r) {
        super(Type.TARGET);
        this.type = TargetType.Ind;
        register = r;
        value = -1;
        this.label = null;
    }
    

    /**
     * target is abs or rel
     * @param tt : either abs or rel
     * @param n : value to increase/ absolute value to jump to.
     */
    public Target(TargetType tt, int n) {
        super(Type.TARGET);
        this.type = tt;
        this.value = n;
        this.label = null;
        this.register = null;
    }

    public Target(TargetType tt, String lb) {
        super(Type.TARGET);
        this.type = tt;
        this.label = lb;
        this.value = -1;
        this.register = null;
    }

    /**
     *
     * @return this register
     */
    public Reg getRegister() {
        return register;
    }

    /**
     *
     * @return this value
     */
    public int getValue() {
        return value;
    }

    public TargetType getTType() {
        return type;
    }

    public String getLabel() {
        return this.label;
    }

    /**
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        String res;
        switch (this.getTType()) {
            case Ind:
                res = "(" + this.getTType() + " " + register + ")";
            default:
                res = "(" + this.getTType() + " " + value + ")";
        }
        return res;
    }
}
