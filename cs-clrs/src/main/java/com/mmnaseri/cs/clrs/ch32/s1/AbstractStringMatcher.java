package com.mmnaseri.cs.clrs.ch32.s1;

import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 6:34 PM)
 */
public abstract class AbstractStringMatcher implements StringMatcher {

    @Override
    public Integer[] indexOf(String needle, String haystack) {
        Objects.requireNonNull(needle, "The needle cannot be null");
        Objects.requireNonNull(haystack, "The haystack cannot be null");
        if (needle.isEmpty()) {
            throw new IllegalArgumentException("The needle cannot be empty");
        }
        return findIndexOf(needle, haystack);
    }

    protected abstract Integer[] findIndexOf(String needle, String haystack);
}
