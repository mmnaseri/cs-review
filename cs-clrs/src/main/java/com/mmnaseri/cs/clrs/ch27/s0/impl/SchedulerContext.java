package com.mmnaseri.cs.clrs.ch27.s0.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
class SchedulerContext {

    private final AtomicReference<ContextMetadata> current = new AtomicReference<>();
    private final Map<UUID, ContextMetadata> contextMap = new ConcurrentHashMap<>();

    SchedulerContext() {
        switchContext();
    }

    private ContextMetadata switchContext() {
        final ContextMetadata metadata = new ContextMetadata();
        current.set(metadata);
        contextMap.put(metadata.id(), metadata);
        return metadata;
    }

    UUID id() {
        return current.get().id();
    }

    void increase(UUID id) {
        System.out.printf("[%s] INCREASE%n", id);
        final ContextMetadata metadata = contextMap.get(id);
        if (metadata == null) {
            throw new IllegalStateException("Increase request is out of context");
        }
        metadata.increase();
    }

    void decrease(UUID id) {
        System.out.printf("[%s] DECREASE%n", id);
        final ContextMetadata metadata = contextMap.get(id);
        if (metadata == null) {
            throw new IllegalStateException("Decrease request is out of context");
        }
        metadata.decrease();
    }

    ContextMetadata freeze() {
        final ContextMetadata metadata = current.get();
        metadata.freeze();
        switchContext();
        return metadata;
    }

    static class ContextMetadata {

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

        int current() {
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
