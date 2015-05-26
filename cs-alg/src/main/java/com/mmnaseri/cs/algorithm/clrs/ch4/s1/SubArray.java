package com.mmnaseri.cs.algorithm.clrs.ch4.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 1:52 AM)
 */
public class SubArray {

    private final int start;
    private final int end;
    private final int sum;

    public SubArray(int start, int end, int sum) {
        this.start = start;
        this.end = end;
        this.sum = sum;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "(" + start + "," + end + ") = " + sum;
    }

}
