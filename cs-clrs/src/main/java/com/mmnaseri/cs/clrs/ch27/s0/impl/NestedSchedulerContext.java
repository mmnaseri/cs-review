package com.mmnaseri.cs.clrs.ch27.s0.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (2/27/16)
 */
class NestedSchedulerContext implements SchedulerContext<NestedSchedulerContext.NestedContextMetadata> {

    private final AtomicReference<NestedContextMetadata> current = new AtomicReference<>();
    private final Map<UUID, NestedContextMetadata> contextMap = new ConcurrentHashMap<>();

    NestedSchedulerContext() {
        switchContext();
    }

    private NestedContextMetadata switchContext() {
        final NestedContextMetadata metadata = new NestedContextMetadata();
        current.set(metadata);
        contextMap.put(metadata.id(), metadata);
        return metadata;
    }

    @Override
    public UUID id() {
        return current.get().id();
    }

    @Override
    public void increase(UUID id) {
        final NestedContextMetadata metadata = contextMap.get(id);
        if (metadata == null) {
            throw new IllegalStateException("Increase request is out of context");
        }
        metadata.increase();
    }

    @Override
    public void decrease(UUID id) {
        final NestedContextMetadata metadata = contextMap.get(id);
        if (metadata == null) {
            throw new IllegalStateException("Decrease request is out of context");
        }
        metadata.decrease();
    }

    @Override
    public NestedContextMetadata freeze() {
        final NestedContextMetadata metadata = current.get();
        metadata.freeze();
        switchContext();
        return metadata;
    }

    static class NestedContextMetadata implements ContextMetadata {

        private final AtomicInteger active = new AtomicInteger(0);
        private final UUID id = UUID.randomUUID();
        private boolean frozen = false;

        private void increase() {
            active.incrementAndGet();
        }

        private void decrease() {
            active.decrementAndGet();
        }

        private void checkFrozen() {
            if (frozen) {
                throw new IllegalAccessError("Attempting to modify a frozen context");
            }
        }

        public int current() {
            return active.get();
        }

        private UUID id() {
            return id;
        }

        private void freeze() {
            checkFrozen();
            frozen = true;
        }

    }

}
