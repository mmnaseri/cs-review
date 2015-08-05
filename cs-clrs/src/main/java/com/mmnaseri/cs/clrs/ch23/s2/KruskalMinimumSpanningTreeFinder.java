package com.mmnaseri.cs.clrs.ch23.s2;

import com.mmnaseri.cs.clrs.ch21.s3.PathCompressingRankedForestDisjointSet;
import com.mmnaseri.cs.clrs.ch21.s3.RankedTreeElement;
import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Note: Even though this works exactly as described in the book, it does not return the "minimum" spanning tree. The tree it returns,
 * as demonstrated in the book has a sum of 37, while the minimum is 35. This is not because this implementation does not follow the
 * instructions of the book, rather, it is exactly <em>because</em> it follows those instructions that it behaves so.
 *
 * I need to further investigate to see what can be improved about this.
 *
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/5/15)
 */
public class KruskalMinimumSpanningTreeFinder<E extends WeightedEdgeDetails, V extends VertexDetails> implements MinimumSpanningTreeFinder<E, V> {

    @Override
    public Graph<E, V> find(Graph<E, V> graph) {
        if (graph.isEmpty()) {
            return graph;
        }
        final Graph<E, V> result = new AdjacencyListGraph<>();
        for (Vertex<V> vertex : graph) {
            result.add(vertex.getDetails());
        }
        final PathCompressingRankedForestDisjointSet<Integer> set = new PathCompressingRankedForestDisjointSet<>();
        final List<RankedTreeElement<Integer>> elements = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            final RankedTreeElement<Integer> element = set.create(i);
            elements.add(element);
        }
        final List<Edge<E, V>> edges = collectEdges(graph);
        Collections.sort(edges, new EdgeWeightComparator<E, V>());
        for (Edge<E, V> edge : edges) {
            final RankedTreeElement<Integer> from = elements.get(edge.getFrom().getIndex());
            final RankedTreeElement<Integer> to = elements.get(edge.getTo().getIndex());
            if (!set.find(from).equals(set.find(to))) {
                set.union(from, to);
                result.connect(from.getValue(), to.getValue());
                result.connect(to.getValue(), from.getValue());
            }
        }
        return result;
    }

    private List<Edge<E, V>> collectEdges(Graph<E, V> graph) {
        final List<Edge<E, V>> edges = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                //we only want to support non-digraphs
                final Edge<E, V> first = graph.edge(i, j);
                final Edge<E, V> second = graph.edge(j, i);
                if (first == null || second == null) {
                    continue;
                }
                E firstDetails = first.getDetails();
                E secondDetails = second.getDetails();
                if (firstDetails == null) {
                    firstDetails = secondDetails;
                }
                if (secondDetails == null) {
                    secondDetails = firstDetails;
                }
                final int firstWeight = firstDetails == null ? 0 : firstDetails.getWeight();
                final int secondWeight = secondDetails == null ? 0 : secondDetails.getWeight();
                //if both directions were specified, but had different weights, it is a digraph edge, and we reject it
                if (firstWeight != secondWeight) {
                    continue;
                }
                //we have now determined that the edges are the same and they have the same weight, so it won't matter
                //which one of them we store
                edges.add(first);
            }
        }
        return edges;
    }

    private static class EdgeWeightComparator<E extends WeightedEdgeDetails, V extends VertexDetails> implements Comparator<Edge<E, V>> {

        @Override
        public int compare(Edge<E, V> first, Edge<E, V> second) {
            final int firstWeight = first.getDetails() == null ? 0 : first.getDetails().getWeight();
            final int secondWeight = second.getDetails() == null ? 0 : second.getDetails().getWeight();
            return Integer.compare(firstWeight, secondWeight);
        }

    }

}
