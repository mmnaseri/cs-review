package com.mmnaseri.cs.algorithm.common;

import com.mmnaseri.cs.algorithm.common.Sorter;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
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
    public void testSortingSingleItemList() throws Exception {
        final Integer[] items = {10};
        getAscendingSorter().sort(items);
        assertThat(items, arrayWithSize(1));
        assertThat(items, arrayContainingInAnyOrder(10));
    }

    @Test
    public void testSimpleAscendingSort() throws Exception {
        final Integer[] items = {1, 9, 3, 7, 3, 0};
        final int length = items.length;
        getAscendingSorter().sort(items);
        assertThat(items, arrayWithSize(length));
        assertThat(items, arrayContaining(0, 1, 3, 3, 7, 9));
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

    @Test
    public void testWithDuplicates() throws Exception {
        final Integer[] items = {8, 8, 8, 7, 7, 7, 6, 6, 6};
        final int length = items.length;
        getAscendingSorter().sort(items);
        assertThat(items, arrayWithSize(length));
        assertThat(items, arrayContaining(6, 6, 6, 7, 7, 7, 8, 8, 8));
    }

}
