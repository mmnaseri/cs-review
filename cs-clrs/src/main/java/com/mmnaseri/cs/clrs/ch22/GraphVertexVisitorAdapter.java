package com.mmnaseri.cs.clrs.ch22;

import com.mmnaseri.cs.clrs.ch22.s1.Edge;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/3/15)
 */
public abstract class GraphVertexVisitorAdapter<E extends EdgeDetails, V extends VertexDetails>
    implements GraphVertexVisitor<E, V> {
  @Override
  public void beforeExploration(Graph<E, V> graph, Vertex<V> vertex) {}

  @Override
  public void afterExploration(Graph<E, V> graph, Vertex<V> vertex) {}

  @Override
  public void visitEdge(Graph<E, V> graph, Edge<E, V> edge) {}
}
