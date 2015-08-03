package com.mmnaseri.cs.clrs.ch22.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 8:02 PM)
 */
public class Vertex<V extends VertexDetails> {

    private V details;
    private int index;

    public V getDetails() {
        return details;
    }

    public void setDetails(V details) {
        this.details = details;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
