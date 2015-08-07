package com.mmnaseri.cs.clrs.ch25;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.common.Matrix;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/7/15)
 */
public interface AllPairsShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> {

    Matrix<Integer> find(Graph<E, V> graph);

}
