package com.mmnaseri.cs.clrs.ch22.s1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/3/15)
 */
public abstract class AbstractGraph<E extends EdgeDetails, V extends VertexDetails>
    implements Graph<E, V> {

  private Graph<E, V> transposed = null;

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public int add() {
    return add(null);
  }

  @Override
  public boolean connected(int from, int to) {
    return edge(from, to) != null;
  }

  @Override
  public Edge<E, V> connect(int from, int to) {
    return connect(from, to, null);
  }

  @Override
  public Graph<E, V> transpose() {
    if (transposed == null) {
      transposed = new TransposedGraph<>(this);
    }
    return transposed;
  }

  @Override
  public List<Vertex<V>> getVertices() {
    final List<Vertex<V>> vertices = new ArrayList<>();
    for (int i = 0; i < size(); i++) {
      vertices.add(get(i));
    }
    return vertices;
  }

  @Override
  public List<Vertex<V>> getNeighbors(Vertex<V> vertex) {
    return getNeighbors(Objects.requireNonNull(vertex, "Vertex must not be null").getIndex());
  }

  @Override
  public Iterator<Vertex<V>> iterator() {
    return getVertices().iterator();
  }

  private static class TransposedGraph<E extends EdgeDetails, V extends VertexDetails>
      implements Graph<E, V> {

    private final Graph<E, V> delegate;

    private TransposedGraph(Graph<E, V> delegate) {
      this.delegate = delegate;
    }

    @Override
    public int size() {
      return delegate.size();
    }

    @Override
    public boolean isEmpty() {
      return delegate.isEmpty();
    }

    @Override
    public V delete(int index) {
      return delegate.delete(index);
    }

    @Override
    public int add() {
      return delegate.add();
    }

    @Override
    public int add(V details) {
      return delegate.add(details);
    }

    @Override
    public Vertex<V> get(int index) {
      return delegate.get(index);
    }

    @Override
    public Edge<E, V> edge(int from, int to) {
      return delegate.edge(from, to);
    }

    @Override
    public boolean connected(int from, int to) {
      return delegate.connected(from, to);
    }

    @Override
    public Edge<E, V> connect(int from, int to) {
      return delegate.connect(to, from);
    }

    @Override
    public Edge<E, V> connect(int from, int to, E details) {
      return delegate.connect(to, from, details);
    }

    @Override
    public E disconnect(int from, int to) {
      return delegate.disconnect(to, from);
    }

    @Override
    public Graph<E, V> inverse() {
      return delegate.inverse();
    }

    @Override
    public Graph<E, V> transpose() {
      return delegate.transpose();
    }

    @Override
    public List<Vertex<V>> getVertices() {
      return delegate.getVertices();
    }

    @Override
    public List<Vertex<V>> getNeighbors(int index) {
      return delegate.getNeighbors(index);
    }

    @Override
    public List<Vertex<V>> getNeighbors(Vertex<V> vertex) {
      return delegate.getNeighbors(vertex);
    }

    @Override
    public List<Edge<E, V>> getEdges() {
      return delegate.getEdges();
    }

    @Override
    public Iterator<Vertex<V>> iterator() {
      return delegate.iterator();
    }
  }
}
