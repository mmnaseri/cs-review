package com.mmnaseri.cs.algorithm.clrs.ch8.custom;

import com.mmnaseri.cs.algorithm.clrs.ch8.s2.CountingSorterTest;
import com.mmnaseri.cs.algorithm.common.Sorter;

public class NonReferencingCountingSorterTest extends CountingSorterTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new NonReferencingCountingSorter(NATURAL_COMPARATOR);
    }

}