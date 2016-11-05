package com.mmnaseri.cs.clrs.ch32.s2;

import com.mmnaseri.cs.clrs.ch32.s1.AbstractStringMatcher;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 7:02 PM)
 */
public class RabinKarpStringMatcher extends AbstractStringMatcher {

    private final RollingHasher hasher;

    public RabinKarpStringMatcher(RollingHasher hasher) {
        this.hasher = hasher;
    }

    @Override
    protected List<Integer> findIndexOf(String needle, String haystack) {
        final List<Integer> indices = new ArrayList<>();
        final BigInteger needleHash = hasher.hash(needle);
        String portion = haystack.substring(0, needle.length());
        BigInteger current = hasher.hash(portion);
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (Objects.equals(needleHash, current)) {
                boolean found = true;
                for (int j = 0; j < needle.length(); j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    indices.add(i);
                }
            }
            if (i + needle.length() < haystack.length()) {
                final char next = haystack.charAt(i + needle.length());
                current = hasher.roll(portion, current, next);
                portion = portion.substring(1) + next;
            }
        }
        return indices;
    }

}
