package com.mmnaseri.cs.clrs.ch24.s1;

import com.mmnaseri.cs.clrs.ch22.s1.Edge;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/6/15)
 */
@Quality(Stage.TESTED)
public class BellmanFordSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> extends AbstractSingleSourceShortestPathFinder<E, V> {

    @Override
    public Graph<E, V> find(Graph<E, V> graph, int start) {
        final Graph<E, V> result = initialize(graph, start);
        final List<Edge<E, V>> edges = result.getEdges();
        for (int i = 0; i < graph.size() - 1; i++) {
            for (Edge<E, V> edge : edges) {
                relax(edge);
            }
        }
        for (Edge<E, V> edge : edges) {
            final Vertex<V> midpoint = edge.getFrom();
            final Vertex<V> destination = edge.getTo();
            final int currentDistance = destination.getProperty("distance", Integer.class);
            final long detourDistance = (long) midpoint.getProperty("distance", Integer.class) + (long) weight(edge);
            if (currentDistance > detourDistance) {
                return null;
            }
        }
        return result;
    }

}
