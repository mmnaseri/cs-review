package com.mmnaseri.cs.algorithm.clrs.ch2.sp;

import com.mmnaseri.cs.algorithm.common.BaseSortTest;
import com.mmnaseri.cs.algorithm.common.Sorter;

public class BubbleSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new BubbleSorter<>(NATURAL_COMPARATOR);
    }
}