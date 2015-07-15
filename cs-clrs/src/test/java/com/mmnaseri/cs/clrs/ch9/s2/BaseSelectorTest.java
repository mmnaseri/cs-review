package com.mmnaseri.cs.clrs.ch9.s2;

import com.mmnaseri.cs.clrs.ch9.Selector;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
            new Object[]{new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 6, 7},
            new Object[]{new Integer[]{5, 4, 3, 2, 1, 0, -1, -2, -3, -4}, 3, -1},
            new Object[]{new Integer[]{-1, -2, 3, 4, 0, 6, 7, 8, 9}, 2, 0},
            new Object[]{new Integer[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1, 2, 3, 4, 5, 6}, 4, 3},
            new Object[]{new Integer[]{1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 7, 8, 5, 6, 7, 8}, 11, 6},
            new Object[]{new Integer[]{1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 7, 8, 5, 6, 7, 8}, 12, 7},
            new Object[]{new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, -1, 1},
            new Object[]{new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, 8, 8},
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
