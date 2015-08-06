package com.mmnaseri.cs.clrs.ch23.s2;

import com.mmnaseri.cs.clrs.ch22.EdgeColor;
import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitorAdapter;
import com.mmnaseri.cs.clrs.ch22.s1.Edge;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch22.s2.BreadthFirstGraphVisitor;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.common.GraphSamples;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/5/15)
 */
public abstract class BaseMinimumSpanningTreeFinderTest {

    protected abstract <E extends WeightedEdgeDetails, V extends VertexDetails> MinimumSpanningTreeFinder<E, V> getFinder();

    @Test
    public void testAgainstSample() throws Exception {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = GraphSamples.sampleWeightedGraph();
        int expectedSum = 37;
        final MinimumSpanningTreeFinder<WeightedEdgeDetails, VertexDetails> finder = getFinder();
        final Graph<WeightedEdgeDetails, VertexDetails> tree = finder.find(graph);
        assertThat(tree.size(), is(graph.size()));
        final Set<Integer> vertices = new HashSet<>();
        int edges = 0;
        int sum = 0;
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                if (tree.connected(i, j)) {
                    vertices.add(i);
                    vertices.add(j);
                    edges ++;
                    sum += weight(graph, i, j);
                }
            }
        }
        assertThat(vertices, hasSize(graph.size())); //all nodes must be included
        assertThat(sum, is(expectedSum * 2)); //the expected sum times 2 (since each edge is bidirectional)
        assertThat(edges, is(2 * (graph.size() - 1))); //each edge is counted twice. trees have n-1 edges
        final BreadthFirstGraphVisitor<WeightedEdgeDetails, VertexDetails> visitor = new BreadthFirstGraphVisitor<>();
        //the resulting graph must not have any loops
        visitor.visit(tree, new GraphVertexVisitorAdapter<WeightedEdgeDetails, VertexDetails>() {
            @Override
            public void beforeExploration(Graph<WeightedEdgeDetails, VertexDetails> graph, Vertex<VertexDetails> vertex) {
                assertThat(vertex.getProperty("color", EdgeColor.class), isIn(Arrays.asList(EdgeColor.WHITE, EdgeColor.GREY)));
            }
        });
    }

    private static <E extends WeightedEdgeDetails, V extends VertexDetails> int weight(Graph<E, V> graph, Integer vertex, Integer neighbor) {
        final Edge<E, V> first = graph.edge(vertex, neighbor);
        final Edge<E, V> second = graph.edge(neighbor, vertex);
        if (second == null) {
            return Integer.MAX_VALUE;
        }
        E firstDetails = first.getDetails();
        E secondDetails = second.getDetails();
        if (firstDetails == null) {
            firstDetails = secondDetails;
        }
        if (secondDetails == null) {
            secondDetails = firstDetails;
        }
        final int firstWeight = firstDetails == null ? 0 : firstDetails.getWeight();
        final int secondWeight = secondDetails == null ? 0 : secondDetails.getWeight();
        return firstWeight != secondWeight ? Integer.MAX_VALUE : firstWeight;
    }

}
