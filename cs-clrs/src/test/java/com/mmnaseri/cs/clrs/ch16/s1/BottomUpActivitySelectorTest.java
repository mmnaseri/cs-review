package com.mmnaseri.cs.clrs.ch16.s1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/23/15)
 */
public class BottomUpActivitySelectorTest extends BaseActivitySelectorTest {

    @Override
    protected ActivitySelector getActivitySelector() {
        return new BottomUpActivitySelector();
    }

}