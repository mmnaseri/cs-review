package com.mmnaseri.cs.clrs.ch22.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 10:51 PM)
 */
@Quality(Stage.TESTED)
public class AdjacencyListGraph<E extends EdgeDetails, V extends VertexDetails> extends AbstractGraph<E, V> {

    final List<AdjacencyEdge<E, V>> adjacencyLists = new ArrayList<>();
    final List<Vertex<V>> vertices = new ArrayList<>();

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    public V delete(int index) {
        check(index);
        final V details = vertices.get(index).getDetails();
        for (int i = 0; i < size(); i++) {
            deleteEdge(index, (AdjacencyEdge<E, V>) edge(index, i));
            deleteEdge(i, (AdjacencyEdge<E, V>) edge(i, index));
        }
        for (int i = index; i < size() - 1; i++) {
            final Vertex<V> vertex = vertices.get(i + 1);
            vertex.setIndex(i);
            vertices.set(i, vertex);
            adjacencyLists.set(i, adjacencyLists.get(i + 1));
        }
        vertices.remove(vertices.size() - 1);
        adjacencyLists.remove(adjacencyLists.size() - 1);
        return details;
    }

    @Override
    public int add(V details) {
        final Vertex<V> vertex = new Vertex<>();
        vertex.setIndex(vertices.size());
        vertex.setDetails(details);
        vertices.add(vertex);
        adjacencyLists.add(null);
        return vertex.getIndex();
    }

    @Override
    public Vertex<V> get(int index) {
        check(index);
        return vertices.get(index);
    }

    @Override
    public Edge<E, V> edge(int from, int to) {
        check(from);
        check(to);
        AdjacencyEdge<E, V> edge = adjacencyLists.get(from);
        while (edge != null) {
            if (edge.getTo().getIndex() == to) {
                return edge;
            }
            edge = edge.getNext();
        }
        return null;
    }

    @Override
    public Edge<E, V> connect(int from, int to, E details) {
        check(from);
        check(to);
        final AdjacencyEdge<E, V> element = new AdjacencyEdge<>();
        element.setFrom(get(from));
        element.setTo(get(to));
        element.setDetails(details);
        AdjacencyEdge<E, V> edge = adjacencyLists.get(from);
        if (edge == null) {
            adjacencyLists.set(from, element);
        } else {
            while (edge.getNext() != null) {
                if (edge.getTo().getIndex() == to) {
                    return edge;
                }
                edge = edge.getNext();
            }
            edge.setNext(element);
            element.setPrevious(edge);
        }
        return element;
    }

    @Override
    public E disconnect(int from, int to) {
        check(from);
        check(to);
        AdjacencyEdge<E, V> edge = adjacencyLists.get(from);
        if (edge == null) {
            return null;
        }
        while (edge != null && edge.getTo().getIndex() != to) {
            edge = edge.getNext();
        }
        return deleteEdge(from, edge);
    }

    protected E deleteEdge(int vertex, AdjacencyEdge<E, V> edge) {
        if (edge == null) {
            return null;
        }
        final E details = edge.getDetails();
        if (edge.getPrevious() == null) {
            adjacencyLists.set(vertex, edge.getNext());
        } else {
            edge.getPrevious().setNext(edge.getNext());
        }
        if (edge.getNext() != null) {
            edge.getNext().setPrevious(edge.getPrevious());
        }
        return details;
    }

    @Override
    public Graph<E, V> inverse() {
        final AdjacencyListGraph<E, V> graph = new AdjacencyListGraph<>();
        graph.vertices.addAll(vertices);
        for (int i = 0; i < size(); i++) {
            graph.adjacencyLists.add(null);
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (edge(i, j) == null) {
                    graph.connect(i, j, null);
                }
            }
        }
        return graph;
    }

    @Override
    public List<Edge<E, V>> getEdges() {
        final List<Edge<E, V>> edges = new LinkedList<>();
        for (AdjacencyEdge<E, V> list : adjacencyLists) {
            AdjacencyEdge<E, V> edge = list;
            while (edge != null) {
                edges.add(edge);
                edge = edge.getNext();
            }
        }
        return edges;
    }

    private void check(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid vertex number: " + index);
        }
    }

    private static class AdjacencyEdge<E extends EdgeDetails, V extends VertexDetails> extends Edge<E, V> {

        private AdjacencyEdge<E, V> previous;
        private AdjacencyEdge<E, V> next;

        public AdjacencyEdge<E, V> getPrevious() {
            return previous;
        }

        public void setPrevious(AdjacencyEdge<E, V> previous) {
            this.previous = previous;
        }

        public AdjacencyEdge<E, V> getNext() {
            return next;
        }

        public void setNext(AdjacencyEdge<E, V> next) {
            this.next = next;
        }

    }

}
