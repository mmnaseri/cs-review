package com.mmnaseri.cs.clrs.ch26.s3;

import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/26/15)
 */
public interface BipartiteMatchingFinder<E extends EdgeDetails, V extends VertexDetails> {

    Graph<E, V> find(Graph<E, V> bipartiteGraph);

}
