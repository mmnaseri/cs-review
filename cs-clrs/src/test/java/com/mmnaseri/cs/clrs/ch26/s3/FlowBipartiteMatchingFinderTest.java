package com.mmnaseri.cs.clrs.ch26.s3;

import com.mmnaseri.cs.clrs.ch22.s1.Edge;
import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.clrs.common.GraphSamples;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixCell;
import com.mmnaseri.cs.clrs.common.MatrixRow;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/26/15)
 */
public class FlowBipartiteMatchingFinderTest {

    @Test
    public void testName() throws Exception {
        final BipartiteMatchingFinder<EdgeDetails, VertexDetails> finder = new FlowBipartiteMatchingFinder<>();
        final Graph<EdgeDetails, VertexDetails> graph = GraphSamples.sampleBipartiteGraph();
        final Graph<EdgeDetails, VertexDetails> result = finder.find(graph);
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(graph.size()));
        final Matrix<Boolean> selection = new ArrayMatrix<>(graph.size(), graph.size());
        for (MatrixRow<Boolean> row : selection) {
            for (MatrixCell<Boolean> cell : row) {
                selection.set(cell.getRowNumber(), cell.getColumnNumber(), false);
            }
        }
        selection.set(0, 5, true);
        selection.set(2, 6, true);
        selection.set(1, 7, true);
        selection.set(5, 0, true);
        selection.set(6, 2, true);
        selection.set(7, 1, true);
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.size(); j++) {
                final Edge<EdgeDetails, VertexDetails> edge = result.edge(i, j);
                if (edge == null) {
                    assertThat(selection.get(i, j), is(false));
                } else {
                    assertThat(edge.getProperty("selected", Boolean.class), is(selection.get(i, j)));
                }
            }
        }
    }

}