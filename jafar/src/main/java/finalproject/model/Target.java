package finalproject.model;

/** Target operand
 */
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
    /** Type of target */
    private final TargetType type;
    /** Register value of Ind target */
    private final Reg register;
    /** Numeric value of Rel/Abs target */
    private final int value;
    /** String label of target */
    private final String label;
    /**
     * target is ind
     * @param r : register storing pc
     */
    public Target(TargetType tt, Reg r) {
        super(Type.TARGET);
        this.type = tt;
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

    /**
     * target is NOP in case of label
     * @param tt : any type
     * @param lb : corresponding label.
     */
    public Target(TargetType tt, String lb) {
        super(Type.TARGET);
        this.type = tt;
        this.label = lb;
        this.value = -1;
        this.register = null;
    }

    /**
     * @return target's register value in case of Ind type
     */
    public Reg getRegister() {
        return register;
    }

    /**
     * @return target's numeric value in case of Rel/Abs type
     */
    public int getValue() {
        return value;
    }

    /**
     * @return target's type
     */
    public TargetType getTType() {
        return type;
    }

    /**
     * @return target's label
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * @return string representation of the object
     */
    @Override
    public String toString() {
        String res;
        switch (this.getTType()) {
            case Ind:
                res = "(" + this.getTType() + " " + register + ")";
                break;
            default:
                res = "(" + this.getTType() + " " + value + ")";
        }
        return res;
    }
}
