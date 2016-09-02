package com.mmnaseri.cs.clrs.ch05.custom;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (9/2/16, 2:55 PM)
 */
public class ShellSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new ShellSorter<>(NATURAL_COMPARATOR, new int[]{701, 301, 132, 57, 23, 10, 4, 1});
    }

}