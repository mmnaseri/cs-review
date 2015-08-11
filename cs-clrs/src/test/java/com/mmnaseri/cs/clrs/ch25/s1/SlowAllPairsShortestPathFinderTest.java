package com.mmnaseri.cs.clrs.ch25.s1;

import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch25.AllPairsShortestPathFinder;
import com.mmnaseri.cs.clrs.ch25.BaseAllPairsShortestPathFinderTest;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/10/15)
 */
public class SlowAllPairsShortestPathFinderTest extends BaseAllPairsShortestPathFinderTest {

    @Override
    protected AllPairsShortestPathFinder<WeightedEdgeDetails, VertexDetails> getFinder() {
        return new SlowAllPairsShortestPathFinder<>();
    }

}