package com.mmnaseri.cs.ctci.ch01.p01;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 9:16 AM)
 */
public class HashSetIsUnique implements IsUnique {

    @Override
    public boolean hasDuplicates(String text) {
        final Set<Character> set = new HashSet<>();
        for (int i = 0; i < text.length(); i++) {
            final char c = text.charAt(i);
            if (set.contains(c)) {
                return true;
            }
            set.add(c);
        }
        return false;
    }

}
