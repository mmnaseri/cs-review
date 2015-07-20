package com.mmnaseri.cs.clrs.ch15.s2;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15, 5:42 AM)
 */
public class SplitSpecification {

    private int split;
    private int operations;

    public SplitSpecification() {
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

}
