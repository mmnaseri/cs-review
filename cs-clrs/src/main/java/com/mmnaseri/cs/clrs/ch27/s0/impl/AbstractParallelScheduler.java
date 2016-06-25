package com.mmnaseri.cs.clrs.ch27.s0.impl;

import com.mmnaseri.cs.clrs.ch27.s0.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/15, 07:47)
 */
public abstract class AbstractParallelScheduler implements Scheduler {

    protected final SchedulerContext context;
    protected ExecutorService executor;

    public AbstractParallelScheduler() {
        executor = startExecutor();
        context = createSchedulerContext();
    }

    protected abstract SchedulerContext createSchedulerContext();

    protected ExecutorService startExecutor() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public void spawn(Action action) {
        if (executor.isShutdown()) {
            executor = startExecutor();
        }
        executor.execute(new ActionWorker(context, action));
    }

    @Override
    public <E> ComputationResult<E> spawn(Computation<E> computation) {
        if (executor.isShutdown()) {
            executor = startExecutor();
        }
        ComputationWorker<E> worker = new ComputationWorker<>(context, computation);
        executor.execute(worker);
        return worker.getResult();
    }

    @Override
    public void loop(int from, int to, final LoopStep step) {
        if (from >= to) {
            return;
        }
        for (int i = from; i < to; i++) {
            final int stepNumber = i;
            spawn(new Action() {
                @Override
                public void perform() {
                    step.perform(stepNumber);
                }
            });
        }
        sync();
    }

    @Override
    public void sync() {
        final ContextMetadata metadata = context.freeze();
        synchronized (this) {
            do {
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            } while (metadata.current() > 0);
        }
    }

    @Override
    public void end() {
        executor.shutdown();
    }

    @Override
    public void syncAndEnd() {
        sync();
        end();
    }

}
