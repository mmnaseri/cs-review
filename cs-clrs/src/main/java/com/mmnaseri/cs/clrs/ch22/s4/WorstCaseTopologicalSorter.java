package com.mmnaseri.cs.clrs.ch22.s4;

import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 11:48 PM)
 */
@Quality(Stage.UNTESTED)
public class WorstCaseTopologicalSorter<E extends EdgeDetails, V extends VertexDetails> implements TopologicalSorter<E,V> {

    @Override
    public List<Vertex<V>> sort(Graph<E, V> graph) {
        final List<Integer> sorted = new LinkedList<>();
        while (sorted.size() < graph.size()) {
            int vertex = pick(graph, sorted);
            if (vertex == -1) {
                return null;
            }
            sorted.add(vertex);
        }
        final LinkedList<Vertex<V>> result = new LinkedList<>();
        for (Integer index : sorted) {
            result.add(graph.get(index));
        }
        return result;
    }

    private int pick(Graph<E, V> graph, List<Integer> sorted) {
        int index = -1;
        for (int i = 0; i < graph.size(); i++) {
            if (sorted.contains(i)) {
                continue;
            }
            index = i;
            for (int j = 0; j < graph.size(); j++) {
                if (sorted.contains(j)) {
                    continue;
                }
                if (graph.edge(j, i) != null) {
                    index = -1;
                    break;
                }
            }
            if (index != -1) {
                break;
            }
        }
        return index;
    }

}
