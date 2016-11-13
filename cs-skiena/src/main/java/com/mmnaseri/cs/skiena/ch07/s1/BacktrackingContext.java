package com.mmnaseri.cs.skiena.ch07.s1;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:14 PM)
 */
public interface BacktrackingContext<E, D> {

    void stop();

    boolean isStopped();

    List<E> current();

    int size();

    D data();

}
