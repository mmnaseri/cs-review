package com.mmnaseri.cs.clrs.ch6.s4;

import com.mmnaseri.cs.clrs.ch6.s1.ArrayHeap;
import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.clrs.common.impl.MinHeapProperty;

public class HeapSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new HeapSorter<>(new ArrayHeap<>(new MinHeapProperty<Integer>()));
    }
}