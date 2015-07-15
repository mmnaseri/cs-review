package com.mmnaseri.cs.clrs.ch8.s3;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RadixSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new RadixSorter();
    }

    @DataProvider
    public Object[][] normalDataProvider() {
        return new Object[][]{
                new Object[]{new Integer[]{10}, new Integer[]{10}}, //testSortingSingleItemList
                new Object[]{new Integer[]{101, 109, 103, 107, 103, 100}, new Integer[]{100, 101, 103, 103, 107, 109}}, //testSimpleAscendingSort
                new Object[]{new Integer[]{1288, 1288, 1288, 1167, 1167, 1167, 9976, 9976, 9976}, new Integer[]{1167, 1167, 1167, 1288, 1288, 1288, 9976, 9976, 9976}}, //testWithDuplicates
                new Object[]{new Integer[]{321, 322, 323, 324, 325, 326}, new Integer[]{321, 322, 323, 324, 325, 326}}, //testSortingAlreadySortedArray
                new Object[]{new Integer[]{326, 325, 324, 323, 322, 321}, new Integer[]{321, 322, 323, 324, 325, 326}}, //testSortingReversedArray
                new Object[]{new Integer[]{320, 291, 106}, new Integer[]{106, 291, 320}}, //testSortingWithReversedHighestDigit
                new Object[]{new Integer[]{34, 37, 11, 1, 610, 4, 612}, new Integer[]{1, 4, 11, 34, 37, 610, 612}}, //testSortingDifferentDigitCounts
        };
    }

    @Override
    //radix sort supports only positive values
    @Test(enabled = false)
    public void testSimpleAscendingSortWithNegativeItems() throws Exception {
    }

    @Override
    //radix sort supports only finite numbers
    @Test(enabled = false)
    public void testAscendingSortWithInfinity() throws Exception {
    }
}