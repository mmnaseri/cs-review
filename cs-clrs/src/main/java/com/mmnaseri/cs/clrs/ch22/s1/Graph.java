package com.mmnaseri.cs.clrs.ch22.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 8:09 PM)
 */
public interface Graph<E extends EdgeDetails, V extends VertexDetails> {

    int getVertices();

    boolean isEmpty();

    V deleteVertex(int index);

    int addVertex(V details);

    Vertex<V> getVertex(int index);

    Edge<E, V> getEdge(int from, int to);

    Edge<E, V> connect(int from, int to, E details);

    E disconnect(int from, int to);

    Graph<E, V> inverse();

}
