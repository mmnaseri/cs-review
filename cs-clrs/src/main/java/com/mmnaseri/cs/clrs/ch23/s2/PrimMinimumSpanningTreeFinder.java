package com.mmnaseri.cs.clrs.ch23.s2;

import com.mmnaseri.cs.clrs.ch22.s1.AdjacencyListGraph;
import com.mmnaseri.cs.clrs.ch22.s1.Edge;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.common.HeapProperty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/5/15)
 */
public class PrimMinimumSpanningTreeFinder<E extends WeightedEdgeDetails, V extends VertexDetails>
    implements MinimumSpanningTreeFinder<E, V> {

  @Override
  public Graph<E, V> find(Graph<E, V> graph) {
    if (graph.isEmpty()) {
      return graph;
    }
    final Map<Integer, Integer> weights = new HashMap<>();
    final Map<Integer, Integer> parents = new HashMap<>();
    final Set<Integer> examined = new HashSet<>();
    final PriorityQueue<Integer> heap =
        new PriorityQueue<>(
            graph.size(),
            new PrimHeapProperty(weights)); // new ArrayHeap<>(new PrimHeapProperty(weights));
    final AdjacencyListGraph<E, V> result = new AdjacencyListGraph<>();
    for (Vertex<V> vertex : graph) {
      weights.put(vertex.getIndex(), Integer.MAX_VALUE);
      parents.put(vertex.getIndex(), null);
      result.add(vertex.getDetails());
      heap.add(vertex.getIndex());
    }
    weights.put(0, 0);
    while (!heap.isEmpty()) {
      final Integer vertex = heap.remove();
      examined.add(vertex);
      final List<Vertex<V>> neighbors = graph.getNeighbors(vertex);
      for (Vertex<V> neighbor : neighbors) {
        if (!examined.contains(neighbor.getIndex())) {
          final int edgeWeight = weight(graph, vertex, neighbor.getIndex());
          if (edgeWeight < weights.get(neighbor.getIndex())) {
            weights.put(neighbor.getIndex(), edgeWeight);
            parents.put(neighbor.getIndex(), vertex);
          }
        }
      }
    }
    for (int i = 1; i < graph.size(); i++) {
      result.connect(i, parents.get(i));
      result.connect(parents.get(i), i);
    }
    return result;
  }

  private static <E extends WeightedEdgeDetails, V extends VertexDetails> int weight(
      Graph<E, V> graph, Integer vertex, Integer neighbor) {
    final Edge<E, V> first = graph.edge(vertex, neighbor);
    final Edge<E, V> second = graph.edge(neighbor, vertex);
    if (second == null) {
      return Integer.MAX_VALUE;
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
    return firstWeight != secondWeight ? Integer.MAX_VALUE : firstWeight;
  }

  private static class PrimHeapProperty implements HeapProperty<Integer> {

    private final Map<Integer, Integer> weights;

    private PrimHeapProperty(Map<Integer, Integer> weights) {
      this.weights = weights;
    }

    @Override
    public int compare(Integer first, Integer second) {
      final int comparison = Integer.compare(weights.get(first), weights.get(second));
      if (comparison == 0) {
        return Integer.compare(first, second);
      }
      return comparison;
    }
  }
}
