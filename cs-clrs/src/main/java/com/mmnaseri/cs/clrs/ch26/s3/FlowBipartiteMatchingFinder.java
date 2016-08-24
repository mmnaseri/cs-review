package com.mmnaseri.cs.clrs.ch26.s3;

import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitorAdapter;
import com.mmnaseri.cs.clrs.ch22.GraphVisitor;
import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.clrs.ch22.s3.DepthFirstGraphVisitor;
import com.mmnaseri.cs.clrs.ch26.s1.FlowEdgeDetails;
import com.mmnaseri.cs.clrs.ch26.s1.MaximumFlowFinder;
import com.mmnaseri.cs.clrs.ch26.s1.MutableFlowEdgeDetails;
import com.mmnaseri.cs.clrs.ch26.s2.EdmondsKarpMaximumFlowFinder;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/26/15)
 */
@Quality(Stage.TESTED)
public class FlowBipartiteMatchingFinder<E extends EdgeDetails, V extends VertexDetails> implements BipartiteMatchingFinder<E, V> {

    private final MaximumFlowFinder<FlowEdgeDetails, V> finder = new EdmondsKarpMaximumFlowFinder<>();
    private final GraphVisitor<E, V> visitor = new DepthFirstGraphVisitor<>();

    @Override
    public Graph<E, V> find(Graph<E, V> bipartiteGraph) {
        final Graph<E, V> copy = GraphUtils.copy(bipartiteGraph);
        final Graph<FlowEdgeDetails, V> network = createFlowNetwork(bipartiteGraph);
        final Graph<FlowEdgeDetails, V> maximumFlow = finder.find(network, network.size() - 2, network.size() - 1);
        for (Vertex<V> vertex : network) {
            if (((Integer) 0).equals(vertex.getProperty("part"))) {
                final List<Vertex<V>> neighbors = network.getNeighbors(vertex);
                for (Vertex<V> neighbor : neighbors) {
                    final boolean selected = maximumFlow.edge(vertex.getIndex(), neighbor.getIndex()).getDetails().getFlow() > 0;
                    copy.edge(vertex.getIndex(), neighbor.getIndex()).setProperty("selected", selected);
                    copy.edge(neighbor.getIndex(), vertex.getIndex()).setProperty("selected", selected);
                }
            }
        }
        return copy;
    }

    protected Graph<FlowEdgeDetails, V> createFlowNetwork(Graph<E, V> bipartiteGraph) {
        final Graph<FlowEdgeDetails, V> network = createBareFlowNetwork(bipartiteGraph);
        discoverPartitioning(bipartiteGraph, network);
        setupNetworkConnections(bipartiteGraph, network);
        setEdgeCapacities(network);
        return network;
    }

    protected void setEdgeCapacities(Graph<FlowEdgeDetails, V> network) {
        for (Edge<FlowEdgeDetails, V> edge : network.getEdges()) {
            final MutableFlowEdgeDetails details = new MutableFlowEdgeDetails();
            details.setCapacity(1);
            details.setFlow(0);
            edge.setDetails(details);
        }
    }

    protected void setupNetworkConnections(Graph<E, V> bipartiteGraph, Graph<FlowEdgeDetails, V> network) {
        final int source = network.add();
        final int destination = network.add();
        network.get(source).setProperty("part", -1);
        network.get(destination).setProperty("part", 2);
        //set up the source and destination
        for (Vertex<V> vertex : network) {
            if (((Integer) 0).equals(vertex.getProperty("part"))) {
                network.connect(source, vertex.getIndex());
            } else if (((Integer) 1).equals(vertex.getProperty("part"))) {
                network.connect(vertex.getIndex(), destination);
            }
        }
        //set up internal connections between the two parts
        for (Vertex<V> vertex : network) {
            //we only initiate edges from the left partition
            if (((Integer) 0).equals(vertex.getProperty("part"))) {
                final List<Vertex<V>> neighbors = bipartiteGraph.getNeighbors(vertex.getIndex());
                for (Vertex<V> neighbor : neighbors) {
                    network.connect(vertex.getIndex(), neighbor.getIndex());
                }
            }
        }
    }

    protected void discoverPartitioning(Graph<E, V> bipartiteGraph, Graph<FlowEdgeDetails, V> network) {
        final Graph<E, V> visitation = visitor.visit(bipartiteGraph, 0, new GraphVertexVisitorAdapter<E, V>() {
        });
        for (Vertex<V> vertex : visitation) {
            network.get(vertex.getIndex()).setProperty("part", vertex.getProperty("distance", Integer.class) % 2);
        }
    }

    protected Graph<FlowEdgeDetails, V> createBareFlowNetwork(Graph<E, V> bipartiteGraph) {
        final Graph<FlowEdgeDetails, V> network = new AdjacencyListGraph<>();
        for (Vertex<V> vertex : bipartiteGraph) {
            network.add(vertex.getDetails());
        }
        return network;
    }

}
