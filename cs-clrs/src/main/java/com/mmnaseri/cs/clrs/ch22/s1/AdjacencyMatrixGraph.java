package com.mmnaseri.cs.clrs.ch22.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 10:17 PM)
 */
@Quality(Stage.UNTESTED)
public class AdjacencyMatrixGraph<E extends EdgeDetails, V extends VertexDetails> extends AbstractGraph<E, V> {
    
    private int capacity = 20;
    private int vertices = 0;
    private Matrix<Edge<E, V>> matrix = new ArrayMatrix<>(capacity, capacity);
    private Map<Integer, Vertex<V>> vertexMap = new HashMap<>();
    
    @Override
    public int size() {
        return vertices;
    }

    @Override
    public V deleteVertex(int index) {
        checkIndex(index);
        final V details = vertexMap.get(index).getDetails();
        for (int i = index; i < vertices - 1; i++) {
            final Vertex<V> vertex = vertexMap.get(i + 1);
            vertex.setIndex(i);
            vertexMap.put(i, vertex);
            for (int j = 0; j < vertices; j++) {
                matrix.set(j, i, matrix.get(j, i + 1));
            }
            for (int j = 0; j < vertices; j++) {
                matrix.set(i, j, matrix.get(i + 1, j));
            }
        }
        for (int i = 0; i < vertices; i++) {
            matrix.set(vertices - 1, i, null);
            matrix.set(i, vertices - 1, null);
        }
        vertices --;
        relaxCapacity();
        return details;
    }

    @Override
    public int addVertex(V details) {
        relaxCapacity();
        final int index = vertices++;
        final Vertex<V> vertex = new Vertex<>();
        vertex.setIndex(index);
        vertex.setDetails(details);
        vertexMap.put(index, vertex);
        return index;
    }

    @Override
    public Vertex<V> getVertex(int index) {
        checkIndex(index);
        return vertexMap.get(index);
    }

    @Override
    public Edge<E, V> getEdge(int from, int to) {
        checkIndex(from);
        checkIndex(to);
        return matrix.get(from, to);
    }

    @Override
    public Edge<E, V> connect(int from, int to, E details) {
        checkIndex(from);
        checkIndex(to);
        final Edge<E, V> edge = new Edge<>();
        edge.setFrom(getVertex(from));
        edge.setTo(getVertex(to));
        edge.setDetails(details);
        matrix.set(from, to, edge);
        return edge;
    }

    @Override
    public E disconnect(int from, int to) {
        checkIndex(from);
        checkIndex(to);
        final Edge<E, V> edge = matrix.get(from, to);
        matrix.set(from, to, null);
        if (edge != null) {
            return edge.getDetails();
        }
        return null;
    }

    @Override
    public Graph<E, V> inverse() {
        final AdjacencyMatrixGraph<E, V> graph = new AdjacencyMatrixGraph<>();
        graph.vertices = vertices;
        graph.matrix = new ArrayMatrix<>(vertices, vertices);
        for (int i = 0; i < vertices; i++) {
            graph.vertexMap.put(i, vertexMap.get(i));
        }
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (matrix.get(i, j) == null) {
                    final Edge<E, V> edge = new Edge<>();
                    edge.setDetails(null);
                    edge.setFrom(getVertex(i));
                    edge.setFrom(getVertex(j));
                    graph.matrix.set(i, j, edge);
                }
            }
        }
        graph.relaxCapacity();
        return graph;
    }

    private void relaxCapacity() {
        int newCapacity = capacity;
        if (vertices < capacity / 3) {
            newCapacity = capacity / 2;
        } else if (vertices > 3 * capacity / 4) {
            newCapacity = capacity * 2;
        }
        newCapacity = Math.max(4, newCapacity);
        if (newCapacity != capacity) {
            capacity = newCapacity;
            final Matrix<Edge<E, V>> temp = new ArrayMatrix<>(capacity, capacity);
            copy(matrix, temp);
        }
    }

    private void copy(Matrix<Edge<E, V>> source, Matrix<Edge<E, V>> target) {
        for (int i = 0; i < Math.min(source.getRows(), target.getRows()); i++) {
            for (int j = 0; j < Math.min(source.getColumns(), target.getColumns()); j++) {
                target.set(i, j, source.get(i, j));
            }
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= vertices) {
            throw new IndexOutOfBoundsException("Invalid vertex index: " + index);
        }
    }

}
