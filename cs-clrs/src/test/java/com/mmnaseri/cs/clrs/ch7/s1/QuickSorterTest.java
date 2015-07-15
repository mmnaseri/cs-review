package com.mmnaseri.cs.clrs.ch7.s1;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;

public class QuickSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new QuickSorter<>(NATURAL_COMPARATOR);
    }
}