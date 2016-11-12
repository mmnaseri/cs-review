package com.mmnaseri.cs.skiena.ch03.s3;

import com.mmnaseri.cs.clrs.common.Sorter;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 10:45 AM)
 */
public class MinimumDeleteBalancedTreeSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getSorter(Comparator<Integer> comparator) {
        return new MinimumDeleteBalancedTreeSorter<>(comparator);
    }

}