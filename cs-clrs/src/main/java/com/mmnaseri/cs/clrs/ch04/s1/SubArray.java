package com.mmnaseri.cs.clrs.ch04.s1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
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

    public int length() {
        return end - start;
    }

    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public String toString() {
        return "(" + start + "," + end + ") = " + sum;
    }

}
