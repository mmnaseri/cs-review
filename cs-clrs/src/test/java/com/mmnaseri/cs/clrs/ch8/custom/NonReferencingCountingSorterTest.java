package com.mmnaseri.cs.clrs.ch8.custom;

import com.mmnaseri.cs.clrs.ch8.s2.CountingSorterTest;
import com.mmnaseri.cs.clrs.common.Sorter;

public class NonReferencingCountingSorterTest extends CountingSorterTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new NonReferencingCountingSorter(NATURAL_COMPARATOR);
    }

}