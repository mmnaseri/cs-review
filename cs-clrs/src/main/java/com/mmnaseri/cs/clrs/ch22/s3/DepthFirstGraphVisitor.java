package com.mmnaseri.cs.clrs.ch22.s3;

import com.mmnaseri.cs.clrs.ch22.EdgeColor;
import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitor;
import com.mmnaseri.cs.clrs.ch22.GraphVisitor;
import com.mmnaseri.cs.clrs.ch22.s1.*;
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
    public Graph<E, V> visit(Graph<E, V> graph, GraphVertexVisitor<E, V> visitor) {
        final Graph<E, V> copy = GraphUtils.copy(graph);
        final List<Vertex<V>> vertices = copy.getVertices();
        if (comparator != null) {
            Collections.sort(vertices, comparator);
        }
        for (Vertex<V> vertex : vertices) {
            vertex.setProperty("color", EdgeColor.WHITE);
            vertex.setProperty("parent", null);
        }
        final AtomicInteger time = new AtomicInteger(0);
        for (Vertex<V> vertex : vertices) {
            if (EdgeColor.WHITE.equals(vertex.getProperty("color", EdgeColor.class))) {
                visitSubGraph(copy, vertex, time, visitor);
            }
        }
        return copy;
    }

    @Override
    public Graph<E, V> visit(Graph<E, V> graph, int start, GraphVertexVisitor<E, V> visitor) {
        final Graph<E, V> copy = GraphUtils.copy(graph);
        final List<Vertex<V>> vertices = copy.getVertices();
        if (comparator != null) {
            Collections.sort(vertices, comparator);
        }
        for (Vertex<V> vertex : vertices) {
            vertex.setProperty("color", EdgeColor.WHITE);
            vertex.setProperty("parent", null);
        }
        final AtomicInteger time = new AtomicInteger(0);
        visitSubGraph(copy, copy.get(start), time, visitor);
        return copy;
    }

    private void visitSubGraph(Graph<E, V> graph, Vertex<V> vertex, AtomicInteger time, GraphVertexVisitor<E, V> visitor) {
        vertex.setProperty("discovery", time.incrementAndGet());
        vertex.setProperty("color", EdgeColor.GREY);
        visitor.beforeExploration(graph, vertex);
        final List<Vertex<V>> neighbors = graph.getNeighbors(vertex.getIndex());
        if (comparator != null) {
            Collections.sort(neighbors, comparator);
        }
        for (Vertex<V> neighbor : neighbors) {
            if (EdgeColor.WHITE.equals(neighbor.getProperty("color", EdgeColor.class))) {
                neighbor.setProperty("parent", vertex);
                visitSubGraph(graph, neighbor, time, visitor);
            }
        }
        vertex.setProperty("color", EdgeColor.BLACK);
        vertex.setProperty("finish", time.incrementAndGet());
        visitor.afterExploration(graph, vertex);
    }

}
