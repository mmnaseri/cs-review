package com.mmnaseri.cs.clrs.ch23.s2;

import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/5/15)
 */
public class KruskalMinimumSpanningTreeFinderTest extends BaseMinimumSpanningTreeFinderTest {

    @Override
    protected <E extends WeightedEdgeDetails, V extends VertexDetails> MinimumSpanningTreeFinder<E, V> getFinder() {
        return new KruskalMinimumSpanningTreeFinder<>();
    }

}