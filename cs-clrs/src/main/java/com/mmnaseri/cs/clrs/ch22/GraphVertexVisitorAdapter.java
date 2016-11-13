package com.mmnaseri.cs.clrs.ch22;

import com.mmnaseri.cs.clrs.ch22.s1.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/3/15)
 */
public abstract class GraphVertexVisitorAdapter<E extends EdgeDetails, V extends VertexDetails> implements GraphVertexVisitor<E, V> {
    @Override
    public void beforeExploration(Graph<E, V> graph, Vertex<V> vertex) {

    }

    @Override
    public void afterExploration(Graph<E, V> graph, Vertex<V> vertex) {

    }

    @Override
    public void visitEdge(Graph<E, V> graph, Edge<E, V> edge) {

    }
}
