package com.mmnaseri.cs.clrs.ch23.s2;

import com.mmnaseri.cs.clrs.ch22.GraphSamples;
import com.mmnaseri.cs.clrs.ch22.s1.AdjacencyListGraph;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/5/15)
 */
public abstract class BaseMinimumSpanningTreeFinderTest {

    protected abstract <E extends WeightedEdgeDetails, V extends VertexDetails> MinimumSpanningTreeFinder<E, V> getFinder();

    @Test
    public void testAgainstSample() throws Exception {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = GraphSamples.sampleWeightedGraph();
        final Graph<WeightedEdgeDetails, VertexDetails> expected = new AdjacencyListGraph<>();
        for (int i = 0; i < graph.size(); i++) {
            expected.add();
        }
        connect(expected, 0, 1);
        connect(expected, 0, 7);
        connect(expected, 2, 3);
        connect(expected, 2, 5);
        connect(expected, 2, 8);
        connect(expected, 3, 4);
        connect(expected, 5, 6);
        connect(expected, 6, 7);
        final MinimumSpanningTreeFinder<WeightedEdgeDetails, VertexDetails> finder = getFinder();
        final Graph<WeightedEdgeDetails, VertexDetails> actual = finder.find(graph);
        assertThat(actual.size(), is(graph.size()));
        int edges = 0;
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                final boolean connected = actual.connected(i, j);
                if (connected) {
                    edges ++;
                }
                assertThat(connected, is(expected.connected(i, j)));
            }
        }
        assertThat(edges, is((graph.size() - 1) * 2));
    }

    private void connect(Graph<WeightedEdgeDetails, VertexDetails> expected, int from, int to) {
        expected.connect(from, to);
        expected.connect(to, from);
    }

}
