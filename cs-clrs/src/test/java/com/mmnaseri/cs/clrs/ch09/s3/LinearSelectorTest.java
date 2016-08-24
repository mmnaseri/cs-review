package com.mmnaseri.cs.clrs.ch09.s3;

import com.mmnaseri.cs.clrs.ch09.Selector;
import com.mmnaseri.cs.clrs.ch09.s2.BaseSelectorTest;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/12/15, 7:50 PM)
 */
public class LinearSelectorTest extends BaseSelectorTest {

    @Override
    protected Selector<Integer> getSelector() {
        return new LinearSelector<>(NATURAL_ORDER);
    }

}