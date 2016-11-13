package com.mmnaseri.cs.skiena.ch07.s1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:18 PM)
 */
public interface BacktrackEventListener<E, D> {

    void onBeforeBacktrack(BacktrackingContext<E, D> context);

    void onAfterBacktrack(BacktrackingContext<E, D> context);

}
