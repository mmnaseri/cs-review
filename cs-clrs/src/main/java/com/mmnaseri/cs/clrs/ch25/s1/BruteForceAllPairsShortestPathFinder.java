package com.mmnaseri.cs.clrs.ch25.s1;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch24.SingleSourceShortestPathFinder;
import com.mmnaseri.cs.clrs.ch24.s1.BellmanFordSingleSourceShortestPathFinder;
import com.mmnaseri.cs.clrs.ch25.AllPairsShortestPathFinder;
import com.mmnaseri.cs.clrs.ch25.ShortestPathMetadata;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.ParameterizedTypeReference;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/7/15)
 */
@Quality(Stage.TESTED)
public class BruteForceAllPairsShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> implements AllPairsShortestPathFinder<E, V> {

    private final SingleSourceShortestPathFinder<E, V> finder;

    public BruteForceAllPairsShortestPathFinder() {
        this(new BellmanFordSingleSourceShortestPathFinder<E, V>());
    }

    public BruteForceAllPairsShortestPathFinder(SingleSourceShortestPathFinder<E, V> finder) {
        this.finder = finder;
    }

    @Override
    public Matrix<ShortestPathMetadata<V>> find(Graph<E, V> graph) {
        final Matrix<ShortestPathMetadata<V>> matrix = new ArrayMatrix<>(graph.size(), graph.size());
        for (Vertex<V> vertex : graph) {
            final Graph<E, V> current = finder.find(graph, vertex.getIndex());
            for (Vertex<V> destination : current) {
                final Integer distance = destination.getProperty("distance", Integer.class);
                matrix.set(vertex.getIndex(), destination.getIndex(), new ShortestPathMetadata<>(distance, destination.getProperty("predecessor", new ParameterizedTypeReference<Vertex<V>>() {
                })));
            }
        }
        return matrix;
    }

}
