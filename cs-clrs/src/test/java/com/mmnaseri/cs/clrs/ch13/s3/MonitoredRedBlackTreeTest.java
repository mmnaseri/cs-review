package com.mmnaseri.cs.clrs.ch13.s3;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15, 1:34 AM)
 */
public class MonitoredRedBlackTreeTest extends RedBlackTreeTest {

    protected RedBlackTree<Integer> tree() {
        return new MonitoredRedBlackTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

}