package com.mmnaseri.cs.clrs.ch25.s1;

import com.mmnaseri.cs.clrs.ch22.s1.Edge;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch25.AllPairsShortestPathFinder;
import com.mmnaseri.cs.clrs.ch25.ShortestPathMetadata;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/10/15)
 */
public abstract class AbstractExtendingAllPairsShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> implements AllPairsShortestPathFinder<E, V> {

    protected Matrix<ShortestPathMetadata<V>> extend(Matrix<ShortestPathMetadata<V>> original, Matrix<ShortestPathMetadata<V>> reference) {
        final int size = original.getRows();
        final Matrix<ShortestPathMetadata<V>> extended = new ArrayMatrix<>(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                extended.set(i, j, new ShortestPathMetadata<>(original.get(i, j).getDistance(), original.get(i, j).getPredecessor()));
                for (int k = 0; k < size; k++) {
                    final ShortestPathMetadata<V> left = original.get(i, k);
                    final ShortestPathMetadata<V> right = reference.get(k, j);
                    final long distance = (long) left.getDistance() + (long) right.getDistance();
                    if (distance < extended.get(i, j).getDistance()) {
                        extended.set(i, j, new ShortestPathMetadata<>((int) distance, original.get(k, j).getPredecessor()));
                    }
                }
            }
        }
        return extended;
    }

    protected Matrix<ShortestPathMetadata<V>> weights(Graph<E, V> graph) {
        final int size = graph.size();
        final Matrix<ShortestPathMetadata<V>> matrix = new ArrayMatrix<>(size, size);
        for (int i = 0; i < size; i++) {
            final Vertex<V> vertex = graph.get(i);
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    matrix.set(i, j, new ShortestPathMetadata<V>(0, null));
                    continue;
                }
                final Edge<E, V> edge = graph.edge(i, j);
                if (edge == null) {
                    matrix.set(i, j, new ShortestPathMetadata<V>(Integer.MAX_VALUE, null));
                } else {
                    final E details = edge.getDetails();
                    final int weight;
                    if (details != null) {
                        weight = details.getWeight();
                    } else {
                        weight = 0;
                    }
                    matrix.set(i, j, new ShortestPathMetadata<>(weight, vertex));
                }
            }
        }
        return matrix;
    }

}
