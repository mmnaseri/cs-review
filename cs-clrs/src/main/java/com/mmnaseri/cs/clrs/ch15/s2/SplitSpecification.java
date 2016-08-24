package com.mmnaseri.cs.clrs.ch15.s2;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/20/15, 5:42 AM)
 */
public class SplitSpecification {

    private int split;
    private int operations;

    public SplitSpecification() {
        this(0, -1);
    }

    public SplitSpecification(int operations, int split) {
        this.split = split;
        this.operations = operations;
    }

    public int getSplit() {
        return split;
    }

    public void setSplit(int split) {
        this.split = split;
    }

    public int getOperations() {
        return operations;
    }

    public void setOperations(int operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return operations + "@" + split;
    }

}
