package com.mmnaseri.cs.clrs.ch24.s3;

import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch24.BaseSingleSourceShortestPathFinderTest;
import com.mmnaseri.cs.clrs.ch24.SingleSourceShortestPathFinder;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/6/15)
 */
public class DijkstraSingleSourceShortestPathFinderTest extends BaseSingleSourceShortestPathFinderTest {

    @Override
    protected SingleSourceShortestPathFinder<WeightedEdgeDetails, VertexDetails> getFinder() {
        return new DijkstraSingleSourceShortestPathFinder<>();
    }

}