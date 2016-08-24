package com.mmnaseri.cs.clrs.ch26.s2;

import com.mmnaseri.cs.clrs.ch22.s1.Edge;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.ch26.s1.FlowEdgeDetails;
import com.mmnaseri.cs.clrs.ch26.s1.MaximumFlowFinder;
import com.mmnaseri.cs.clrs.common.GraphSamples;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/26/15)
 */
public class EdmondsKarpMaximumFlowFinderTest {

    @Test
    public void testAgainstSample() throws Exception {
        final Graph<FlowEdgeDetails, VertexDetails> network = GraphSamples.sampleFlowNetwork();
        final MaximumFlowFinder<FlowEdgeDetails, VertexDetails> finder = new EdmondsKarpMaximumFlowFinder<>();
        final int source = 0;
        final int destination = 5;
        final Graph<FlowEdgeDetails, VertexDetails> graph = finder.find(network, source, destination);
        assertThat(graph, is(notNullValue()));
        assertThat(graph.size(), is(network.size()));
        int outgoingFlow = 0;
        int incomingFlow = 0;
        for (int i = 0; i < graph.size(); i++) {
            final Edge<FlowEdgeDetails, VertexDetails> edge = graph.edge(source, i);
            if (edge != null) {
                outgoingFlow += edge.getDetails().getFlow();
            }
        }
        for (int i = 0; i < graph.size(); i++) {
            final Edge<FlowEdgeDetails, VertexDetails> edge = graph.edge(i, destination);
            if (edge != null) {
                incomingFlow += edge.getDetails().getFlow();
            }
        }
        assertThat(outgoingFlow, is(incomingFlow));
        assertThat(outgoingFlow, is(23));
        final Matrix<Integer> flows = new ArrayMatrix<>(graph.size(), graph.size());
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                flows.set(i, j, 0);
            }
        }
        flows.set(0, 1, 12);
        flows.set(0, 2, 11);
        flows.set(1, 3, 12);
        flows.set(2, 4, 11);
        flows.set(3, 5, 19);
        flows.set(4, 3, 7);
        flows.set(4, 5, 4);
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                final Edge<FlowEdgeDetails, VertexDetails> edge = graph.edge(i, j);
                if (edge == null) {
                    assertThat(flows.get(i, j), is(0));
                    continue;
                }
                final int flow = edge.getDetails().getFlow();
                assertThat(flow, is(flows.get(i, j)));
            }
        }
    }

}