package com.mmnaseri.cs.clrs.ch2.sp;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;

public class BubbleSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new BubbleSorter<>(NATURAL_COMPARATOR);
    }
}