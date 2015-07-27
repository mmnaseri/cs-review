package com.mmnaseri.cs.qa.monitor.impl;

import com.mmnaseri.cs.qa.monitor.Feature;
import com.mmnaseri.cs.qa.monitor.FeatureController;
import com.mmnaseri.cs.qa.monitor.Monitor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15, 12:42 AM)
 */
public class MonitorBuilder<D> {

    private final Set<Feature<D>> features = new HashSet<>();

    private MonitorBuilder(Set<Feature<D>> features) {
        this.features.addAll(features);
    }

    private Set<Feature<D>> getFeatures() {
        return Collections.unmodifiableSet(features);
    }

    private FeatureBuilder<D> feature(String name) {
        return new FeatureBuilder<>(this, name);
    }

    public FeatureBuilder<D> andFeature(String name) {
        return new FeatureBuilder<>(this, name);
    }

    public Monitor<D> byMonitor() {
        return new DefaultMonitor<>(features);
    }

    public BoundMonitor<D> byMonitoring(D dataStructure) {
        return new BoundMonitor<>(byMonitor(), dataStructure);
    }

    public static class FeatureBuilder<D> {

        private final String featureName;
        private final MonitorBuilder<D> monitorBuilder;

        private FeatureBuilder(MonitorBuilder<D> monitorBuilder, String featureName) {
            this.featureName = featureName;
            this.monitorBuilder = monitorBuilder;
        }

        public MonitorBuilder<D> using(FeatureController<D> controller) {
            final Feature<D> feature = new ControlledFeature<>(featureName, controller);
            final Set<Feature<D>> features = new HashSet<>(monitorBuilder.getFeatures());
            features.add(feature);
            return new MonitorBuilder<>(features);
        }

    }

    public static <D> FeatureBuilder<D> controlFeature(String featureName) {
        return new MonitorBuilder<>(Collections.<Feature<D>>emptySet()).feature(featureName);
    }

}
