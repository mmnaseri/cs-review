package com.mmnaseri.cs.skiena.ch04.s8.impl;

import com.mmnaseri.cs.skiena.ch04.s8.Merger;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (11/17/16, 3:19 PM)
 */
public class ComparingHeapMergerTest extends BaseMergerTest {

    @Override
    protected Merger<Integer> getMerger() {
        return new ComparingHeapMerger<>(NATURAL_ORDER);
    }

}