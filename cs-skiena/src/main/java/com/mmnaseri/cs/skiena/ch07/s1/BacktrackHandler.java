package com.mmnaseri.cs.skiena.ch07.s1;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:15 PM)
 */
public interface BacktrackHandler<E, D> {

    boolean isSolution(BacktrackingContext<E, D> context);

    void process(BacktrackingContext<E, D> context);

    List<E> generateCandidates(BacktrackingContext<E, D> context);

}
