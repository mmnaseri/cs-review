package com.mmnaseri.cs.qa.monitor;

import com.mmnaseri.cs.qa.monitor.impl.Failure;

import java.util.Set;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15, 12:41 AM)
 */
public interface FeatureController<D> {

    Set<Failure<D>> control(D dataStructure);

}
