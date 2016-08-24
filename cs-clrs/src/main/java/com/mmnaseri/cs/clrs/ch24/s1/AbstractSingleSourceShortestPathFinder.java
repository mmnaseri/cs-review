package com.mmnaseri.cs.clrs.ch24.s1;

import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch24.SingleSourceShortestPathFinder;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/6/15)
 */
public abstract class AbstractSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> implements SingleSourceShortestPathFinder<E, V> {

    protected Graph<E, V> initialize(Graph<E, V> graph, int start) {
        final AdjacencyListGraph<E, V> result = new AdjacencyListGraph<>();
        for (Vertex<V> vertex : graph) {
            final int index = result.add(vertex.getDetails());
            final Vertex<V> createdVertex = result.get(index);
            createdVertex.setProperty("distance", Integer.MAX_VALUE);
            createdVertex.setProperty("predecessor", null);
        }
        for (Edge<E, V> edge : graph.getEdges()) {
            result.connect(edge.getFrom().getIndex(), edge.getTo().getIndex(), edge.getDetails());
        }
        result.get(start).setProperty("distance", 0);
        return result;
    }

    protected void relax(Graph<E, V> graph, Vertex<V> midpoint, Vertex<V> destination) {
        final Edge<E, V> edge = graph.edge(midpoint.getIndex(), destination.getIndex());
        relax(edge);
    }

    protected void relax(Edge<E, V> edge) {
        final Vertex<V> midpoint = edge.getFrom();
        final Vertex<V> destination = edge.getTo();
        final int edgeWeight = weight(edge);
        final Integer midpointDistance = midpoint.getProperty("distance", Integer.class);
        final Integer currentDistance = destination.getProperty("distance", Integer.class);
        final long detourDistance = (long) midpointDistance + (long) edgeWeight;
        if (detourDistance < currentDistance) {
            destination.setProperty("distance", (int) detourDistance);
            destination.setProperty("predecessor", midpoint);
        }
    }

    protected int weight(Edge<E, V> edge) {
        final E details = edge.getDetails();
        return details == null ? 0 : details.getWeight();
    }

}
