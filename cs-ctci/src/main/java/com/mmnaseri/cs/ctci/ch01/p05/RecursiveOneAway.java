package com.mmnaseri.cs.ctci.ch01.p05;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 11:08 AM)
 */
public class RecursiveOneAway implements OneAway {

    @Override
    public boolean oneAway(String first, String second) {
        return oneAway(first, second, false);
    }

    private boolean oneAway(String first, String second, boolean edited) {
        if (first.isEmpty() && second.isEmpty()) {
            return true;
        }
        if (first.isEmpty() && !second.isEmpty() || !first.isEmpty() && second.isEmpty()) {
            return !edited;
        }
        if (first.charAt(0) == second.charAt(0)) {
            return oneAway(first.substring(1), second.substring(1), edited);
        } else if (!edited) {
            if (first.length() == second.length()) {
                return oneAway(first.substring(1), second.substring(1), true);
            } else {
                if (first.length() < second.length()) {
                    return oneAway(first, second.substring(1), true);
                } else {
                    return oneAway(first.substring(1), second, true);
                }
            }
        }
        return false;
    }

}
