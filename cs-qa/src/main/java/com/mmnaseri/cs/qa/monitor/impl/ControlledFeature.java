package com.mmnaseri.cs.qa.monitor.impl;

import com.mmnaseri.cs.qa.monitor.Feature;
import com.mmnaseri.cs.qa.monitor.FeatureController;

import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/27/15, 12:48 AM)
 */
public class ControlledFeature<D> implements Feature<D> {

    private final String name;
    private final FeatureController<D> controller;

    public ControlledFeature(String name, FeatureController<D> controller) {
        this.name = name;
        this.controller = controller;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<Failure<D>> control(D dataStructure) {
        return controller.control(dataStructure);
    }

}
