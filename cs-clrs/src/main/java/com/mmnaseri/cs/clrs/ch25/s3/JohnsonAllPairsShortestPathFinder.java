package com.mmnaseri.cs.clrs.ch25.s3;

import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.clrs.ch23.s1.MutableWeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch24.s1.BellmanFordSingleSourceShortestPathFinder;
import com.mmnaseri.cs.clrs.ch24.s3.DijkstraSingleSourceShortestPathFinder;
import com.mmnaseri.cs.clrs.ch25.AllPairsShortestPathFinder;
import com.mmnaseri.cs.clrs.ch25.ShortestPathMetadata;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.ParameterizedTypeReference;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/11/15)
 */
@Quality(Stage.TESTED)
public class JohnsonAllPairsShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> implements AllPairsShortestPathFinder<E, V> {

    private final BellmanFordSingleSourceShortestPathFinder<WeightedEdgeDetails, V> bellmanFord = new BellmanFordSingleSourceShortestPathFinder<>();
    private final DijkstraSingleSourceShortestPathFinder<WeightedEdgeDetails, V> dijkstra = new DijkstraSingleSourceShortestPathFinder<>();

    @Override
    public Matrix<ShortestPathMetadata<V>> find(Graph<E, V> graph) {
        //running bellman-ford has two benefits:
        //1. we will know if we have a negative cycle
        //2. we get some positive integer that we can use for offsetting
        final Graph<WeightedEdgeDetails, V> bellmanFordResult = computeSingleSourceShortestPath(graph);
        if (bellmanFordResult == null) {
            return null;
        }
        //we now offset each weight value so that the original weights are converted to something positive
        for (Edge<WeightedEdgeDetails, V> edge : bellmanFordResult.getEdges()) {
            final WeightedEdgeDetails details = edge.getDetails();
            final int edgeWeight;
            if (details != null) {
                edgeWeight = details.getWeight();
            } else {
                edgeWeight = 0;
            }
            final int newWeight;
            final Integer sourcePotential = edge.getFrom().getProperty("distance", Integer.class);
            final Integer targetPotential = edge.getTo().getProperty("distance", Integer.class);
            if (sourcePotential == Integer.MAX_VALUE || targetPotential == Integer.MAX_VALUE || edgeWeight == Integer.MAX_VALUE) {
                newWeight = Integer.MAX_VALUE;
            } else {
                newWeight = edgeWeight + sourcePotential - targetPotential;
            }
            final MutableWeightedEdgeDetails newDetails = new MutableWeightedEdgeDetails();
            newDetails.setWeight(newWeight);
            edge.setDetails(newDetails);
        }
        final int size = graph.size();
        final Matrix<ShortestPathMetadata<V>> result = new ArrayMatrix<>(size, size);
        for (int i = 0; i < size; i++) {
            //we now run dijkstra on each vertex, the same as we did in the brute force implementation, only now we can use dijkstra safely
            final Graph<WeightedEdgeDetails, V> dijkstraResult = dijkstra.find(bellmanFordResult, i);
            final Integer sourcePotential = bellmanFordResult.get(i).getProperty("distance", Integer.class);
            for (int j = 0; j < size; j++) {
                final Integer targetPotential = bellmanFordResult.get(j).getProperty("distance", Integer.class);
                final Integer localDistance = dijkstraResult.get(j).getProperty("distance", Integer.class);
                //we now reverse the offsetting we did above
                final int actualDistance = localDistance + targetPotential - sourcePotential;
                //the predecessor for each node is the one we computed during dijkstra
                final Vertex<V> predecessor = dijkstraResult.get(j).getProperty("predecessor", new ParameterizedTypeReference<Vertex<V>>() {
                });
                result.set(i, j, new ShortestPathMetadata<>(actualDistance, predecessor));
            }
        }
        return result;
    }

    protected Graph<WeightedEdgeDetails, V> computeSingleSourceShortestPath(Graph<E, V> graph) {
        final Graph<WeightedEdgeDetails, V> copy = copyGraph(graph);
        final int startingPoint = copy.add();
        for (int i = 0; i < graph.size(); i++) {
            final MutableWeightedEdgeDetails details = new MutableWeightedEdgeDetails();
            details.setWeight(0);
            copy.connect(startingPoint, i, details);
        }
        return bellmanFord.find(copy, startingPoint);
    }

    protected Graph<WeightedEdgeDetails, V> copyGraph(Graph<E, V> graph) {
        final Graph<WeightedEdgeDetails, V> copy = new AdjacencyListGraph<>();
        for (Vertex<V> vertex : graph) {
            copy.add(vertex.getDetails());
        }
        for (Edge<E, V> edge : graph.getEdges()) {
            copy.connect(edge.getFrom().getIndex(), edge.getTo().getIndex(), edge.getDetails());
        }
        return copy;
    }

}
