package com.mmnaseri.cs.clrs.ch25;

import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/9/15, 6:01 PM)
 */
public class ShortestPathMetadata<V extends VertexDetails> {

    private final int distance;
    private final Vertex<V> predecessor;

    public ShortestPathMetadata(int distance, Vertex<V> predecessor) {
        this.distance = distance;
        this.predecessor = predecessor;
    }

    public int getDistance() {
        return distance;
    }

    public Vertex<V> getPredecessor() {
        return predecessor;
    }

    @Override
    public String toString() {
        return String.valueOf(distance);
    }
}
