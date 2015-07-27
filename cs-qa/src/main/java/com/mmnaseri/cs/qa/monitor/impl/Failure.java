package com.mmnaseri.cs.qa.monitor.impl;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15, 12:35 AM)
 */
public class Failure<D> {

    private final Object source;
    private final D dataStructure;
    private final String explanation;

    public Failure(Object source, D dataStructure, String explanation) {
        this.source = source;
        this.dataStructure = dataStructure;
        this.explanation = explanation;
    }

    public Object getSource() {
        return source;
    }

    public D getDataStructure() {
        return dataStructure;
    }

    public String getExplanation() {
        return explanation;
    }

}
