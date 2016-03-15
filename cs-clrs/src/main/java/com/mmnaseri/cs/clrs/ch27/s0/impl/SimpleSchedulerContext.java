package com.mmnaseri.cs.clrs.ch27.s0.impl;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/15, 07:51)
 */
class SimpleSchedulerContext implements SchedulerContext<SimpleSchedulerContext.SimpleContextMetadata> {

    private final UUID uuid =  UUID.randomUUID();
    private final AtomicInteger count = new AtomicInteger(0);
    private final SimpleContextMetadata metadata = new SimpleContextMetadata(count);

    @Override
    public UUID id() {
        return uuid;
    }

    @Override
    public void increase(UUID id) {
        check(id);
        count.incrementAndGet();
    }

    @Override
    public void decrease(UUID id) {
        check(id);
        count.decrementAndGet();
    }

    @Override
    public SimpleContextMetadata freeze() {
        return metadata;
    }

    protected void check(UUID id) {
        if (!uuid.equals(id)) {
            throw new IllegalArgumentException("Expected " + uuid + " but received " + id);
        }
    }

    static class SimpleContextMetadata implements ContextMetadata {

        private AtomicInteger count;

        public SimpleContextMetadata(AtomicInteger count) {
            this.count = count;
        }

        @Override
        public int current() {
            return count.get();
        }

    }

}
