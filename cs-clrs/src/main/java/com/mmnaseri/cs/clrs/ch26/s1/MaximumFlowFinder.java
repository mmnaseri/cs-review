package com.mmnaseri.cs.clrs.ch26.s1;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/26/15)
 */
public interface MaximumFlowFinder<E extends FlowEdgeDetails, V extends VertexDetails> {

    Graph<FlowEdgeDetails, V> find(Graph<E, V> network, int source, int destination);

}
