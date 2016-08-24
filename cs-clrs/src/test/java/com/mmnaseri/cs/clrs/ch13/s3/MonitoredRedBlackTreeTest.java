package com.mmnaseri.cs.clrs.ch13.s3;

import org.testng.annotations.Test;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
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

    @Test
    public void testBulkInsertion() throws Exception {
        final RedBlackTree<Integer> tree = tree();
        tree.insert(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        tree.insert(-14, -13, -12, -11, -10, -9, -8, -7, -8, -6, -5, -4, -3, -2, -1);
    }

    @Test
    public void testBulkDeletion() throws Exception {
        final RedBlackTree<Integer> tree = tree();
        tree.insert(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        tree.delete(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        tree.insert(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        tree.delete(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    }

}