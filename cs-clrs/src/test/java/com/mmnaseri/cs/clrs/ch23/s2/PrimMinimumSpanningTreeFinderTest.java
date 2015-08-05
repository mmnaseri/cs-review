package com.mmnaseri.cs.clrs.ch23.s2;

import com.mmnaseri.cs.clrs.ch22.s1.AdjacencyListGraph;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/5/15)
 */
public class PrimMinimumSpanningTreeFinderTest extends BaseMinimumSpanningTreeFinderTest {

    @Override
    protected <E extends WeightedEdgeDetails, V extends VertexDetails> MinimumSpanningTreeFinder<E, V> getFinder() {
        return new PrimMinimumSpanningTreeFinder<>();
    }

    protected Graph<WeightedEdgeDetails, VertexDetails> getExpectedGraph(Graph<WeightedEdgeDetails, VertexDetails> graph) {
        final Graph<WeightedEdgeDetails, VertexDetails> expected = new AdjacencyListGraph<>();
        for (int i = 0; i < graph.size(); i++) {
            expected.add();
        }
        connect(expected, 0, 1);
        connect(expected, 2, 1);
        connect(expected, 3, 2);
        connect(expected, 4, 3);
        connect(expected, 5, 2);
        connect(expected, 6, 5);
        connect(expected, 7, 6);
        connect(expected, 8, 2);
        return expected;
    }

}