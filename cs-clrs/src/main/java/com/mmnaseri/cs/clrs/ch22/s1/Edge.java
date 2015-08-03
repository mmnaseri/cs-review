package com.mmnaseri.cs.clrs.ch22.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 8:02 PM)
 */
public class Edge<E extends EdgeDetails, V extends VertexDetails> extends GraphElement {

    private Vertex<V> from;
    private Vertex<V> to;
    private E details;

    public Vertex<V> getFrom() {
        return from;
    }

    public void setFrom(Vertex<V> from) {
        this.from = from;
    }

    public Vertex<V> getTo() {
        return to;
    }

    public void setTo(Vertex<V> to) {
        this.to = to;
    }

    public E getDetails() {
        return details;
    }

    public void setDetails(E details) {
        this.details = details;
    }

}
