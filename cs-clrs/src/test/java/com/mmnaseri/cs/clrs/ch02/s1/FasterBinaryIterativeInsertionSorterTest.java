package com.mmnaseri.cs.clrs.ch02.s1;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/13/15)
 */
public class FasterBinaryIterativeInsertionSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new FasterBinaryIterativeInsertionSorter<>(NATURAL_COMPARATOR);
    }

}