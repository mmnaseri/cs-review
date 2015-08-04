package com.mmnaseri.cs.clrs.ch22.s3;

import com.mmnaseri.cs.clrs.ch22.GraphSamples;
import com.mmnaseri.cs.clrs.ch22.GraphVertexVisitorAdapter;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.common.ParameterizedTypeReference;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/3/15)
 */
public class DepthFirstGraphVisitorTest {

    @Test
    public void testDirected() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = GraphSamples.directed();
        final DepthFirstGraphVisitor<EdgeDetails, VertexDetails> visitor = new DepthFirstGraphVisitor<>();
        visitor.visit(graph, -1, new GraphVertexVisitorAdapter<EdgeDetails, VertexDetails>() {
        });
        assertThat(graph.get(0).getProperty("discovery", Integer.class), is(1));
        assertThat(graph.get(1).getProperty("discovery", Integer.class), is(2));
        assertThat(graph.get(2).getProperty("discovery", Integer.class), is(4));
        assertThat(graph.get(3).getProperty("discovery", Integer.class), is(3));
        assertThat(graph.get(4).getProperty("discovery", Integer.class), is(9));
        assertThat(graph.get(5).getProperty("discovery", Integer.class), is(10));
        assertThat(graph.get(0).getProperty("finish", Integer.class), is(8));
        assertThat(graph.get(1).getProperty("finish", Integer.class), is(7));
        assertThat(graph.get(2).getProperty("finish", Integer.class), is(5));
        assertThat(graph.get(3).getProperty("finish", Integer.class), is(6));
        assertThat(graph.get(4).getProperty("finish", Integer.class), is(12));
        assertThat(graph.get(5).getProperty("finish", Integer.class), is(11));
        assertThat(graph.get(0).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(nullValue()));
        assertThat(graph.get(1).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(graph.get(0)));
        assertThat(graph.get(2).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(graph.get(3)));
        assertThat(graph.get(3).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(graph.get(1)));
        assertThat(graph.get(4).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(nullValue()));
        assertThat(graph.get(5).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(graph.get(4)));
    }

    @Test
    public void testUndirected() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = GraphSamples.undirected();
        final DepthFirstGraphVisitor<EdgeDetails, VertexDetails> visitor = new DepthFirstGraphVisitor<>();
        visitor.visit(graph, -1, new GraphVertexVisitorAdapter<EdgeDetails, VertexDetails>() {
        });
        assertThat(graph.get(0).getProperty("discovery", Integer.class), is(1));
        assertThat(graph.get(1).getProperty("discovery", Integer.class), is(2));
        assertThat(graph.get(2).getProperty("discovery", Integer.class), is(3));
        assertThat(graph.get(3).getProperty("discovery", Integer.class), is(4));
        assertThat(graph.get(4).getProperty("discovery", Integer.class), is(5));
        assertThat(graph.get(5).getProperty("discovery", Integer.class), is(6));
        assertThat(graph.get(0).getProperty("finish", Integer.class), is(12));
        assertThat(graph.get(1).getProperty("finish", Integer.class), is(11));
        assertThat(graph.get(2).getProperty("finish", Integer.class), is(10));
        assertThat(graph.get(3).getProperty("finish", Integer.class), is(9));
        assertThat(graph.get(4).getProperty("finish", Integer.class), is(8));
        assertThat(graph.get(5).getProperty("finish", Integer.class), is(7));
        assertThat(graph.get(0).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(nullValue()));
        assertThat(graph.get(1).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(graph.get(0)));
        assertThat(graph.get(2).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(graph.get(1)));
        assertThat(graph.get(3).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(graph.get(2)));
        assertThat(graph.get(4).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(graph.get(3)));
        assertThat(graph.get(5).getProperty("parent", new ParameterizedTypeReference<Vertex<VertexDetails>>() {}), is(graph.get(4)));
    }
}