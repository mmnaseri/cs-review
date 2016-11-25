package com.mmnaseri.cs.ctci.ch01.p09;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 12:53 PM)
 */
public abstract class RotationFinder {

    protected boolean isSubstring(String haystack, String needle) {
        return haystack.contains(needle);
    }

    public abstract boolean isRotation(String first, String second);

}
