package com.mmnaseri.cs.algorithm.clrs.ch2.sp;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 1:03 PM)
 */
public class BruteForceInversionCounter<E extends Comparable<E>> implements InversionCounter<E> {

    private final Comparator<E> comparator;

    public BruteForceInversionCounter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @SafeVarargs
    @Override
    public final int count(E... items) {
        int inversions = 0;
        for (int i = 0; i < items.length; i++) {
            for (int j = i + 1; j < items.length; j++) {
                if (comparator.compare(items[i], items[j]) > 0) {
                    inversions ++;
                }
            }
        }
        return inversions;
    }
}
