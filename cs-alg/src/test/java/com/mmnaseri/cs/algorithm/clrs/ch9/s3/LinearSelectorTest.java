package com.mmnaseri.cs.algorithm.clrs.ch9.s3;

import com.mmnaseri.cs.algorithm.clrs.ch9.Selector;
import com.mmnaseri.cs.algorithm.clrs.ch9.s2.BaseSelectorTest;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 7:50 PM)
 */
public class LinearSelectorTest extends BaseSelectorTest {

    @Override
    protected Selector<Integer> getSelector() {
        return new LinearSelector<>(NATURAL_ORDER);
    }

}