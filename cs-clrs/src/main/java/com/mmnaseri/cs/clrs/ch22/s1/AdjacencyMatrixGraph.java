package com.mmnaseri.cs.clrs.ch22.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 10:17 PM)
 */
@Quality(Stage.TESTED)
public class AdjacencyMatrixGraph<E extends EdgeDetails, V extends VertexDetails>
    extends AbstractGraph<E, V> {

  public static final int DEFAULT_CAPACITY = 32;
  private int capacity;
  private int vertices;
  private Matrix<Edge<E, V>> matrix;
  private final Map<Integer, Vertex<V>> vertexMap;

  public AdjacencyMatrixGraph() {
    this(DEFAULT_CAPACITY);
  }

  public AdjacencyMatrixGraph(int capacity) {
    this.capacity = capacity;
    vertices = 0;
    matrix = new ArrayMatrix<>(capacity, capacity);
    vertexMap = new HashMap<>(capacity);
  }

  @Override
  public int size() {
    return vertices;
  }

  @Override
  public V delete(int index) {
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
    vertices--;
    relaxCapacity();
    return details;
  }

  @Override
  public int add(V details) {
    relaxCapacity();
    final int index = vertices++;
    final Vertex<V> vertex = new Vertex<>();
    vertex.setIndex(index);
    vertex.setDetails(details);
    vertexMap.put(index, vertex);
    return index;
  }

  @Override
  public Vertex<V> get(int index) {
    checkIndex(index);
    return vertexMap.get(index);
  }

  @Override
  public Edge<E, V> edge(int from, int to) {
    checkIndex(from);
    checkIndex(to);
    return matrix.get(from, to);
  }

  @Override
  public Edge<E, V> connect(int from, int to, E details) {
    checkIndex(from);
    checkIndex(to);
    final Edge<E, V> edge = new Edge<>();
    edge.setFrom(get(from));
    edge.setTo(get(to));
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
        if (edge(i, j) == null) {
          final Edge<E, V> edge = new Edge<>();
          edge.setDetails(null);
          edge.setFrom(get(i));
          edge.setFrom(get(j));
          graph.matrix.set(i, j, edge);
        }
      }
    }
    graph.relaxCapacity();
    return graph;
  }

  @Override
  public List<Edge<E, V>> getEdges() {
    final List<Edge<E, V>> list = new LinkedList<>();
    for (int i = 0; i < size(); i++) {
      for (int j = 0; j < size(); j++) {
        final Edge<E, V> edge = edge(i, j);
        if (edge != null) {
          list.add(edge);
        }
      }
    }
    return list;
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
      matrix = temp;
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

  @Override
  public List<Vertex<V>> getNeighbors(int index) {
    final List<Vertex<V>> vertices = new ArrayList<>();
    for (int i = 0; i < size(); i++) {
      final Edge<E, V> edge = matrix.get(index, i);
      if (edge != null) {
        vertices.add(edge.getTo());
      }
    }
    return vertices;
  }
}
