package com.mmnaseri.cs.ctci.ch01.p01;

import java.util.Arrays;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 9:17 AM)
 */
public class SortingIsUnique implements IsUnique {

    @Override
    public boolean hasDuplicates(String text) {
        final char[] chars = text.toCharArray();
        Arrays.sort(chars);
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                return true;
            }
        }
        return false;
    }

}
