package com.mmnaseri.cs.clrs.ch22.s1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/3/15)
 */
public abstract class AbstractGraph<E extends EdgeDetails, V extends VertexDetails> implements Graph<E, V> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public List<Vertex<V>> getVertices() {
        final List<Vertex<V>> vertices = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            vertices.add(getVertex(i));
        }
        return vertices;
    }

    @Override
    public List<Vertex<V>> getNeighbors(int index) {
        final List<Vertex<V>> vertices = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            final Edge<E, V> edge = getEdge(index, i);
            if (edge != null) {
                vertices.add(edge.getTo());
            }
        }
        return vertices;
    }

    @Override
    public List<Vertex<V>> getNeighbors(Vertex<V> vertex) {
        return getNeighbors(Objects.requireNonNull(vertex, "Vertex must not be null").getIndex());
    }

}
