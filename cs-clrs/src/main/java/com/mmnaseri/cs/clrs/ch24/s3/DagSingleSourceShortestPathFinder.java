package com.mmnaseri.cs.clrs.ch24.s3;

import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch22.s4.DFSTopologicalSorter;
import com.mmnaseri.cs.clrs.ch22.s4.TopologicalSorter;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch24.s1.AbstractSingleSourceShortestPathFinder;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/6/15)
 */
@Quality(Stage.TESTED)
public class DagSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> extends AbstractSingleSourceShortestPathFinder<E, V> {

    private final TopologicalSorter<E, V> sorter;

    public DagSingleSourceShortestPathFinder() {
        this(new DFSTopologicalSorter<E, V>());
    }

    public DagSingleSourceShortestPathFinder(TopologicalSorter<E, V> sorter) {
        this.sorter = sorter;
    }

    @Override
    public Graph<E, V> find(Graph<E, V> graph, int start) {
        final Graph<E, V> result = initialize(graph, start);
        final List<Vertex<V>> vertices = sorter.sort(result);
        for (Vertex<V> vertex : vertices) {
            final List<Vertex<V>> neighbors = result.getNeighbors(vertex);
            for (Vertex<V> neighbor : neighbors) {
                relax(result, vertex, neighbor);
            }
        }
        return result;
    }

}
