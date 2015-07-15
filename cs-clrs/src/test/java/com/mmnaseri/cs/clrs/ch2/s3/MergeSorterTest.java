package com.mmnaseri.cs.clrs.ch2.s3;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;

public class MergeSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new MergeSorter<>(NATURAL_COMPARATOR);
    }

}