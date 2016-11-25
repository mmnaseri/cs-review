package com.mmnaseri.cs.ctci.ch01.p02;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 9:26 AM)
 */
public class BruteForceCheckPermutation implements CheckPermutation {

    @Override
    public boolean isPermutation(String first, String second) {
        for (int i = 0; i < first.length(); i++) {
            boolean found = false;
            for (int j = 0; j < second.length(); j++) {
                if (second.charAt(j) == first.charAt(i)) {
                    found = true;
                    second = second.substring(0, j) + second.substring(j + 1);
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

}
