package com.mmnaseri.cs.clrs.ch25.s1;

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
 * @since 1.0 (8/9/15, 6:27 PM)
 */
@Quality(Stage.BUGGY)
public class FasterAllPairsShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> implements com.mmnaseri.cs.clrs.ch25.AllPairsShortestPathFinder<E,V> {

    private Matrix<ShortestPathMetadata<V>> extend(Matrix<ShortestPathMetadata<V>> original, Matrix<ShortestPathMetadata<V>> reference) {
        final int size = original.getRows();
        final Matrix<ShortestPathMetadata<V>> result = new ArrayMatrix<>(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.set(i, j, new ShortestPathMetadata<V>(Integer.MAX_VALUE, null));
                for (int k = 0; k < size; k++) {
                    final ShortestPathMetadata<V> metadata = reference.get(k, j);
                    final int edgeWeight = metadata.getDistance();
                    final ShortestPathMetadata<V> previous = original.get(i, k);
                    final long newDistance = (long) edgeWeight + (long) previous.getDistance();
                    if (newDistance < result.get(i, j).getDistance()) {
                        result.set(i, j, new ShortestPathMetadata<>((int) newDistance, metadata.getPredecessor()));
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
        int iteration = 1;
        while (iteration < size - 1) {
            result = extend(result, result);
            iteration = iteration * 2;
        }
        for (int i = 0; i < size; i++) {
            result.set(i, i, new ShortestPathMetadata<V>(0, null));
        }
        return result;
    }

}
