package com.mmnaseri.cs.algorithm.clrs.ch7.s3;

import com.mmnaseri.cs.algorithm.common.BaseSortTest;
import com.mmnaseri.cs.algorithm.common.Sorter;

public class RandomizedQuickSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new RandomizedQuickSorter<>(NATURAL_COMPARATOR);
    }
}