package com.mmnaseri.cs.clrs.ch22.s3;

import com.mmnaseri.cs.clrs.ch22.GraphColor;
import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitor;
import com.mmnaseri.cs.clrs.ch22.GraphVisitor;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.GraphUtils;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 11:31 PM)
 */
@Quality(Stage.UNTESTED)
public class DepthFirstGraphVisitor<E extends EdgeDetails, V extends VertexDetails>
    implements GraphVisitor<E, V> {

  private final Comparator<Vertex<V>> comparator;

  public DepthFirstGraphVisitor(Comparator<Vertex<V>> comparator) {
    this.comparator = comparator;
  }

  public DepthFirstGraphVisitor() {
    this(null);
  }

  @Override
  public Graph<E, V> visit(Graph<E, V> graph, GraphVertexVisitor<E, V> visitor) {
    Graph<E, V> copy = initializeSearchGraph(graph);
    List<Vertex<V>> vertices = copy.getVertices();
    final AtomicInteger time = new AtomicInteger(0);
    for (Vertex<V> vertex : vertices) {
      if (GraphColor.WHITE.equals(vertex.getProperty("color", GraphColor.class))) {
        visitSubGraph(copy, vertex, time, visitor);
      }
    }
    return copy;
  }

  @Override
  public Graph<E, V> visit(Graph<E, V> graph, int start, GraphVertexVisitor<E, V> visitor) {
    final Graph<E, V> copy = initializeSearchGraph(graph);
    final AtomicInteger time = new AtomicInteger(0);
    visitSubGraph(copy, copy.get(start), time, visitor);
    return copy;
  }

  private Graph<E, V> initializeSearchGraph(Graph<E, V> graph) {
    final Graph<E, V> copy = GraphUtils.copy(graph);
    final List<Vertex<V>> vertices = copy.getVertices();
    if (comparator != null) {
      vertices.sort(comparator);
    }
    for (Vertex<V> vertex : vertices) {
      vertex.setProperty("color", GraphColor.WHITE);
      vertex.setProperty("parent", null);
      vertex.setProperty("distance", 0);
    }
    return copy;
  }

  private void visitSubGraph(
      Graph<E, V> graph, Vertex<V> vertex, AtomicInteger time, GraphVertexVisitor<E, V> visitor) {
    vertex.setProperty("discovery", time.incrementAndGet());
    vertex.setProperty("color", GraphColor.GREY);
    visitor.beforeExploration(graph, vertex);
    final List<Vertex<V>> neighbors = graph.getNeighbors(vertex.getIndex());
    if (comparator != null) {
      neighbors.sort(comparator);
    }
    for (Vertex<V> neighbor : neighbors) {
      visitor.visitEdge(graph, graph.edge(vertex.getIndex(), neighbor.getIndex()));
      if (GraphColor.WHITE.equals(neighbor.getProperty("color", GraphColor.class))) {
        neighbor.setProperty("distance", vertex.getProperty("distance", Integer.class) + 1);
        neighbor.setProperty("parent", vertex);
        visitSubGraph(graph, neighbor, time, visitor);
      }
    }
    vertex.setProperty("color", GraphColor.BLACK);
    vertex.setProperty("finish", time.incrementAndGet());
    visitor.afterExploration(graph, vertex);
  }
}
