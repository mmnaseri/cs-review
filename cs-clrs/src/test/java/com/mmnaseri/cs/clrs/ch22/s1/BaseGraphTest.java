package com.mmnaseri.cs.clrs.ch22.s1;

import com.mmnaseri.cs.clrs.common.ParameterizedTypeReference;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/3/15)
 */
public abstract class BaseGraphTest {

    protected abstract Graph<EdgeDetails, VertexDetails> graph();

    @Test
    public void testInitialState() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        assertThat(graph, is(notNullValue()));
        assertThat(graph.size(), is(0));
        assertThat(graph.isEmpty(), is(true));
        assertThat(graph.getVertices(), is(empty()));
    }

    @Test
    public void testAddingAVertex() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        assertThat(graph.size(), is(0));
        assertThat(graph.isEmpty(), is(true));
        final VertexDetails details = new SimpleVertexDetails();
        final int index = graph.add(details);
        assertThat(graph.size(), is(1));
        assertThat(graph.isEmpty(), is(false));
        assertThat(index, is(0));
    }

    @Test
    public void testRetrievingAVertex() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        final VertexDetails details = new SimpleVertexDetails();
        final int index = graph.add(details);
        final Vertex<VertexDetails> vertex = graph.get(0);
        assertThat(vertex, is(notNullValue()));
        assertThat(vertex.getIndex(), is(index));
        assertThat(vertex.getDetails(), is(details));
    }

    @Test
    public void testDeletingAVertex() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        final int index = graph.add(null);
        assertThat(graph.size(), is(1));
        assertThat(graph.isEmpty(), is(false));
        graph.delete(index);
        assertThat(graph.size(), is(0));
        assertThat(graph.isEmpty(), is(true));
    }

    @Test
    public void testAddingVertices() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        assertThat(graph.size(), is(0));
        assertThat(graph.isEmpty(), is(true));
        for (int i = 0; i < 500; i++) {
            final int index = graph.add(null);
            assertThat(index, is(i));
            assertThat(graph.size(), is(i + 1));
            assertThat(graph.isEmpty(), is(false));
        }
    }

    @Test
    public void testRetrievingVertices() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        final List<VertexDetails> vertices = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            final VertexDetails details = new SimpleVertexDetails();
            vertices.add(details);
            graph.add(details);
        }
        for (int i = 0; i < graph.size(); i++) {
            final Vertex<VertexDetails> vertex = graph.get(i);
            vertex.setProperty("index", i);
            assertThat(vertex, is(notNullValue()));
            assertThat(vertex.getIndex(), is(i));
            assertThat(vertex.getDetails(), is(notNullValue()));
            assertThat(vertex.getDetails(), is(vertices.get(i)));
        }
        final List<Vertex<VertexDetails>> list = graph.getVertices();
        for (int i = 0; i < list.size(); i++) {
            final Vertex<VertexDetails> vertex = list.get(i);
            assertThat(vertex, is(notNullValue()));
            assertThat(vertex.getIndex(), is(i));
            assertThat(vertex.getDetails(), is(notNullValue()));
            assertThat(vertex.getDetails(), is(vertices.get(i)));
            assertThat(vertex.getProperty("index"), is(notNullValue()));
            assertThat(vertex.getProperty("index", Integer.class), is(notNullValue()));
            assertThat(vertex.getProperty("index", Integer.class), is(i));
        }
    }

    @Test(invocationCount = 10)
    public void testDeletingVertices() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        final List<VertexDetails> vertices = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            final VertexDetails details = new SimpleVertexDetails();
            vertices.add(details);
            graph.add(details);
        }
        final Random random = new Random();
        for (int i = 0; i < 500; i++) {
            final int from = random.nextInt(500);
            final int to = random.nextInt(500);
            graph.connect(from, to);
            graph.connect(to, from);
        }
        while (!graph.isEmpty()) {
            final VertexDetails details = graph.delete(0);
            assertThat(details, is(notNullValue()));
            assertThat(details, is(vertices.get(0)));
            vertices.remove(0);
        }
    }

    @Test
    public void testCreatingEdges() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        for (int i = 0; i < 50; i++) {
            graph.add();
        }
        final Set<Integer> expectedNeighbors = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 20; j < 30; j++) {
                final Edge<EdgeDetails, VertexDetails> edge = graph.connect(i, j);
                edge.setProperty("reference", new AtomicReference<>(i * j));
                expectedNeighbors.add(j);
            }
        }
        for (int i = 0; i < 10; i++) {
            final List<Vertex<VertexDetails>> neighbors = graph.getNeighbors(graph.get(i));
            final Set<Integer> neighborsIndices = new HashSet<>();
            for (Vertex<VertexDetails> neighbor : neighbors) {
                neighborsIndices.add(neighbor.getIndex());
            }
            assertThat(neighborsIndices, is(expectedNeighbors));
            for (int j = 0; j < 20; j++) {
                assertThat(graph.edge(i, j), is(nullValue()));
            }
            for (int j = 20; j < 30; j++) {
                final Edge<EdgeDetails, VertexDetails> edge = graph.edge(i, j);
                assertThat(edge.getFrom(), is(notNullValue()));
                assertThat(edge.getFrom().getIndex(), is(i));
                assertThat(edge.getTo(), is(notNullValue()));
                assertThat(edge.getTo().getIndex(), is(j));
                assertThat(edge, is(notNullValue()));
                assertThat(edge.getProperty("reference"), is(notNullValue()));
                assertThat(edge.getProperty("reference", new ParameterizedTypeReference<AtomicReference<Integer>>() {
                }).get(), is(i * j));
            }
            for (int j = 30; j < 50; j++) {
                assertThat(graph.edge(i, j), is(nullValue()));
            }
        }
        for (int i = 10; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                assertThat(graph.edge(i, j), is(nullValue()));
            }
        }
    }

    @Test
    public void testRemovingEdges() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        for (int i = 0; i < 50; i++) {
            graph.add();
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 20; j < 30; j++) {
                graph.connect(i, j);
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 20; j < 30; j++) {
                graph.disconnect(i, j);
            }
        }
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                assertThat(graph.edge(i, j), is(nullValue()));
            }
        }
    }

    @Test
    public void testDisconnectingNonExistentEdge() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        for (int i = 0; i < 50; i++) {
            graph.add();
        }
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                final EdgeDetails details = graph.disconnect(i, j);
                assertThat(details, is(nullValue()));
            }
        }
    }

    @Test
    public void testTakingTheInverse() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        for (int i = 0; i < 50; i++) {
            graph.add();
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 20; j < 30; j++) {
                graph.connect(i, j);
            }
        }
        final Graph<EdgeDetails, VertexDetails> inverse = graph.inverse();
        assertThat(inverse.size(), is(graph.size()));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                assertThat(inverse.edge(i, j), is(notNullValue()));
            }
            for (int j = 20; j < 30; j++) {
                assertThat(inverse.edge(i, j), is(nullValue()));
            }
            for (int j = 30; j < 50; j++) {
                assertThat(inverse.edge(i, j), is(notNullValue()));
            }
        }
        for (int i = 10; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                assertThat(inverse.edge(i, j), is(notNullValue()));
            }
        }
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testAddressingABelowRangeVertex() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        for (int i = 0; i < 50; i++) {
            graph.add();
        }
        graph.get(-1);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testAddressingAnAboveRangeVertex() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        for (int i = 0; i < 50; i++) {
            graph.add();
        }
        graph.get(50);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testAddressingAnOutOfRangeFromVertexForEdge() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        for (int i = 0; i < 50; i++) {
            graph.add();
        }
        graph.edge(51, 20);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testAddressingAnOutOfRangeToVertexForEdge() throws Exception {
        final Graph<EdgeDetails, VertexDetails> graph = graph();
        for (int i = 0; i < 50; i++) {
            graph.add();
        }
        graph.edge(20, 51);
    }

}
