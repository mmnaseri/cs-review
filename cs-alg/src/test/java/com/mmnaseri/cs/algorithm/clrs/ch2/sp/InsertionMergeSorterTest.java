package com.mmnaseri.cs.algorithm.clrs.ch2.sp;

import com.mmnaseri.cs.algorithm.BaseSortTest;
import com.mmnaseri.cs.algorithm.common.Sorter;

public class InsertionMergeSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new InsertionMergeSorter<Integer>(NATURAL_COMPARATOR);
    }

}