package com.mmnaseri.cs.clrs.ch22;

import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 11:11 PM)
 */
public interface GraphVisitor<E extends EdgeDetails, V extends VertexDetails> {

  Graph<E, V> visit(Graph<E, V> graph, GraphVertexVisitor<E, V> visitor);

  Graph<E, V> visit(Graph<E, V> graph, int start, GraphVertexVisitor<E, V> visitor);
}
