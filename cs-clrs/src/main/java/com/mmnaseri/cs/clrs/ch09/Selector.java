package com.mmnaseri.cs.clrs.ch09;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/11/15, 5:46 PM)
 */
public interface Selector<E extends Comparable<E>> {

    @SuppressWarnings("unchecked")
    E select(int order, E... items);

}
