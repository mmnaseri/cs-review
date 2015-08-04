package com.mmnaseri.cs.clrs.ch22.s4;

import com.mmnaseri.cs.clrs.ch22.GraphSamples;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/3/15)
 */
@SuppressWarnings("unchecked")
public abstract class BaseTopologicalSorterTest {

    protected abstract TopologicalSorter<EdgeDetails, VertexDetails> getTopologicalSorter();

    @Test
    public void testUndirected() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = GraphSamples.undirected();
        final List<Vertex<VertexDetails>> list = getTopologicalSorter().sort(graph);
        assertThat(list, contains(graph.get(0), graph.get(1), graph.get(2), graph.get(3), graph.get(4), graph.get(5)));
    }

    @Test
    public void testDirected() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = GraphSamples.directed();
        final List<Vertex<VertexDetails>> list = getTopologicalSorter().sort(graph);
        assertThat(list, contains(graph.get(4), graph.get(5), graph.get(0), graph.get(1), graph.get(3), graph.get(2)));
    }

}