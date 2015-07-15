package com.mmnaseri.cs.clrs.ch8.s4;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;
import static org.hamcrest.collection.IsArrayWithSize.emptyArray;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 4:59 PM)
 */
public class BucketSorterTest {

    private BucketSorter sorter;

    @BeforeMethod
    public void setUp() throws Exception {
        sorter = new BucketSorter(10);
    }

    public BucketSorter getAscendingSorter() {
        return sorter;
    }

    @Test
    public void testSortingEmptyArray() throws Exception {
        final Double[] items = {};
        getAscendingSorter().sort(items);
        assertThat(items, emptyArray());
    }

    @Test
    public void testSimpleAscendingSortWithNegativeItems() throws Exception {
        final Double[] items = {-1.7, -1.9, -1.8, 1.7, 1.9, 1.8, 0.0};
        final int length = items.length;
        getAscendingSorter().sort(items);
        assertThat(items, arrayWithSize(length));
        assertThat(items, arrayContaining(-1.9, -1.8, -1.7, 0.0, 1.7, 1.8, 1.9));
    }

    @Test
    public void testAscendingSortWithInfinity() throws Exception {
        final Double[] items = {1., 2., 3., (double) Integer.MIN_VALUE, 0., (double) Integer.MAX_VALUE, 4., 5., 6.};
        final int length = items.length;
        getAscendingSorter().sort(items);
        assertThat(items, arrayWithSize(length));
        assertThat(items, arrayContaining((double) Integer.MIN_VALUE, 0., 1., 2., 3., 4., 5., 6., (double) Integer.MAX_VALUE));
    }

    @DataProvider
    public Object[][] normalDataProvider() {
        return new Object[][]{
                new Object[]{new Double[]{10.}, new Double[]{10.}}, //testSortingSingleItemList
                new Object[]{new Double[]{1.0, 9.0, 3.0, 7.0, 3.0, 0.0}, new Double[]{0.0, 1.0, 3.0, 3.0, 7.0, 9.0}}, //testSimpleAscendingSort
                new Object[]{new Double[]{8.0, 8.0, 8.0, 7.0, 7.0, 7.0, 6.0, 6.0, 6.0}, new Double[]{6.0, 6.0, 6.0, 7.0, 7.0, 7.0, 8.0, 8.0, 8.0}}, //testWithDuplicates
                new Object[]{new Double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0}, new Double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0}}, //testSortingAlreadySortedArray
                new Object[]{new Double[]{6.0, 5.0, 4.0, 3.0, 2.0, 1.0}, new Double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0}}, //testSortingReversedArray
        };
    }

    @Test(dataProvider = "normalDataProvider")
    public void testSortIntegrity(Double[] items, Double[] expected) throws Exception {
        final Double[] target = new Double[items.length];
        System.arraycopy(items, 0, target, 0, target.length);
        getAscendingSorter().sort(target);
        assertThat(target, arrayWithSize(expected.length));
        assertThat(target, arrayContaining(expected));
    }

}