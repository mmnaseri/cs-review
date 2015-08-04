package com.mmnaseri.cs.clrs.ch22.s2;

import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitor;
import com.mmnaseri.cs.clrs.ch22.GraphVisitor;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 11:12 PM)
 */
@Quality(Stage.UNTESTED)
public class BreadthFirstGraphVisitor<E extends EdgeDetails, V extends VertexDetails> implements GraphVisitor<E, V> {

    @Override
    public void visit(Graph<E, V> graph, int start, GraphVertexVisitor<E, V> visitor) {
        final List<Vertex<V>> vertices = graph.getVertices();
        for (Vertex<V> vertex : vertices) {
            if (vertex.getIndex() == start) {
                continue;
            }
            vertex.setProperty("color", Color.WHITE);
            vertex.setProperty("distance", Integer.MAX_VALUE);
            vertex.setProperty("parent", null);
        }
        final Vertex<V> startingVertex = graph.get(start);
        startingVertex.setProperty("color", Color.GREY);
        startingVertex.setProperty("distance", 0);
        startingVertex.setProperty("parent", null);
        final Queue<Vertex<V>> queue = new ArrayDeque<>();
        queue.add(startingVertex);
        while (!queue.isEmpty()) {
            final Vertex<V> vertex = queue.poll();
            visitor.beforeExploration(graph, vertex);
            for (Vertex<V> neighbor : graph.getNeighbors(vertex)) {
                if (Color.WHITE.equals(neighbor.getProperty("color", Color.class))) {
                    neighbor.setProperty("color", Color.GREY);
                    neighbor.setProperty("distance", vertex.getProperty("distance", Integer.class) + 1);
                    neighbor.setProperty("parent", vertex);
                    queue.add(neighbor);
                }
            }
            vertex.setProperty("color", Color.BLACK);
            visitor.afterExploration(graph, vertex);
        }
    }

    private enum Color {

        WHITE, GREY, BLACK

    }

}
