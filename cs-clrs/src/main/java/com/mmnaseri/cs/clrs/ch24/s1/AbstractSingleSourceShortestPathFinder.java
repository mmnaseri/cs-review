package com.mmnaseri.cs.clrs.ch24.s1;

import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
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
        result.get(start).setProperty("distance", 0);
        return result;
    }

    protected void relax(Graph<E, V> graph, int midpoint, int destination) {
        final Edge<E, V> edge = graph.edge(midpoint, destination);
        final int edgeWeight;
        if (edge == null) {
            //the nodes where not adjacent
            edgeWeight = Integer.MAX_VALUE;
        } else {
            final E details = edge.getDetails();
            edgeWeight = details == null ? 0 : details.getWeight();
        }
        final Vertex<V> midpointVertex = graph.get(midpoint);
        final Vertex<V> destinationVertex = graph.get(destination);
        final Integer midpointDistance = midpointVertex.getProperty("distance", Integer.class);
        final Integer currentDistance = destinationVertex.getProperty("distance", Integer.class);
        final long detourDistance = (long) midpointDistance + (long) edgeWeight;
        if (detourDistance < currentDistance) {
            destinationVertex.setProperty("distance", (int) detourDistance);
            destinationVertex.setProperty("predecessor", midpointVertex);
        }
    }

}
