package com.mmnaseri.cs.clrs.ch15.s4;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15)
 */
public abstract class BaseLongestAscendingSubSequenceFinderTest {

    public static final Comparator<Integer> NATURAL_ORDER = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };

    protected abstract LongestAscendingSubSequenceFinder<Integer> getFinder();

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
            new Object[]{"sorted ascending", new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3, 4, 5}},
            new Object[]{"sorted descending", new Integer[]{5, 4, 3, 2, 1}, new Integer[]{5}},
            new Object[]{"two candidates", new Integer[]{1, 4, 2, 6, 3, 7, 4, 5}, new Integer[]{1, 2, 3, 4, 5}},
            new Object[]{"two candidates of same size", new Integer[]{1, 4, 6, 3, 7, 4, 5}, new Integer[]{1, 4, 6, 7}},
        };
    }

    @Test(dataProvider = "dataProvider")
    public void testFunctionality(@SuppressWarnings("UnusedParameters") String name, Integer[] sequence, Integer[] expectation) throws Exception {
        final LongestAscendingSubSequenceFinder<Integer> finder = getFinder();
        final List<Integer> list = finder.find(Arrays.asList(sequence));
        assertThat(list, is(notNullValue()));
        assertThat(list, hasSize(expectation.length));
        for (int i = 0; i < expectation.length; i++) {
            assertThat(list.get(i), is(expectation[i]));
        }
    }

}
