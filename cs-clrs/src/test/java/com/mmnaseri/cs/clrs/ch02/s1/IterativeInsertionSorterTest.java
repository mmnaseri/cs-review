package com.mmnaseri.cs.clrs.ch02.s1;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;

public class IterativeInsertionSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new IterativeInsertionSorter<>(NATURAL_COMPARATOR);
    }

}