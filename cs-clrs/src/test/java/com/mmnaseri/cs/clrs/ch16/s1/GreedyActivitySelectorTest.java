package com.mmnaseri.cs.clrs.ch16.s1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/22/15, 10:50 PM)
 */
public class GreedyActivitySelectorTest extends BaseActivitySelectorTest {

    @Override
    protected ActivitySelector getActivitySelector() {
        return new GreedyActivitySelector();
    }
}