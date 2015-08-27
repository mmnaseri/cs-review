package com.mmnaseri.cs.clrs.common;

import com.mmnaseri.cs.clrs.ch22.s1.AdjacencyListGraph;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch23.s1.MutableWeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch26.s1.FlowEdgeDetails;
import com.mmnaseri.cs.clrs.ch26.s1.MutableFlowEdgeDetails;

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
     * @return the weighted graph on page 632 of CLRS book, 3rd edition, according to the ebook version
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
        bidiConnect(graph, 0, 1, 4);
        bidiConnect(graph, 0, 7, 8);
        bidiConnect(graph, 1, 2, 8);
        bidiConnect(graph, 1, 7, 11);
        bidiConnect(graph, 2, 3, 7);
        bidiConnect(graph, 2, 5, 4);
        bidiConnect(graph, 2, 8, 2);
        bidiConnect(graph, 3, 4, 9);
        bidiConnect(graph, 3, 5, 14);
        bidiConnect(graph, 4, 5, 10);
        bidiConnect(graph, 4, 5, 10);
        bidiConnect(graph, 5, 6, 2);
        bidiConnect(graph, 6, 7, 1);
        bidiConnect(graph, 6, 8, 6);
        bidiConnect(graph, 7, 8, 7);
        return graph;
    }

    /**
     * @return the weighted graph on page 653 of CLRS book, 3rd edition, according to the ebook version
     */
    public static Graph<WeightedEdgeDetails, VertexDetails> sampleWeightedGraphWithNegativeLoops() {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
        graph.add(); //s: 0
        graph.add(); //t: 1
        graph.add(); //x: 2
        graph.add(); //y: 3
        graph.add(); //z: 4
        connect(graph, 0, 1, 6);
        connect(graph, 0, 3, 7);
        connect(graph, 1, 2, 5);
        connect(graph, 1, 3, 8);
        connect(graph, 1, 4, -4);
        connect(graph, 2, 1, -2);
        connect(graph, 3, 2, -3);
        connect(graph, 3, 4, 9);
        connect(graph, 4, 0, 2);
        connect(graph, 4, 2, 7);
        return graph;
    }

    /**
     * @return the weighted graph on page 656 of CLRS book, 3rd edition, according to the ebook version
     */
    public static Graph<WeightedEdgeDetails, VertexDetails> sampleWeightedGraphWithoutNegativeLoops() {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
        graph.add(); //r: 0
        graph.add(); //s: 1
        graph.add(); //t: 2
        graph.add(); //x: 3
        graph.add(); //y: 4
        graph.add(); //z: 4
        connect(graph, 0, 1, 5);
        connect(graph, 0, 2, 3);
        connect(graph, 1, 2, 2);
        connect(graph, 1, 3, 6);
        connect(graph, 2, 3, 7);
        connect(graph, 2, 4, 4);
        connect(graph, 2, 5, 2);
        connect(graph, 3, 4, -1);
        connect(graph, 3, 5, 1);
        connect(graph, 4, 5, -2);
        return graph;
    }

    /**
     * @return the weighted graph on page 659 of CLRS book, 3rd edition, according to the ebook version
     */
    public static Graph<WeightedEdgeDetails, VertexDetails> sampleWeightedLoopingGraphWithoutNegativeEdges() {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
        graph.add(); //s: 0
        graph.add(); //t: 1
        graph.add(); //x: 2
        graph.add(); //y: 3
        graph.add(); //z: 4
        connect(graph, 0, 1, 10);
        connect(graph, 0, 3, 5);
        connect(graph, 1, 2, 1);
        connect(graph, 1, 3, 2);
        connect(graph, 2, 4, 4);
        connect(graph, 3, 1, 3);
        connect(graph, 3, 2, 9);
        connect(graph, 3, 4, 2);
        connect(graph, 4, 0, 7);
        connect(graph, 4, 2, 6);
        return graph;
    }

    public static Graph<WeightedEdgeDetails, VertexDetails> sampleWeightedGraphForAllPairsShortestPathCalculation() {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
        graph.add();
        graph.add();
        graph.add();
        graph.add();
        connect(graph, 0, 1, 4);
        connect(graph, 0, 2, 3);
        connect(graph, 0, 3, 6);
        connect(graph, 1, 2, 1);
        connect(graph, 2, 0, 2);
        connect(graph, 2, 3, 1);
        connect(graph, 3, 2, 1);
        return graph;
    }

    /**
     * @return the weighted graph on page 690 of CLRS book, 3rd edition, according to the ebook version
     */
    public static Graph<WeightedEdgeDetails, VertexDetails> sampleFiveVertexWeightedGraphForAllPairsShortestPathCalculation() {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
        graph.add();
        graph.add();
        graph.add();
        graph.add();
        graph.add();
        connect(graph, 0, 1, 3);
        connect(graph, 0, 2, 8);
        connect(graph, 0, 4, -4);
        connect(graph, 1, 3, 1);
        connect(graph, 1, 4, 7);
        connect(graph, 2, 1, 4);
        connect(graph, 3, 0, 2);
        connect(graph, 3, 2, -5);
        connect(graph, 4, 3, 6);
        return graph;
    }

    /**
     * @return the flow network from page 710 of CLRS book, 3rd edition, according to the ebook version
     */
    public static Graph<FlowEdgeDetails, VertexDetails> sampleFlowNetwork() {
        final Graph<FlowEdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
        graph.add(); //0: s
        graph.add(); //1: v1
        graph.add(); //2: v2
        graph.add(); //3: v3
        graph.add(); //4: v4
        graph.add(); //5: t
        flow(graph, 0, 1, 16);
        flow(graph, 0, 2, 13);
        flow(graph, 1, 3, 12);
        flow(graph, 2, 1, 4);
        flow(graph, 2, 4, 14);
        flow(graph, 3, 2, 9);
        flow(graph, 3, 5, 20);
        flow(graph, 4, 3, 7);
        flow(graph, 4, 5, 4);
        return graph;
    }

    /**
     * @return sample bipartite graph from page 733 of CLRS 3rd edition according to the ebook
     */
    public static Graph<EdgeDetails, VertexDetails> sampleBipartiteGraph() {
        final Graph<EdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
        graph.add();
        graph.add();
        graph.add();
        graph.add();
        graph.add();
        graph.add();
        graph.add();
        graph.add();
        graph.add();
        graph.connect(0, 5);
        graph.connect(5, 0);
        graph.connect(1, 5);
        graph.connect(1, 7);
        graph.connect(5, 1);
        graph.connect(7, 1);
        graph.connect(2, 6);
        graph.connect(2, 7);
        graph.connect(2, 8);
        graph.connect(6, 2);
        graph.connect(7, 2);
        graph.connect(8, 2);
        graph.connect(3, 7);
        graph.connect(7, 3);
        graph.connect(4, 7);
        graph.connect(7, 4);
        return graph;
    }

    private static void flow(Graph<FlowEdgeDetails, VertexDetails> graph, int from, int to, int capacity) {
        final MutableFlowEdgeDetails details = new MutableFlowEdgeDetails();
        details.setCapacity(capacity);
        details.setFlow(0);
        graph.connect(from, to, details);
    }

    private static WeightedEdgeDetails weight(int weight) {
        final MutableWeightedEdgeDetails details = new MutableWeightedEdgeDetails();
        details.setWeight(weight);
        return details;
    }

    private static void bidiConnect(Graph<WeightedEdgeDetails, VertexDetails> graph, int from, int to, int weight) {
        final WeightedEdgeDetails edgeDetails = weight(weight);
        graph.connect(from, to, edgeDetails);
        graph.connect(to, from, edgeDetails);
    }

    private static void connect(Graph<WeightedEdgeDetails, VertexDetails> graph, int from, int to, int weight) {
        final WeightedEdgeDetails edgeDetails = weight(weight);
        graph.connect(from, to, edgeDetails);
    }
}
