package com.mmnaseri.cs.clrs.ch22.s4;

import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.common.GraphSamples;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/3/15)
 */
@SuppressWarnings("unchecked")
public abstract class BaseTopologicalSorterTest {

    protected abstract TopologicalSorter<EdgeDetails, VertexDetails> getTopologicalSorter();

    @Test
    public void testUndirected() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = GraphSamples.undirected();
        final List<Vertex<VertexDetails>> list = getTopologicalSorter().sort(graph);
        final List<Integer> indices = new ArrayList<>();
        for (Vertex<VertexDetails> vertex : list) {
            indices.add(vertex.getIndex());
        }
        assertThat(indices, contains(0, 1, 2, 3, 4, 5));
    }

    @Test
    public void testDirected() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = GraphSamples.directed();
        final List<Vertex<VertexDetails>> list = getTopologicalSorter().sort(graph);
        final List<Integer> indices = new ArrayList<>();
        for (Vertex<VertexDetails> vertex : list) {
            indices.add(vertex.getIndex());
        }
        assertThat(indices, contains(4, 5, 0, 1, 3, 2));
    }

}