package com.mmnaseri.cs.algorithm.clrs.ch2.s2;

import com.mmnaseri.cs.algorithm.common.BaseSortTest;
import com.mmnaseri.cs.algorithm.common.Sorter;

public class SelectionSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new SelectionSorter<>(NATURAL_COMPARATOR);
    }
}