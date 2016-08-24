package com.mmnaseri.cs.clrs.ch16.s2;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/24/15)
 */
@Quality(Stage.TESTED)
public class WholeItemKnapsackFiller {

    @Complexity(value = "O(nW)", explanation = "We go over the all items for each value of weight in 1..W")
    public Set<Integer> fill(int size, int... weights) {
        final int[][] memory = new int[size + 1][];
        final int[] indices = new int[size + 1];
        for (int i = 0; i < size + 1; i++) {
            memory[i] = new int[weights.length + 1];
            memory[i][0] = 0;
            indices[i] = -1;
        }
        for (int i = 0; i < weights.length + 1; i ++) {
            memory[0][i] = 0;
        }
        for (int weight = 1; weight <= size; weight ++) {
            for (int i = 1; i <= weights.length; i ++) {
                if (weights[i - 1] > weight) {
                    memory[weight][i] = memory[weight][i - 1];
                } else {
                    final int current = weights[i - 1] + memory[weight - weights[i - 1]][i - 1];
                    if (current > memory[weight][i - 1]) {
                        indices[weight] = i;
                        memory[weight][i] = current;
                    } else {
                        memory[weight][i] = memory[weight][i - 1];
                    }
                }
            }
        }
        final Set<Integer> result = new HashSet<>();
        int weight = memory[size][weights.length];
        while (weight > 0) {
            final int index = indices[weight] - 1;
            result.add(index);
            weight -= weights[(index)];
        }
        return result;
    }

}
