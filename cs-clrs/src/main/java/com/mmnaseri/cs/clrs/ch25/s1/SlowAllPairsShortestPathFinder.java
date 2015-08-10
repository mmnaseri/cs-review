package com.mmnaseri.cs.clrs.ch25.s1;

import com.mmnaseri.cs.clrs.ch22.s1.Edge;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch25.ShortestPathMetadata;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/9/15, 6:23 PM)
 */
@Quality(Stage.UNTESTED)
public class SlowAllPairsShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> implements com.mmnaseri.cs.clrs.ch25.AllPairsShortestPathFinder<E,V> {

    private Matrix<ShortestPathMetadata<V>> extend(Matrix<ShortestPathMetadata<V>> original, Graph<E, V> graph) {
        final int size = original.getRows();
        final Matrix<ShortestPathMetadata<V>> result = new ArrayMatrix<>(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.set(i, j, new ShortestPathMetadata<V>(Integer.MAX_VALUE, null));
                for (int k = 0; k < size; k++) {
                    final Edge<E, V> edge = graph.edge(k, j);
                    if (edge == null) {
                        continue;
                    }
                    final ShortestPathMetadata<V> previous = original.get(i, k);
                    final int edgeWeight;
                    if (edge.getDetails() != null) {
                        edgeWeight = edge.getDetails().getWeight();
                    } else {
                        edgeWeight = Integer.MAX_VALUE;
                    }
                    final long newDistance = (long) edgeWeight + (long) previous.getDistance();
                    if (newDistance < result.get(i, j).getDistance()) {
                        result.set(i, j, new ShortestPathMetadata<>((int) newDistance, edge.getFrom()));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Matrix<ShortestPathMetadata<V>> find(Graph<E, V> graph) {
        final int size = graph.size();
        Matrix<ShortestPathMetadata<V>> result = new ArrayMatrix<>(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.set(i, j, new ShortestPathMetadata<V>(i == j ? 0 : Integer.MAX_VALUE, null));
            }
        }
        for (int i = 2; i < size; i++) {
            result = extend(result, graph);
        }
        return result;
    }

}
