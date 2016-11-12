package com.mmnaseri.cs.skiena.ch04.s8;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 12:57 AM)
 */
public interface Merger<E> {

    @SuppressWarnings("unchecked")
    List<E> merge(List<E>... lists);

}
