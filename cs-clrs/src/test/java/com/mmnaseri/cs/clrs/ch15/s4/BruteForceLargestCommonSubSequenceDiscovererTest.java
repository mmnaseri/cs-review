package com.mmnaseri.cs.clrs.ch15.s4;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15)
 */
public class BruteForceLargestCommonSubSequenceDiscovererTest {

    protected LargestCommonSubSequenceDiscoverer<Integer> getFinder() {
        return new BruteForceLargestCommonSubSequenceDiscoverer<>();
    }

    @DataProvider
    public Object[][] testSuiteDataProvider() {
        return new Object[][]{
            new Object[]{"Nothing in common", new Integer[]{1, 2, 3, 4, 5, 6}, new Integer[]{7, 8, 9, 10, 11, 12}, new Integer[0]},
            new Object[]{"First is a prefix of second", new Integer[]{1, 2, 3}, new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3}},
            new Object[]{"First is a suffix of second", new Integer[]{3, 4, 5}, new Integer[]{1, 2, 3, 4, 5}, new Integer[]{3, 4, 5}},
            new Object[]{"First is a child of second", new Integer[]{3, 4, 5}, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, new Integer[]{3, 4, 5}},
            new Object[]{"Second is a prefix of second", new Integer[]{1, 2, 3, 4, 5, 6}, new Integer[]{1, 2, 3}, new Integer[]{1, 2, 3}},
            new Object[]{"Second is a suffix of second", new Integer[]{1, 2, 3, 4, 5, 6}, new Integer[]{4, 5, 6}, new Integer[]{4, 5, 6}},
            new Object[]{"Second is a child of second", new Integer[]{1, 2, 3, 4, 5, 6}, new Integer[]{2, 3, 4, 5}, new Integer[]{2, 3, 4, 5}},
            new Object[]{"Have two LCS", new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, new Integer[]{5, 6, 7, 8, 1, 2, 3, 4}, new Integer[]{1, 2, 3, 4}},
            new Object[]{"First is null", null, new Integer[]{1, 2, 3, 4}, null},
            new Object[]{"Second is null", new Integer[]{1, 2, 3, 4}, null, null},
            new Object[]{"Both are null", null, null, null},
        };
    }

    @Test(dataProvider = "testSuiteDataProvider")
    public void testFunctionality(@SuppressWarnings("UnusedParameters") String name, Integer[] first, Integer[] second, Integer[] expected) throws Exception {
        final LargestCommonSubSequenceDiscoverer<Integer> finder = getFinder();
        final List<Integer> firstList = first == null ? null : Arrays.asList(first);
        final List<Integer> secondList = second == null ? null : Arrays.asList(second);
        final List<Integer> largestCommonSubSequence = finder.find(firstList, secondList);
        if (expected == null) {
            assertThat(largestCommonSubSequence, is(nullValue()));
        } else {
            assertThat(largestCommonSubSequence, is(notNullValue()));
            assertThat(largestCommonSubSequence, hasSize(expected.length));
            for (int i = 0; i < expected.length; i++) {
                assertThat(largestCommonSubSequence.get(i), is(expected[i]));
            }
        }
    }

}