package com.mmnaseri.cs.skiena.ch04.s8.impl;

import com.mmnaseri.cs.skiena.ch04.s8.Merger;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 9:59 AM)
 */
public class HeapMergerTest extends BaseMergerTest {

    @Override
    protected Merger<Integer> getMerger() {
        return new HeapMerger<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer first, Integer second) {
                return Integer.compare(first, second);
            }
        });
    }

}