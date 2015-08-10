package com.mmnaseri.cs.clrs.ch25;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.common.GraphSamples;
import com.mmnaseri.cs.clrs.common.Matrix;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/10/15)
 */
public abstract class BaseAllPairsShortestPathFinderTest {

    protected abstract AllPairsShortestPathFinder<WeightedEdgeDetails, VertexDetails> getFinder();

    @Test
    public void testAgainstSample() throws Exception {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = GraphSamples.sampleWeightedGraphForAllPairsShortestPathCalculation();
        final AllPairsShortestPathFinder<WeightedEdgeDetails, VertexDetails> finder = getFinder();
        final Matrix<ShortestPathMetadata<VertexDetails>> matrix = finder.find(graph);
        assertThat(matrix, is(notNullValue()));
        assertThat(matrix.getRows(), is(graph.size()));
        assertThat(matrix.getColumns(), is(graph.size()));
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                assertThat(matrix.get(i, j), is(notNullValue()));
            }
        }
        validateMetadata(matrix, 0, 0, 0, null);
        validateMetadata(matrix, 0, 1, 4, 0);
        validateMetadata(matrix, 0, 2, 3, 0);
        validateMetadata(matrix, 0, 3, 4, 2);
        validateMetadata(matrix, 1, 0, 3, 2);
        validateMetadata(matrix, 1, 1, 0, null);
        validateMetadata(matrix, 1, 2, 1, 1);
        validateMetadata(matrix, 1, 3, 2, 2);
        validateMetadata(matrix, 2, 0, 2, 2);
        validateMetadata(matrix, 2, 1, 6, 0);
        validateMetadata(matrix, 2, 2, 0, null);
        validateMetadata(matrix, 2, 3, 1, 2);
        validateMetadata(matrix, 3, 0, 3, 2);
        validateMetadata(matrix, 3, 1, 7, 0);
        validateMetadata(matrix, 3, 2, 1, 3);
        validateMetadata(matrix, 3, 3, 0, null);
    }

    protected void validateMetadata(Matrix<ShortestPathMetadata<VertexDetails>> matrix, int i, int j, int distance, Integer predecessor) {
        final ShortestPathMetadata<VertexDetails> metadata = matrix.get(i, j);
        assertThat(metadata.getDistance(), is(distance));
        if (predecessor == null) {
            assertThat(metadata.getPredecessor(), is(nullValue()));
        } else {
            assertThat(metadata.getPredecessor(), is(notNullValue()));
            assertThat(metadata.getPredecessor().getIndex(), is(predecessor));
        }
    }
}
