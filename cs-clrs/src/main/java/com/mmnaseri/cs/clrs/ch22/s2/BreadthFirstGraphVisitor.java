package com.mmnaseri.cs.clrs.ch22.s2;

import com.mmnaseri.cs.clrs.ch22.GraphColor;
import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitor;
import com.mmnaseri.cs.clrs.ch22.GraphVisitor;
import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 11:12 PM)
 */
@Quality(Stage.UNTESTED)
public class BreadthFirstGraphVisitor<E extends EdgeDetails, V extends VertexDetails> implements GraphVisitor<E, V> {

    private final Comparator<Vertex<V>> comparator;

    public BreadthFirstGraphVisitor() {
        this(null);
    }

    public BreadthFirstGraphVisitor(Comparator<Vertex<V>> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Graph<E, V> visit(Graph<E, V> graph, GraphVertexVisitor<E, V> visitor) {
        final Graph<E, V> copy = GraphUtils.copy(graph);
        final List<Vertex<V>> vertices = copy.getVertices();
        for (Vertex<V> vertex : vertices) {
            vertex.setProperty("color", GraphColor.WHITE);
            vertex.setProperty("distance", Integer.MAX_VALUE);
            vertex.setProperty("parent", null);
        }
        if (comparator != null) {
            Collections.sort(vertices, comparator);
        }
        for (Vertex<V> vertex : vertices) {
            if (GraphColor.WHITE.equals(vertex.getProperty("color", GraphColor.class))) {
                visitSubGraph(copy, visitor, vertex);
            }
        }
        return copy;
    }

    @Override
    public Graph<E, V> visit(Graph<E, V> graph, int start, GraphVertexVisitor<E, V> visitor) {
        final Graph<E, V> copy = GraphUtils.copy(graph);
        final List<Vertex<V>> vertices = copy.getVertices();
        for (Vertex<V> vertex : vertices) {
            if (vertex.getIndex() == start) {
                continue;
            }
            vertex.setProperty("color", GraphColor.WHITE);
            vertex.setProperty("distance", Integer.MAX_VALUE);
            vertex.setProperty("parent", null);
        }
        final Vertex<V> startingVertex = copy.get(start);
        visitSubGraph(copy, visitor, startingVertex);
        return copy;
    }

    private void visitSubGraph(Graph<E, V> graph, GraphVertexVisitor<E, V> visitor, Vertex<V> startingVertex) {
        startingVertex.setProperty("color", GraphColor.GREY);
        startingVertex.setProperty("distance", 0);
        startingVertex.setProperty("parent", null);
        final Queue<Vertex<V>> queue = new ArrayDeque<>();
        queue.add(startingVertex);
        while (!queue.isEmpty()) {
            final Vertex<V> vertex = queue.poll();
            visitor.beforeExploration(graph, vertex);
            for (Vertex<V> neighbor : graph.getNeighbors(vertex)) {
                visitor.visitEdge(graph, graph.edge(vertex.getIndex(), neighbor.getIndex()));
                if (GraphColor.WHITE.equals(neighbor.getProperty("color", GraphColor.class))) {
                    neighbor.setProperty("color", GraphColor.GREY);
                    neighbor.setProperty("distance", vertex.getProperty("distance", Integer.class) + 1);
                    neighbor.setProperty("parent", vertex);
                    queue.add(neighbor);
                }
            }
            vertex.setProperty("color", GraphColor.BLACK);
            visitor.afterExploration(graph, vertex);
        }
    }

}
