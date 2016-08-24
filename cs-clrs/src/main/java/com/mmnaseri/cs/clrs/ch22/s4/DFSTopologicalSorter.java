package com.mmnaseri.cs.clrs.ch22.s4;

import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitorAdapter;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch22.s3.DepthFirstGraphVisitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/3/15)
 */
public class DFSTopologicalSorter<E extends EdgeDetails, V extends VertexDetails> implements TopologicalSorter<E, V> {

    final DepthFirstGraphVisitor<E, V> visitor = new DepthFirstGraphVisitor<>();

    @Override
    public List<Vertex<V>> sort(Graph<E, V> graph) {
        final LinkedList<Vertex<V>> sorted = new LinkedList<>();
        visitor.visit(graph, new GraphVertexVisitorAdapter<E, V>() {
            @Override
            public void afterExploration(Graph<E, V> graph, Vertex<V> vertex) {
                sorted.add(0, vertex);
            }
        });
        return sorted;
    }

}
