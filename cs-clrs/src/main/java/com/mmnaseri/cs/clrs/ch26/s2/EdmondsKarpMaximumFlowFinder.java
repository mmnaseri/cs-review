package com.mmnaseri.cs.clrs.ch26.s2;

import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitorAdapter;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch22.s2.BreadthFirstGraphVisitor;
import com.mmnaseri.cs.clrs.ch26.s1.FlowEdgeDetails;
import com.mmnaseri.cs.clrs.common.ParameterizedTypeReference;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/26/15)
 */
@Quality(Stage.TESTED)
public class EdmondsKarpMaximumFlowFinder<E extends FlowEdgeDetails, V extends VertexDetails> extends AbstractFordFulkersonMaximumFlowFinder<E, V> {

    private final BreadthFirstGraphVisitor<FlowEdgeDetails, V> visitor = new BreadthFirstGraphVisitor<>();

    @Override
    protected List<Integer> findAugmentingPath(Graph<FlowEdgeDetails, V> residualNetwork, int source, int destination) {
        final Graph<FlowEdgeDetails, V> visitation = visitor.visit(residualNetwork, source, new GraphVertexVisitorAdapter<FlowEdgeDetails, V>() {
        });
        final List<Integer> path = new LinkedList<>();
        int vertex = destination;
        while (vertex != source) {
            final Vertex<V> parent = visitation.get(vertex).getProperty("parent", new ParameterizedTypeReference<Vertex<V>>() {
            });
            if (parent != null) {
                path.add(0, vertex);
                vertex = parent.getIndex();
            } else {
                return Collections.emptyList();
            }
        }
        path.add(0, source);
        return path;
    }

}
