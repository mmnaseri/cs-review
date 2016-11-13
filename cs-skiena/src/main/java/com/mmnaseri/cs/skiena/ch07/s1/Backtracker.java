package com.mmnaseri.cs.skiena.ch07.s1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:13 PM)
 */
public interface Backtracker<E, D> {

    void backtrack(D data, BacktrackHandler<E, D> callback);

    void backtrack(BacktrackHandler<E, D> callback);

}
