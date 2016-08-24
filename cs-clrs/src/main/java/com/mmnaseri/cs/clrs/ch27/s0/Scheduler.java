package com.mmnaseri.cs.clrs.ch27.s0;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
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
