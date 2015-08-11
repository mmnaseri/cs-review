package com.mmnaseri.cs.clrs.ch25.s1;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch25.ShortestPathMetadata;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/9/15, 6:23 PM)
 */
@Quality(Stage.TESTED)
public class SlowAllPairsShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> extends AbstractExtendingAllPairsShortestPathFinder<E, V> {

    @Override
    public Matrix<ShortestPathMetadata<V>> find(Graph<E, V> graph) {
        final int size = graph.size();
        final Matrix<ShortestPathMetadata<V>> weights = weights(graph);
        Matrix<ShortestPathMetadata<V>> result = weights;
        for (int i = 1; i < size - 1; i++) {
            result = extend(result, weights);
        }
        for (int i = 0; i < size; i++) {
            result.set(i, i, new ShortestPathMetadata<V>(0, null));
        }
        return result;
    }

}
