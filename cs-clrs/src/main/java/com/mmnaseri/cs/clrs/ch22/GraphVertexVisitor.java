package com.mmnaseri.cs.clrs.ch22;

import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 11:11 PM)
 */
public interface GraphVertexVisitor<E extends EdgeDetails, V extends VertexDetails> {

    void beforeExploration(Graph<E, V> graph, Vertex<V> vertex);

    void afterExploration(Graph<E, V> graph, Vertex<V> vertex);

}
