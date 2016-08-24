package com.mmnaseri.cs.clrs.ch25.s2;

import com.mmnaseri.cs.clrs.ch22.s1.Edge;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch25.AllPairsShortestPathFinder;
import com.mmnaseri.cs.clrs.ch25.ShortestPathMetadata;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/10/15)
 */
@Quality(Stage.TESTED)
public class FloydWarshallAllPairsShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> implements AllPairsShortestPathFinder<E, V> {

    @Override
    public Matrix<ShortestPathMetadata<V>> find(Graph<E, V> graph) {
        final int size = graph.size();
        final Matrix<ShortestPathMetadata<V>> result = new ArrayMatrix<>(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final Edge<E, V> edge = graph.edge(i, j);
                final int edgeWeight = edge == null ? Integer.MAX_VALUE : (edge.getDetails() == null ? 0 : edge.getDetails().getWeight());
                final Vertex<V> predecessor = i == j || edgeWeight == Integer.MAX_VALUE ? null : graph.get(i);
                result.set(i, j, new ShortestPathMetadata<>(edgeWeight, predecessor));
            }
        }
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    final ShortestPathMetadata<V> left = result.get(i, k);
                    final ShortestPathMetadata<V> right = result.get(k, j);
                    final long detour = (long) left.getDistance() + (long) right.getDistance();
                    if (result.get(i, j).getDistance() > detour) {
                        result.set(i, j, new ShortestPathMetadata<>((int) detour, right.getPredecessor()));
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            result.set(i, i, new ShortestPathMetadata<V>(0, null));
        }
        return result;
    }

}
