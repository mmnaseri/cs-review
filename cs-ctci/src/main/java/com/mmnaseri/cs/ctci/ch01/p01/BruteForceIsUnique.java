package com.mmnaseri.cs.ctci.ch01.p01;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 9:18 AM)
 */
public class BruteForceIsUnique implements IsUnique {

    @Override
    public boolean hasDuplicates(String text) {
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < i - 1; j++) {
                if (text.charAt(j) == text.charAt(i)) {
                    return true;
                }
            }
        }
        return false;
    }

}
