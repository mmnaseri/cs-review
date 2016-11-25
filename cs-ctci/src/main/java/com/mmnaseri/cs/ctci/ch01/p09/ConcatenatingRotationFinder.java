package com.mmnaseri.cs.ctci.ch01.p09;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 12:54 PM)
 */
public class ConcatenatingRotationFinder extends RotationFinder {

    @Override
    public boolean isRotation(String first, String second) {
        return !first.isEmpty() && first.length() == second.length() && isSubstring(second.concat(second), first);
    }

}
