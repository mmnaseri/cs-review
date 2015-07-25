package com.mmnaseri.cs.clrs.ch16.s2;

import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/24/15)
 */
public class WholeItemKnapsackFillerTest {

    private Set<Integer> fill(int size, int... weights) {
        return new WholeItemKnapsackFiller().fill(size, weights);
    }

    @Test
    public void testFillingTornKnapsack() throws Exception {
        final Set<Integer> indices = fill(0, 1, 2, 3, 4);
        assertThat(indices, is(empty()));
    }

    private int sum(int[] weights, Set<Integer> indices) {
        int weight = 0;
        for (Integer index : indices) {
            weight += weights[index];
        }
        return weight;
    }

    @Test
    public void testReplacingTheContent() throws Exception {
        final int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final Set<Integer> indices = fill(10, weights);
        assertThat(sum(weights, indices), is(10));
        assertThat(indices, is(not(empty())));
        assertThat(indices, hasSize(4));
        assertThat(indices, contains(0, 1, 2, 3)); //1 + 2 + 3 + 4
    }

    @Test
    public void testReversedOrder() throws Exception {
        final int[] weights = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        final Set<Integer> indices = fill(10, weights);
        assertThat(sum(weights, indices), is(10));
        assertThat(indices, is(not(empty())));
        assertThat(indices, hasSize(1));
        assertThat(indices, contains(0)); //10
    }

    @Test
    public void testMixed() throws Exception {
        final int[] weights = {1, 7, 2, 6, 3, 5, 4};
        final Set<Integer> indices = fill(10, weights);
        assertThat(sum(weights, indices), is(10));
        assertThat(indices, is(not(empty())));
        assertThat(indices, hasSize(3));
        assertThat(indices, contains(0, 1, 2)); //1 + 7 + 2
    }

}