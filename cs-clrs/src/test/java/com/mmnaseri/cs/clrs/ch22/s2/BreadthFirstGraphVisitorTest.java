package com.mmnaseri.cs.clrs.ch22.s2;

import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitorAdapter;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.common.GraphSamples;
import com.mmnaseri.cs.clrs.common.ParameterizedTypeReference;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/3/15)
 */
public class BreadthFirstGraphVisitorTest {

    @Test
    public void testDirectedGraph() throws Exception {
        final BreadthFirstGraphVisitor<EdgeDetails, VertexDetails> visitor = new BreadthFirstGraphVisitor<>();
        final Graph<EdgeDetails, VertexDetails> graph = GraphSamples.directed();
        final Graph<EdgeDetails, VertexDetails> result = visitor.visit(graph, 0, new GraphVertexVisitorAdapter<EdgeDetails, VertexDetails>() {
        });
        assertThat(result.get(0).getProperty("distance", Integer.class), is(0));
        assertThat(result.get(1).getProperty("distance", Integer.class), is(1));
        assertThat(result.get(2).getProperty("distance", Integer.class), is(2));
        assertThat(result.get(3).getProperty("distance", Integer.class), is(1));
        assertThat(result.get(4).getProperty("distance", Integer.class), is(Integer.MAX_VALUE));
        assertThat(result.get(5).getProperty("distance", Integer.class), is(Integer.MAX_VALUE));
        assertThat(result.get(0).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(nullValue()));
        assertThat(result.get(1).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(0)));
        assertThat(result.get(2).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(3)));
        assertThat(result.get(3).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(0)));
        assertThat(result.get(4).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(nullValue()));
        assertThat(result.get(5).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(nullValue()));
    }

    @Test
    public void testUndirectedGraph() throws Exception {
        final BreadthFirstGraphVisitor<EdgeDetails, VertexDetails> visitor = new BreadthFirstGraphVisitor<>();
        final Graph<EdgeDetails, VertexDetails> graph = GraphSamples.undirected();
        final Graph<EdgeDetails, VertexDetails> result = visitor.visit(graph, 0, new GraphVertexVisitorAdapter<EdgeDetails, VertexDetails>() {
        });
        assertThat(result.get(0).getProperty("distance", Integer.class), is(0));
        assertThat(result.get(1).getProperty("distance", Integer.class), is(1));
        assertThat(result.get(2).getProperty("distance", Integer.class), is(1));
        assertThat(result.get(3).getProperty("distance", Integer.class), is(1));
        assertThat(result.get(4).getProperty("distance", Integer.class), is(2));
        assertThat(result.get(5).getProperty("distance", Integer.class), is(2));
        assertThat(result.get(0).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(nullValue()));
        assertThat(result.get(1).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(0)));
        assertThat(result.get(2).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(0)));
        assertThat(result.get(3).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(0)));
        assertThat(result.get(4).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(3)));
        assertThat(result.get(5).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(result.get(1)));
    }
    
}