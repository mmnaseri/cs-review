package com.mmnaseri.cs.clrs.ch22.s3;

import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitor;
import com.mmnaseri.cs.clrs.ch22.GraphVisitor;
import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 11:31 PM)
 */
@Quality(Stage.UNTESTED)
public class DepthFirstGraphVisitor<E extends EdgeDetails, V extends VertexDetails> implements GraphVisitor<E, V> {

    private enum Color {

        WHITE, GREY, BLACK

    }

    @Override
    public void visit(Graph<E, V> graph, int start, GraphVertexVisitor<E, V> visitor) {
        final Map<Integer, Color> colors = new HashMap<>();
        for (int i = 0; i < graph.getVertices(); i++) {
            colors.put(i, Color.WHITE);
        }
        visit(graph, start, visitor, colors);
    }

    private void visit(Graph<E, V> graph, int vertex, GraphVertexVisitor<E, V> visitor, Map<Integer, Color> colors) {
        visitor.visit(graph, graph.getVertex(vertex));
        colors.put(vertex, Color.GREY);
        for (int i = 0; i < graph.getVertices(); i++) {
            final Edge<E, V> edge = graph.getEdge(vertex, i);
            if (edge == null || !Color.WHITE.equals(colors.get(i))) {
                continue;
            }
            visit(graph, i, visitor, colors);
        }
        postProcess(graph, graph.getVertex(vertex));
        colors.put(vertex, Color.BLACK);
    }

    protected void postProcess(Graph<E, V> graph, Vertex<V> vertex) {

    }

}
