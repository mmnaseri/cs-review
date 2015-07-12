package com.mmnaseri.cs.algorithm.clrs.ch9.custom;

import com.mmnaseri.cs.algorithm.clrs.ch2.s1.InsertionSorter;
import com.mmnaseri.cs.algorithm.clrs.ch9.Selector;
import com.mmnaseri.cs.algorithm.clrs.ch9.s2.BaseSelectorTest;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 6:13 PM)
 */
public class SortingSelectorTest extends BaseSelectorTest {

    @Override
    protected Selector<Integer> getSelector() {
        return new SortingSelector<>(new InsertionSorter<>(NATURAL_ORDER));
    }

}