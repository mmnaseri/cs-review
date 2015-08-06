package com.mmnaseri.cs.clrs.ch24.s2;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch24.BaseSingleSourceShortestPathFinderTest;
import com.mmnaseri.cs.clrs.ch24.s1.SingleSourceShortestPathFinder;
import com.mmnaseri.cs.clrs.common.GraphSamples;
import org.testng.annotations.Test;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/6/15)
 */
public class BellmanFordSingleSourceShortestPathFinderTest extends BaseSingleSourceShortestPathFinderTest {

    @Override
    protected SingleSourceShortestPathFinder<WeightedEdgeDetails, VertexDetails> getFinder() {
        return new BellmanFordSingleSourceShortestPathFinder<>();
    }

    @Test
    public void testAgainstSampleWithNegativeLoops() throws Exception {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = GraphSamples.sampleWeightedGraphWithNegativeLoops();
        final SingleSourceShortestPathFinder<WeightedEdgeDetails, VertexDetails> finder = getFinder();
        final Graph<WeightedEdgeDetails, VertexDetails> result = finder.find(graph, 0);
        checkResultIntegrity(graph, result);
        checkVertexProperties(result);
        checkPathProperty(result, 0, 0, null);
        checkPathProperty(result, 1, 2, 2);
        checkPathProperty(result, 2, 4, 3);
        checkPathProperty(result, 3, 7, 0);
        checkPathProperty(result, 4, -2, 1);
    }

}