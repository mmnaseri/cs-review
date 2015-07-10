package com.mmnaseri.cs.algorithm.clrs.ch6.s4;

import com.mmnaseri.cs.algorithm.BaseSortTest;
import com.mmnaseri.cs.algorithm.clrs.ch6.s1.ArrayHeap;
import com.mmnaseri.cs.algorithm.common.Sorter;
import com.mmnaseri.cs.algorithm.common.impl.MinHeapProperty;

public class HeapSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new HeapSorter<>(new ArrayHeap<>(new MinHeapProperty<Integer>()));
    }
}