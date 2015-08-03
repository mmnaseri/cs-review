package com.mmnaseri.cs.clrs.ch22.s3;

import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitor;
import com.mmnaseri.cs.clrs.ch22.GraphVisitor;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 11:31 PM)
 */
@Quality(Stage.UNTESTED)
public class DepthFirstGraphVisitor<E extends EdgeDetails, V extends VertexDetails> implements GraphVisitor<E, V> {

    private final Comparator<Vertex<V>> comparator;

    public DepthFirstGraphVisitor(Comparator<Vertex<V>> comparator) {
        this.comparator = comparator;
    }

    public DepthFirstGraphVisitor() {
        this(null);
    }

    @Override
    public void visit(Graph<E, V> graph, int start, GraphVertexVisitor<E, V> visitor) {
        final List<Vertex<V>> vertices = graph.getVertices();
        if (comparator != null) {
            Collections.sort(vertices, comparator);
        }
        for (Vertex<V> vertex : vertices) {
            vertex.setProperty("color", Color.WHITE);
            vertex.setProperty("parent", null);
        }
        final AtomicInteger time = new AtomicInteger(0);
        for (Vertex<V> vertex : vertices) {
            if (Color.WHITE.equals(vertex.getProperty("color", Color.class))) {
                visit(graph, vertex, time, visitor);
            }
        }
    }

    private void visit(Graph<E, V> graph, Vertex<V> vertex, AtomicInteger time, GraphVertexVisitor<E, V> visitor) {
        vertex.setProperty("discovery", time.incrementAndGet());
        vertex.setProperty("color", Color.GREY);
        visitor.beforeExploration(graph, vertex);
        final List<Vertex<V>> neighbors = graph.getNeighbors(vertex.getIndex());
        if (comparator != null) {
            Collections.sort(neighbors, comparator);
        }
        for (Vertex<V> neighbor : neighbors) {
            if (Color.WHITE.equals(neighbor.getProperty("color", Color.class))) {
                neighbor.setProperty("parent", vertex);
                visit(graph, neighbor, time, visitor);
            }
        }
        vertex.setProperty("color", Color.BLACK);
        vertex.setProperty("finish", time.incrementAndGet());
        visitor.afterExploration(graph, vertex);
    }

    private enum Color {

        WHITE, GREY, BLACK

    }

}
