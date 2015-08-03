package com.mmnaseri.cs.clrs.ch22.s1;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 8:09 PM)
 */
public interface Graph<E extends EdgeDetails, V extends VertexDetails> {

    int size();

    boolean isEmpty();

    V deleteVertex(int index);

    int addVertex(V details);

    Vertex<V> getVertex(int index);

    Edge<E, V> getEdge(int from, int to);

    Edge<E, V> connect(int from, int to, E details);

    E disconnect(int from, int to);

    Graph<E, V> inverse();

    List<Vertex<V>> getVertices();

    List<Vertex<V>> getNeighbors(int index);

    List<Vertex<V>> getNeighbors(Vertex<V> vertex);

}
