package com.mmnaseri.cs.clrs.ch07.s3;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;
import org.testng.annotations.Test;

public class RandomizedQuickSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new RandomizedQuickSorter<>(NATURAL_COMPARATOR);
    }

    @Override
    @Test(dataProvider = "normalDataProvider", invocationCount = 100)
    public void testSortIntegrity(Integer[] items) throws Exception {
        super.testSortIntegrity(items);
    }

}