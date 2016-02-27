package com.mmnaseri.cs.clrs.ch27.s0.impl;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
class SchedulerContext {

    private final AtomicInteger active = new AtomicInteger(0);

    public void increase() {
        active.incrementAndGet();
    }

    public void decrease() {
        active.decrementAndGet();
    }

    public int current() {
        return active.get();
    }


}
