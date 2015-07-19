package com.mmnaseri.cs.clrs.ch12.sp;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/18/15, 10:09 PM)
 */
public enum Bit {

    ZERO(-1, "0"), ONE(1, "1"), NEUTRAL(0, "");

    private final int value;
    private final String representation;

    Bit(int value, String representation) {
        this.value = value;
        this.representation = representation;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return representation;
    }
}
