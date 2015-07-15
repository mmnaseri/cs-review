package com.mmnaseri.cs.clrs.ch9.sp;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 9:08 PM)
 */
public class BestCaseQuickSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new BestCaseQuickSorter<>(NATURAL_COMPARATOR);
    }

}