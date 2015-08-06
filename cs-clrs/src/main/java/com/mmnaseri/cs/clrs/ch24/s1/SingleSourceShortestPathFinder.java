package com.mmnaseri.cs.clrs.ch24.s1;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/6/15)
 */
public interface SingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> {

    List<Vertex<V>> find(Graph<E, V> graph, int start);

}
