package com.mmnaseri.cs.qa.monitor.impl;

import com.mmnaseri.cs.qa.monitor.Monitor;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15, 12:51 AM)
 */
public class BoundMonitor<D> {

    private final Monitor<D> monitor;
    private final D dataStructure;

    public BoundMonitor(Monitor<D> monitor, D dataStructure) {
        this.monitor = monitor;
        this.dataStructure = dataStructure;
    }

    public void validate() {
        monitor.validate(dataStructure);
    }

}
