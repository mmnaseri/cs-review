package com.mmnaseri.cs.clrs.common;

import com.mmnaseri.cs.clrs.ch22.s1.AdjacencyListGraph;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/3/15)
 */
public abstract class GraphSamples {

    public static Graph<EdgeDetails, VertexDetails> undirected() {
        final Graph<EdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
        graph.add(); //0
        graph.add(); //1
        graph.add(); //2
        graph.add(); //3
        graph.add(); //4
        graph.add(); //5
        graph.connect(0, 1);
        graph.connect(0, 2);
        graph.connect(0, 3);
        graph.connect(1, 0);
        graph.connect(1, 2);
        graph.connect(1, 5);
        graph.connect(2, 0);
        graph.connect(2, 1);
        graph.connect(2, 3);
        graph.connect(3, 0);
        graph.connect(3, 2);
        graph.connect(3, 4);
        graph.connect(4, 3);
        graph.connect(4, 5);
        graph.connect(5, 4);
        graph.connect(5, 1);
        return graph;
    }

    public static Graph<EdgeDetails, VertexDetails> directed() {
        final Graph<EdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
        graph.add(); //0
        graph.add(); //1
        graph.add(); //2
        graph.add(); //3
        graph.add(); //4
        graph.add(); //5
        graph.connect(0, 1);
        graph.connect(0, 3);
        graph.connect(1, 3);
        graph.connect(2, 0);
        graph.connect(3, 2);
        graph.connect(4, 5);
        return graph;
    }

    /**
     * @return the weighted graph on page 632 of CLRS book, 3rd edition, accodring to the ebook version
     */
    public static Graph<WeightedEdgeDetails, VertexDetails> sampleWeightedGraph() {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
        graph.add(); //a: 0
        graph.add(); //b: 1
        graph.add(); //c: 2
        graph.add(); //d: 3
        graph.add(); //e: 4
        graph.add(); //f: 5
        graph.add(); //g: 6
        graph.add(); //h: 7
        graph.add(); //i: 8
        connect(graph, 0, 1, 4);
        connect(graph, 0, 7, 8);
        connect(graph, 1, 2, 8);
        connect(graph, 1, 7, 11);
        connect(graph, 2, 3, 7);
        connect(graph, 2, 5, 4);
        connect(graph, 2, 8, 2);
        connect(graph, 3, 4, 9);
        connect(graph, 3, 5, 14);
        connect(graph, 4, 5, 10);
        connect(graph, 4, 5, 10);
        connect(graph, 5, 6, 2);
        connect(graph, 6, 7, 1);
        connect(graph, 6, 8, 6);
        connect(graph, 7, 8, 7);
        return graph;
    }

    private static WeightedEdgeDetails weight(int weight) {
        final WeightedEdgeDetails details = new WeightedEdgeDetails();
        details.setWeight(weight);
        return details;
    }

    private static void connect(Graph<WeightedEdgeDetails, VertexDetails> graph, int from, int to, int weight) {
        final WeightedEdgeDetails edgeDetails = weight(weight);
        graph.connect(from, to, edgeDetails);
        graph.connect(to, from, edgeDetails);
    }

}
