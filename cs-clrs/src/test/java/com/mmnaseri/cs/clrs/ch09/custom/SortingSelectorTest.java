package com.mmnaseri.cs.clrs.ch09.custom;

import com.mmnaseri.cs.clrs.ch02.s1.IterativeInsertionSorter;
import com.mmnaseri.cs.clrs.ch09.Selector;
import com.mmnaseri.cs.clrs.ch09.s2.BaseSelectorTest;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/11/15, 6:13 PM)
 */
public class SortingSelectorTest extends BaseSelectorTest {

    @Override
    protected Selector<Integer> getSelector() {
        return new SortingSelector<>(new IterativeInsertionSorter<>(NATURAL_ORDER));
    }

}