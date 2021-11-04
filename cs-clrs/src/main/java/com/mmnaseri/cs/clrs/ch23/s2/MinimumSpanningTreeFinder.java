package com.mmnaseri.cs.clrs.ch23.s2;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/5/15)
 */
public interface MinimumSpanningTreeFinder<E extends WeightedEdgeDetails, V extends VertexDetails> {

  Graph<E, V> find(Graph<E, V> graph);
}
