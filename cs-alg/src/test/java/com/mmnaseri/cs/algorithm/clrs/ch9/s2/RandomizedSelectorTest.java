package com.mmnaseri.cs.algorithm.clrs.ch9.s2;

import com.mmnaseri.cs.algorithm.clrs.ch9.Selector;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 5:55 PM)
 */
public class RandomizedSelectorTest extends BaseSelectorTest {

    @Override
    protected Selector<Integer> getSelector() {
        return new RandomizedSelector<>(NATURAL_ORDER);
    }

}