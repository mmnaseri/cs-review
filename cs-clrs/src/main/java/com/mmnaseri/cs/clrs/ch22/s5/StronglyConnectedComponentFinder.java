package com.mmnaseri.cs.clrs.ch22.s5;

import com.mmnaseri.cs.clrs.ch21.DisjointSet;
import com.mmnaseri.cs.clrs.ch21.s3.PathCompressingRankedForestDisjointSet;
import com.mmnaseri.cs.clrs.ch21.s3.RankedTreeElement;
import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitorAdapter;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch22.s3.DepthFirstGraphVisitor;
import com.mmnaseri.cs.clrs.common.ParameterizedTypeReference;

import java.util.Comparator;
import java.util.Set;

/**
 * This algorithm is due to <a
 * href="https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm">Kosaraju-Sharir</a>
 *
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/3/15)
 */
public class StronglyConnectedComponentFinder<E extends EdgeDetails, V extends VertexDetails> {

  private final DepthFirstGraphVisitor<E, V> first = new DepthFirstGraphVisitor<>();
  private final DepthFirstGraphVisitor<E, V> second =
      new DepthFirstGraphVisitor<>(new VertexFinishTimeComparator<>());

  public DisjointSet<?, Vertex<V>> find(Graph<E, V> graph) {
    // visit all nodes
    final Graph<E, V> coloredGraph = first.visit(graph, new GraphVertexVisitorAdapter<E, V>() {});
    // compute the transposed graph
    final Graph<E, V> transposed = coloredGraph.transpose();
    // visit the inverse nodes in decreasing order of the finish time the first time around
    final Graph<E, V> coloredTransposed =
        second.visit(transposed, new GraphVertexVisitorAdapter<E, V>() {});
    // create a disjoint set where each set is represented by a root of the DFS tree and contains
    // all its internal nodes
    final PathCompressingRankedForestDisjointSet<Vertex<V>> set =
        new PathCompressingRankedForestDisjointSet<>();
    for (Vertex<V> vertex : coloredTransposed.getVertices()) {
      final RankedTreeElement<Vertex<V>> element = set.create(vertex);
      final Vertex<V> root = findRoot(vertex);
      if (root.getIndex() == vertex.getIndex()) {
        continue;
      }
      RankedTreeElement<Vertex<V>> rootElement = null;
      for (RankedTreeElement<Vertex<V>> treeElement : set.sets()) {
        final Set<Vertex<V>> elements = set.elements(treeElement);
        if (elements.contains(root)) {
          rootElement = treeElement;
          break;
        }
      }
      if (rootElement == null) {
        rootElement = set.create(root);
      }
      set.union(rootElement, element);
    }
    return set;
  }

  private Vertex<V> findRoot(Vertex<V> vertex) {
    while (true) {
      final Vertex<V> parent =
          vertex.getProperty("parent", new ParameterizedTypeReference<Vertex<V>>() {});
      if (parent == null) {
        return vertex;
      }
      vertex = parent;
    }
  }

  private static class VertexFinishTimeComparator<V extends VertexDetails>
      implements Comparator<Vertex<V>> {

    @Override
    public int compare(Vertex<V> first, Vertex<V> second) {
      return Integer.compare(
          second.getProperty("finish", Integer.class), first.getProperty("finish", Integer.class));
    }
  }
}
