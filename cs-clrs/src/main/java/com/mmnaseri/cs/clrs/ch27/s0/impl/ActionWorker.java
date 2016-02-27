package com.mmnaseri.cs.clrs.ch27.s0.impl;

import com.mmnaseri.cs.clrs.ch27.s0.Action;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
public class ActionWorker implements Runnable {

    private final SchedulerContext context;
    private final Action action;

    public ActionWorker(SchedulerContext context, Action action) {
        this.context = context;
        this.action = action;
    }

    @Override
    public void run() {
        context.increase();
        action.perform();
        context.decrease();
    }

}
