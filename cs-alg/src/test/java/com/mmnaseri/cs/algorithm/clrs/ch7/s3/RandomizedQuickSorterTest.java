package com.mmnaseri.cs.algorithm.clrs.ch7.s3;

import com.mmnaseri.cs.algorithm.common.BaseSortTest;
import com.mmnaseri.cs.algorithm.common.Sorter;
import org.testng.annotations.Test;

public class RandomizedQuickSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new RandomizedQuickSorter<>(NATURAL_COMPARATOR);
    }

    @Override
    @Test(dataProvider = "normalDataProvider", invocationCount = 100)
    public void testSortIntegrity(Integer[] items, Integer[] expected) throws Exception {
        super.testSortIntegrity(items, expected);
    }

}