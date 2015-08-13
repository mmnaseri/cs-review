package com.mmnaseri.cs.clrs.ch22.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/13/15)
 */
public abstract class GraphUtils {

    public static <E extends EdgeDetails, V extends VertexDetails> Graph<E, V> copy(Graph<E, V> original) {
        final Graph<E, V> copy = new AdjacencyListGraph<>();
        for (Vertex<V> vertex : original) {
            final int index = copy.add();
            final Vertex<V> added = copy.get(index);
            copy(vertex, added);
        }
        for (Edge<E, V> edge : original.getEdges()) {
            final Edge<E, V> added = copy.connect(edge.getFrom().getIndex(), edge.getTo().getIndex());
            copy(edge, added);
        }
        return copy;
    }

    public static <E extends GraphElement<D>, D extends GraphDetails> void copy(E original, E copy) {
        copy.setDetails(original.getDetails());
        for (String property : original.getPropertyNames()) {
            copy.setProperty(property, original.getProperty(property));
        }
    }

}
