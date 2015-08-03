package com.mmnaseri.cs.clrs.ch22.s2;

import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitor;
import com.mmnaseri.cs.clrs.ch22.GraphVisitor;
import com.mmnaseri.cs.clrs.ch22.s1.Edge;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 11:12 PM)
 */
@Quality(Stage.UNTESTED)
public class BreadthFirstGraphVisitor<E extends EdgeDetails, V extends VertexDetails> implements GraphVisitor<E, V> {

    private enum Color {
        WHITE, GREY, BLACK
    }

    @Override
    public void visit(Graph<E, V> graph, int start, GraphVertexVisitor<E, V> visitor) {
        final Map<Integer, Color> colors = new HashMap<>();
        for (int i = 0; i < graph.getVertices(); i++) {
            colors.put(i, Color.WHITE);
        }
        colors.put(start, Color.GREY);
        final Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            final Integer vertex = queue.poll();
            visitor.visit(graph, graph.getVertex(vertex));
            for (int i = 0; i < graph.getVertices(); i++) {
                final Edge<E, V> edge = graph.getEdge(vertex, i);
                if (edge == null || !Color.WHITE.equals(colors.get(i))) {
                    continue;
                }
                colors.put(i, Color.GREY);
                queue.add(i);
            }
            colors.put(vertex, Color.BLACK);
        }
    }

}
