package com.mmnaseri.cs.clrs.ch8.s2;

import com.mmnaseri.cs.clrs.common.BaseSortTest;
import com.mmnaseri.cs.clrs.common.Sorter;
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