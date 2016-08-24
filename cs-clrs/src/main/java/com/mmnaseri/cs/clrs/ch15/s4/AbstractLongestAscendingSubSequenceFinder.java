package com.mmnaseri.cs.clrs.ch15.s4;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15)
 */
public abstract class AbstractLongestAscendingSubSequenceFinder<E> implements LongestAscendingSubSequenceFinder<E> {

    private final Comparator<E> comparator;

    public AbstractLongestAscendingSubSequenceFinder(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    protected boolean lessThan(E first, E second) {
        return comparator.compare(first, second) < 0;
    }

}
