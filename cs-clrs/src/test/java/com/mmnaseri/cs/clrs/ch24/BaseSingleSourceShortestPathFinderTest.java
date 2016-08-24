package com.mmnaseri.cs.clrs.ch24;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.common.GraphSamples;
import com.mmnaseri.cs.clrs.common.ParameterizedTypeReference;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/6/15)
 */
public abstract class BaseSingleSourceShortestPathFinderTest {

    protected abstract SingleSourceShortestPathFinder<WeightedEdgeDetails, VertexDetails> getFinder();

    @Test
    public void testAgainstSampleWithoutNegativeLoops() throws Exception {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = GraphSamples.sampleWeightedGraphWithoutNegativeLoops();
        final Graph<WeightedEdgeDetails, VertexDetails> result = getFinder().find(graph, 1);
        checkResultIntegrity(graph, result);
        checkVertexProperties(result);
        checkPathProperty(result, 0, Integer.MAX_VALUE, null);
        checkPathProperty(result, 1, 0, null);
        checkPathProperty(result, 2, 2, 1);
        checkPathProperty(result, 3, 6, 1);
        checkPathProperty(result, 4, 5, 3);
        checkPathProperty(result, 5, 3, 4);
    }

    @Test
    public void testAgainstCyclicSampleWithoutNegativeEdges() throws Exception {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = GraphSamples.sampleWeightedLoopingGraphWithoutNegativeEdges();
        final Graph<WeightedEdgeDetails, VertexDetails> result = getFinder().find(graph, 0);
        checkResultIntegrity(graph, result);
        checkVertexProperties(result);
        checkPathProperty(result, 0, 0, null);
        checkPathProperty(result, 1, 8, 3);
        checkPathProperty(result, 2, 9, 1);
        checkPathProperty(result, 3, 5, 0);
        checkPathProperty(result, 4, 7, 3);
    }

    protected void checkVertexProperties(Graph<WeightedEdgeDetails, VertexDetails> result) {
        for (Vertex<VertexDetails> vertex : result) {
            assertThat(vertex.hasProperty("distance"), is(true));
            assertThat(vertex.getProperty("distance"), is(notNullValue()));
            assertThat(vertex.getProperty("distance", Integer.class), is(notNullValue()));
            assertThat(vertex.hasProperty("predecessor"), is(true));
        }
    }

    protected void checkResultIntegrity(Graph<WeightedEdgeDetails, VertexDetails> graph, Graph<WeightedEdgeDetails, VertexDetails> result) {
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(graph.size()));
        assertThat(result.getEdges().size(), is(graph.getEdges().size()));
    }

    protected void checkPathProperty(Graph<WeightedEdgeDetails, VertexDetails> graph, int vertex, int distance, Integer predecessor) {
        assertThat(graph.get(vertex).getProperty("distance", Integer.class), is(distance));
        if (predecessor == null) {
            assertThat(graph.get(vertex).getProperty("predecessor", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(nullValue()));
        } else {
            assertThat(graph.get(vertex).getProperty("predecessor", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(graph.get(predecessor)));
        }
    }

}
