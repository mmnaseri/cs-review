package com.mmnaseri.cs.qa.monitor.impl;

import com.mmnaseri.cs.qa.monitor.Feature;
import com.mmnaseri.cs.qa.monitor.FeatureController;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15, 12:42 AM)
 */
public class MonitorBuilder<D> {

    private final D dataStructure;
    private final Set<Feature<D>> features = new HashSet<>();

    private MonitorBuilder(D dataStructure, Set<Feature<D>> features) {
        this.dataStructure = dataStructure;
        this.features.addAll(features);
    }

    private Set<Feature<D>> getFeatures() {
        return Collections.unmodifiableSet(features);
    }

    private D getDataStructure() {
        return dataStructure;
    }

    private FeatureBuilder<D> feature(String name) {
        return new FeatureBuilder<>(this, name);
    }

    public FeatureBuilder<D> andFeature(String name) {
        return new FeatureBuilder<>(this, name);
    }

    public BoundMonitor<D> viaMonitor() {
        return new BoundMonitor<>(new DefaultMonitor<>(features), dataStructure);
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
            return new MonitorBuilder<>(monitorBuilder.getDataStructure(), features);
        }

    }

    public static class MonitorBuilderStarter<D> {

        private final D dataStructure;

        public MonitorBuilderStarter(D dataStructure) {
            this.dataStructure = dataStructure;
        }

        public FeatureBuilder<D> givenFeature(String featureName) {
            return new MonitorBuilder<>(dataStructure, Collections.<Feature<D>>emptySet()).feature(featureName);
        }

    }

    public static <D> MonitorBuilderStarter<D> controlDataStructure(D dataStructure) {
        return new MonitorBuilderStarter<>(dataStructure);
    }

}
