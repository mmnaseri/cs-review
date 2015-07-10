package com.mmnaseri.cs.algorithm.clrs.ch2.s1;

import com.mmnaseri.cs.algorithm.BaseSortTest;
import com.mmnaseri.cs.algorithm.common.Sorter;

public class InsertionSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new InsertionSorter<>(NATURAL_COMPARATOR);
    }

}