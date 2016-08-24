package com.mmnaseri.cs.clrs.ch24.s2;

import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch24.BaseSingleSourceShortestPathFinderTest;
import com.mmnaseri.cs.clrs.ch24.SingleSourceShortestPathFinder;
import org.testng.annotations.Test;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/6/15)
 */
public class DagSingleSourceShortestPathFinderTest extends BaseSingleSourceShortestPathFinderTest {

    @Override
    protected SingleSourceShortestPathFinder<WeightedEdgeDetails, VertexDetails> getFinder() {
        return new DagSingleSourceShortestPathFinder<>();
    }

    @Override
    @Test(enabled = false)
    public void testAgainstCyclicSampleWithoutNegativeEdges() throws Exception {
        super.testAgainstCyclicSampleWithoutNegativeEdges();
    }

}