package com.mmnaseri.cs.clrs.ch09.sp;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/12/15, 9:08 PM)
 */
public class BestCaseQuickSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new BestCaseQuickSorter<>(NATURAL_COMPARATOR);
    }

}