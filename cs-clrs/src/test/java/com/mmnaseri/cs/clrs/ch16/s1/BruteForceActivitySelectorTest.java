package com.mmnaseri.cs.clrs.ch16.s1;

import org.testng.annotations.Test;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/22/15)
 */
@Test(enabled = false)
public class BruteForceActivitySelectorTest extends BaseActivitySelectorTest {

    @Override
    protected ActivitySelector getActivitySelector() {
        return new BruteForceActivitySelector();
    }

}