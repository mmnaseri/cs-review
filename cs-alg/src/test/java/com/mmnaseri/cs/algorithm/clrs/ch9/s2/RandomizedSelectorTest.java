package com.mmnaseri.cs.algorithm.clrs.ch9.s2;

import com.mmnaseri.cs.algorithm.clrs.ch9.Selector;
import org.testng.annotations.Test;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 5:55 PM)
 */
public class RandomizedSelectorTest extends BaseSelectorTest {

    @Override
    protected Selector<Integer> getSelector() {
        return new RandomizedSelector<>(NATURAL_ORDER);
    }

    @Override
    @Test(invocationCount = 50, dataProvider = "normalDataProvider")
    public void testSelectorIntegrity(Integer[] data, int order, Integer expectation) throws Exception {
        super.testSelectorIntegrity(data, order, expectation);
    }
}