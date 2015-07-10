package com.mmnaseri.cs.algorithm.clrs.ch7.s1;

import com.mmnaseri.cs.algorithm.BaseSortTest;
import com.mmnaseri.cs.algorithm.common.Sorter;

public class QuickSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new QuickSorter<>(NATURAL_COMPARATOR);
    }
}