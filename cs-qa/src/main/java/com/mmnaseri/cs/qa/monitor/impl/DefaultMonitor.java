package com.mmnaseri.cs.qa.monitor.impl;

import com.mmnaseri.cs.qa.monitor.Feature;
import com.mmnaseri.cs.qa.monitor.Monitor;
import com.mmnaseri.cs.qa.monitor.error.MonitorFailureException;

import java.util.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15, 12:33 AM)
 */
public class DefaultMonitor<D> implements Monitor<D> {

    private final Set<Feature<D>> features;

    public DefaultMonitor(Set<Feature<D>> features) {
        this.features = new HashSet<>();
        this.features.addAll(features);
    }

    @Override
    public Set<? extends Feature<D>> getFeatures() {
        return Collections.unmodifiableSet(features);
    }

    @Override
    public void validate(D dataStructure) {
        final Map<Feature<D>, Set<Failure<D>>> failures = new HashMap<>();
        boolean hasFailures = false;
        for (Feature<D> feature : features) {
            final Set<Failure<D>> featureFailures = feature.control(dataStructure);
            if (featureFailures != null) {
                hasFailures = hasFailures || !featureFailures.isEmpty();
                failures.put(feature, featureFailures);
            }
        }
        if (hasFailures) {
            final StringBuilder builder = new StringBuilder();
            for (Map.Entry<Feature<D>, Set<Failure<D>>> entry : failures.entrySet()) {
                if (entry.getValue().isEmpty()) {
                    continue;
                }
                builder.append("- ").append(entry.getKey().getName()).append(":\n");
                for (Failure<D> failure : entry.getValue()) {
                    builder.append(failure.getExplanation()).append(" for ").append(failure.getSource()).append("\n");
                }
            }
            throw new MonitorFailureException("There are feature failures for data structure " + dataStructure.getClass().getName() + ":\n" + builder.toString());
        }
    }

}
