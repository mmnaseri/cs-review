package com.mmnaseri.cs.clrs.ch15.s5;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15, 9:19 PM)
 */
public class SplitSpecification {

    private final int root;
    private final double expectation;
    private final double weight;

    public SplitSpecification(int root, double expectation, double weight) {
        this.root = root;
        this.expectation = expectation;
        this.weight = weight;
    }

    public int getRoot() {
        return root;
    }

    public double getExpectation() {
        return expectation;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return root + "," + expectation;
    }
}
