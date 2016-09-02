package com.mmnaseri.cs.clrs.ch05.custom;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (9/2/16, 2:40 PM)
 */
public class StoogeSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new StoogeSorter<>(NATURAL_COMPARATOR);
    }

}