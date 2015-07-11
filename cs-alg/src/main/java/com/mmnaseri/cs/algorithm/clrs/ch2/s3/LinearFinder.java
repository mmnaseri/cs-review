package com.mmnaseri.cs.algorithm.clrs.ch2.s3;

import com.mmnaseri.cs.algorithm.common.Finder;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 1:23 PM)
 */
public class LinearFinder<E> implements Finder<E> {

    @SafeVarargs
    @Override
    public final int find(E needle, E... items) {
        for (int i = 0; i < items.length; i++) {
            E item = items[i];
            if (needle.equals(item)) {
                return i;
            }
        }
        return -1;
    }

}
