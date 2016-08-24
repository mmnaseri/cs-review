package com.mmnaseri.cs.clrs.ch24.s3;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch24.s1.AbstractSingleSourceShortestPathFinder;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/6/15)
 */
public class DijkstraSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> extends AbstractSingleSourceShortestPathFinder<E, V> {

    @Override
    public Graph<E, V> find(Graph<E, V> graph, int start) {
        final Graph<E, V> result = initialize(graph, start);
        final Queue<Vertex<V>> queue = new PriorityQueue<>(result.size(), new DijkstraVertexComparator<V>());
        for (Vertex<V> vertex : result) {
            queue.add(vertex);
        }
        while (!queue.isEmpty()) {
            final Vertex<V> vertex = queue.remove();
            final List<Vertex<V>> neighbors = result.getNeighbors(vertex);
            for (Vertex<V> neighbor : neighbors) {
                relax(result, vertex, neighbor);
            }
        }
        return result;
    }

    private static class DijkstraVertexComparator<V extends VertexDetails> implements Comparator<Vertex<V>> {

        @Override
        public int compare(Vertex<V> first, Vertex<V> second) {
            return Integer.compare(first.getProperty("distance", Integer.class), second.getProperty("distance", Integer.class));
        }

    }

}
