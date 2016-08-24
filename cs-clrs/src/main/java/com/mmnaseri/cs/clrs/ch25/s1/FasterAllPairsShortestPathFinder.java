package com.mmnaseri.cs.clrs.ch25.s1;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch25.ShortestPathMetadata;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/9/15, 6:27 PM)
 */
@Quality(Stage.TESTED)
public class FasterAllPairsShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> extends AbstractExtendingAllPairsShortestPathFinder<E, V> {

    @Override
    public Matrix<ShortestPathMetadata<V>> find(Graph<E, V> graph) {
        final int size = graph.size();
        Matrix<ShortestPathMetadata<V>> result = weights(graph);
        int iteration = 1;
        while (iteration < size) {
            result = extend(result, result);
            iteration = iteration * 2;
        }
        for (int i = 0; i < size; i++) {
            result.set(i, i, new ShortestPathMetadata<V>(0, null));
        }
        return result;
    }

}
