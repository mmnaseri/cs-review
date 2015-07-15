package com.mmnaseri.cs.clrs.ch2.s2;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;

public class SelectionSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new SelectionSorter<>(NATURAL_COMPARATOR);
    }
}