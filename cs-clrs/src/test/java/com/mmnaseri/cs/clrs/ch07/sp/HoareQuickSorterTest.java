package com.mmnaseri.cs.clrs.ch07.sp;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;

public class HoareQuickSorterTest extends BaseSortTest{

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new HoareQuickSorter<>(NATURAL_COMPARATOR);
    }

}