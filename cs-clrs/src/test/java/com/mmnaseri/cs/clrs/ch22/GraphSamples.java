package com.mmnaseri.cs.clrs.ch22;

import com.mmnaseri.cs.clrs.ch22.s1.AdjacencyListGraph;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/3/15)
 */
public abstract class GraphSamples {

    public static Graph<EdgeDetails, VertexDetails> undirected() {
        final AdjacencyListGraph<EdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
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
        final AdjacencyListGraph<EdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
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

}
