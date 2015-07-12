package com.mmnaseri.cs.algorithm.common;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;
import static org.hamcrest.collection.IsArrayWithSize.emptyArray;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/9/15, 8:53 PM)
 */
public abstract class BaseSortTest {
    
    protected abstract Sorter<Integer> getAscendingSorter();

    protected static final Comparator<Integer> NATURAL_COMPARATOR = new Comparator<Integer>() {
        @Override
        public int compare(Integer first, Integer second) {
            return first.compareTo(second);
        }
    };

    @Test
    public void testSortingEmptyArray() throws Exception {
        final Integer[] items = {};
        getAscendingSorter().sort(items);
        assertThat(items, emptyArray());
    }

    @Test
    public void testSimpleAscendingSortWithNegativeItems() throws Exception {
        final Integer[] items = {-7, -9, -8, 7, 9, 8, 0};
        final int length = items.length;
        getAscendingSorter().sort(items);
        assertThat(items, arrayWithSize(length));
        assertThat(items, arrayContaining(-9, -8, -7, 0, 7, 8, 9));
    }

    @Test
    public void testAscendingSortWithInfinity() throws Exception {
        final Integer[] items = {1, 2, 3, Integer.MIN_VALUE, 0, Integer.MAX_VALUE, 4, 5, 6};
        final int length = items.length;
        getAscendingSorter().sort(items);
        assertThat(items, arrayWithSize(length));
        assertThat(items, arrayContaining(Integer.MIN_VALUE, 0, 1, 2, 3, 4, 5, 6, Integer.MAX_VALUE));
    }

    @DataProvider
    public Object[][] normalDataProvider() {
        return new Object[][]{
                new Object[]{new Integer[]{10}, new Integer[]{10}}, //testSortingSingleItemList
                new Object[]{new Integer[]{1, 9, 3, 7, 3, 0}, new Integer[]{0, 1, 3, 3, 7, 9}}, //testSimpleAscendingSort
                new Object[]{new Integer[]{8, 8, 8, 7, 7, 7, 6, 6, 6}, new Integer[]{6, 6, 6, 7, 7, 7, 8, 8, 8}}, //testWithDuplicates
                new Object[]{new Integer[]{1, 2, 3, 4, 5, 6}, new Integer[]{1, 2, 3, 4, 5, 6}}, //testSortingAlreadySortedArray
                new Object[]{new Integer[]{6, 5, 4, 3, 2, 1}, new Integer[]{1, 2, 3, 4, 5, 6}}, //testSortingReversedArray
        };
    }

    @Test(dataProvider = "normalDataProvider", invocationCount = 50)
    public void testSortIntegrity(Integer[] items, Integer[] expected) throws Exception {
        final Integer[] target = new Integer[items.length];
        System.arraycopy(items, 0, target, 0, target.length);
        getAscendingSorter().sort(target);
        assertThat(target, arrayWithSize(expected.length));
        assertThat(target, arrayContaining(expected));
    }

}
