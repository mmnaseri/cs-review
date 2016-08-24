package com.mmnaseri.cs.clrs.ch27.s0.impl;

import com.mmnaseri.cs.clrs.ch27.s0.Action;

import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (2/27/16)
 */
public class ActionWorker implements Runnable {

    private final SchedulerContext context;
    private final Action action;
    private final UUID contextId;

    public ActionWorker(SchedulerContext context, Action action) {
        this.context = context;
        contextId = context.id();
        this.action = action;
    }

    @Override
    public void run() {
        context.increase(contextId);
        action.perform();
        context.decrease(contextId);
    }

}
