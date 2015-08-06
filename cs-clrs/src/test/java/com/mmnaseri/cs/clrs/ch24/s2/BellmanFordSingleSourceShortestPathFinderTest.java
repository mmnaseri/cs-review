package com.mmnaseri.cs.clrs.ch24.s2;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch24.s1.SingleSourceShortestPathFinder;
import com.mmnaseri.cs.clrs.common.GraphSamples;
import com.mmnaseri.cs.clrs.common.ParameterizedTypeReference;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/6/15)
 */
public class BellmanFordSingleSourceShortestPathFinderTest {

    protected SingleSourceShortestPathFinder<WeightedEdgeDetails, VertexDetails> getFinder() {
        return new BellmanFordSingleSourceShortestPathFinder<>();
    }

    @Test
    public void testAgainstSample() throws Exception {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = GraphSamples.sampleWeightedGraphWithNegativeLoops();
        final SingleSourceShortestPathFinder<WeightedEdgeDetails, VertexDetails> finder = getFinder();
        final Graph<WeightedEdgeDetails, VertexDetails> result = finder.find(graph, 0);
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(graph.size()));
        assertThat(result.getEdges().size(), is(graph.getEdges().size()));
        for (Vertex<VertexDetails> vertex : result) {
            assertThat(vertex.hasProperty("distance"), is(true));
            assertThat(vertex.getProperty("distance"), is(notNullValue()));
            assertThat(vertex.getProperty("distance", Integer.class), is(notNullValue()));
            assertThat(vertex.hasProperty("predecessor"), is(true));
        }
        assertThat(result.get(0).getProperty("distance", Integer.class), is(0));
        assertThat(result.get(1).getProperty("distance", Integer.class), is(2));
        assertThat(result.get(2).getProperty("distance", Integer.class), is(4));
        assertThat(result.get(3).getProperty("distance", Integer.class), is(7));
        assertThat(result.get(4).getProperty("distance", Integer.class), is(-2));
        assertThat(result.get(0).getProperty("predecessor", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(nullValue()));
        assertThat(result.get(1).getProperty("predecessor", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(2)));
        assertThat(result.get(2).getProperty("predecessor", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(3)));
        assertThat(result.get(3).getProperty("predecessor", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(0)));
        assertThat(result.get(4).getProperty("predecessor", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(1)));
    }

}