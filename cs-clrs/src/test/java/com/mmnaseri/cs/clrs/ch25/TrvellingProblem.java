package com.mmnaseri.cs.clrs.ch25;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch25.s2.FloydWarshallAllPairsShortestPathFinder;
import com.mmnaseri.cs.clrs.common.GraphSamples;
import com.mmnaseri.cs.clrs.common.Matrix;
import org.junit.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TrvellingProblem extends BaseAllPairsShortestPathFinderTest{

    protected AllPairsShortestPathFinder<WeightedEdgeDetails, VertexDetails> getFinder() {
        return new FloydWarshallAllPairsShortestPathFinder<>();
    }

    private static Graph<WeightedEdgeDetails, VertexDetails> createGraph(){
        return GraphSamples.sampleFiveVertexWeightedGraphForAllPairsShortestPathCalculation();
    }

    @Test
    public Matrix<ShortestPathMetadata<VertexDetails>> findShortestForAllVertices(){
        final Graph<WeightedEdgeDetails, VertexDetails> graph = createGraph();
        final AllPairsShortestPathFinder<WeightedEdgeDetails, VertexDetails> finder = getFinder();
        final Matrix<ShortestPathMetadata<VertexDetails>> matrix = finder.find(graph);
        assertThat(matrix.getRows(), is(graph.size()));

        return matrix;
    }
}
