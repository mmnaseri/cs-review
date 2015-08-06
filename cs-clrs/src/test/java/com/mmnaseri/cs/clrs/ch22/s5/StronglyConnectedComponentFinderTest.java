package com.mmnaseri.cs.clrs.ch22.s5;

import com.mmnaseri.cs.clrs.ch21.DisjointSet;
import com.mmnaseri.cs.clrs.ch21.Element;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.common.GraphSamples;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/3/15)
 */
@SuppressWarnings("unchecked")
public class StronglyConnectedComponentFinderTest {

    @Test
    public void testUndirected() throws Exception {
        final StronglyConnectedComponentFinder<EdgeDetails, VertexDetails> finder = new StronglyConnectedComponentFinder<>();
        final Graph<EdgeDetails, VertexDetails> graph = GraphSamples.undirected();
        final DisjointSet<Element<Vertex<VertexDetails>>, Vertex<VertexDetails>> set = (DisjointSet<Element<Vertex<VertexDetails>>, Vertex<VertexDetails>>) finder.find(graph);
        assertThat(set, is(notNullValue()));
        assertThat(set.sets(), hasSize(1));
        final Element<Vertex<VertexDetails>> root = set.sets().iterator().next();
        assertThat(set.elements(root), containsInAnyOrder(graph.get(0), graph.get(1), graph.get(2), graph.get(3), graph.get(4), graph.get(5)));
    }

    @Test
    public void testDirected() throws Exception {
        final StronglyConnectedComponentFinder<EdgeDetails, VertexDetails> finder = new StronglyConnectedComponentFinder<>();
        final Graph<EdgeDetails, VertexDetails> graph = GraphSamples.directed();
        final DisjointSet<Element<Vertex<VertexDetails>>, Vertex<VertexDetails>> set = (DisjointSet<Element<Vertex<VertexDetails>>, Vertex<VertexDetails>>) finder.find(graph);
        assertThat(set, is(notNullValue()));
        final Set<Element<Vertex<VertexDetails>>> sets = set.sets();
        assertThat(sets, hasSize(2));
        for (Element<Vertex<VertexDetails>> element : sets) {
            final Set<Vertex<VertexDetails>> elements = set.elements(element);
            if (elements.contains(graph.get(0))) {
                assertThat(elements, containsInAnyOrder(graph.get(0), graph.get(1), graph.get(2), graph.get(3)));
            } else {
                assertThat(elements, containsInAnyOrder(graph.get(4), graph.get(5)));
            }
        }
    }

}