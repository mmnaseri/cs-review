package com.mmnaseri.cs.clrs.common;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 3:14 AM)
 */
public interface Finder<E> {

    @SuppressWarnings("unchecked")
    int find(E needle, E... items);

}
