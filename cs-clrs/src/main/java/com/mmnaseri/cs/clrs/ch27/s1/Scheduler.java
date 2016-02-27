package com.mmnaseri.cs.clrs.ch27.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
public interface Scheduler {

    void spawn(Action action);

    <E> ComputationResult<E> spawn(Computation<E> computation);

    void loop(int from, int to, LoopStep step);

    void sync();

    void end();

    void syncAndEnd();

}
