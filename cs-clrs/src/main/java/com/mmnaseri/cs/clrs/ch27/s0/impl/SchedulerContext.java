package com.mmnaseri.cs.clrs.ch27.s0.impl;

import java.util.UUID;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/15, 07:46)
 */
public interface SchedulerContext<M extends ContextMetadata> {

    UUID id();

    void increase(UUID id);

    void decrease(UUID id);

    M freeze();

}
