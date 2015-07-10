package com.mmnaseri.cs.algorithm.clrs.ch2.s3;

import com.mmnaseri.cs.algorithm.BaseSortTest;
import com.mmnaseri.cs.algorithm.common.Sorter;

public class MergeSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new MergeSorter<>(NATURAL_COMPARATOR);
    }

}