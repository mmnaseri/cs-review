package com.mmnaseri.cs.clrs.ch22.s4;

import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/3/15)
 */
public interface TopologicalSorter<E extends EdgeDetails, V extends VertexDetails> {

  List<Vertex<V>> sort(Graph<E, V> graph);
}
