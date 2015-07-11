package com.mmnaseri.cs.algorithm.clrs.ch8.s2;

import com.mmnaseri.cs.algorithm.common.BaseSortTest;
import com.mmnaseri.cs.algorithm.common.Sorter;
import org.testng.annotations.Test;

public class CountingSorterTest extends BaseSortTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new CountingSorter(NATURAL_COMPARATOR);
    }

    //counting sort assumes the maximum item to be a finite, small number
    @Override
    @Test(enabled = false)
    public void testAscendingSortWithInfinity() throws Exception {
        super.testAscendingSortWithInfinity();
    }
}