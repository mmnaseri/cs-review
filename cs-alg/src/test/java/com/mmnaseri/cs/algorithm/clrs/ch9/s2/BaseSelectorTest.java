package com.mmnaseri.cs.algorithm.clrs.ch9.s2;

import com.mmnaseri.cs.algorithm.clrs.ch9.Selector;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 6:14 PM)
 */
public abstract class BaseSelectorTest {

    public static final Comparator<Integer> NATURAL_ORDER = new Comparator<Integer>() {
        @Override
        public int compare(Integer first, Integer second) {
            return first.compareTo(second);
        }
    };

    protected abstract Selector<Integer> getSelector();

    @DataProvider
    public Object[][] normalDataProvider() {
        return new Object[][]{
            new Object[]{new Integer[]{1, 2, 3, 4, 5}, 3, 4}, //test sorted array
            new Object[]{new Integer[]{5, 4, 3, 2, 1}, 3, 4}, //test reversed array
            new Object[]{new Integer[]{-1, -2, 3, 4, 0}, 2, 0}, //test with negatives
            new Object[]{new Integer[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0}, 1, 0}, //test with infinity
            new Object[]{new Integer[]{1, 2, 3, 4, 1, 2, 3, 4}, 5, 3}, //test with duplicates
            new Object[]{new Integer[]{1, 2, 3, 4, 1, 2, 3, 4}, 6, 4}, //test with duplicates (2)
            new Object[]{new Integer[]{1, 2, 3}, -1, 1}, //test violating lower bound
            new Object[]{new Integer[]{1, 2, 3}, 4, 3}, //test violating upper bound
        };
    }

    @Test(dataProvider = "normalDataProvider")
    public void testSelectorIntegrity(Integer[] data, int order, Integer expectation) throws Exception {
        final Integer[] items = new Integer[data.length];
        System.arraycopy(data, 0, items, 0, items.length);
        final Integer selected = getSelector().select(order, items);
        assertThat(selected, is(notNullValue()));
        assertThat(selected, is(expectation));
    }

    @Test
    public void testSelectingFromEmptyArray() throws Exception {
        assertThat(getSelector().select(0), is(nullValue()));
        assertThat(getSelector().select(1), is(nullValue()));
        assertThat(getSelector().select(2), is(nullValue()));
        assertThat(getSelector().select(3), is(nullValue()));
    }

}
