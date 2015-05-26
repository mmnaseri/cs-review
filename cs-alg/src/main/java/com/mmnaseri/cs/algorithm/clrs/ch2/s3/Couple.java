package com.mmnaseri.cs.algorithm.clrs.ch2.s3;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 1:50 AM)
 */
public class Couple {

    private final int first;
    private final int second;

    public Couple(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return first + "+" + second + "=" + (first + second);
    }

}
