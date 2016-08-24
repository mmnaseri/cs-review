package com.mmnaseri.cs.clrs.ch15.sp;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15, 9:51 PM)
 */
public class EditOperation {

    private final EditOperationType type;
    private final String target;
    private final int cost;

    public EditOperation(EditOperationType type, int cost) {
        this(type, null, cost);
    }

    public EditOperation(EditOperationType type, String target, int cost) {
        this.type = type;
        this.target = target;
        this.cost = cost;
    }

    public String getTarget() {
        return target;
    }

    public EditOperationType getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return type.name().toLowerCase() + (target != null ? " " + target : "");
    }

}
