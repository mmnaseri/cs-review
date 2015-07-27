package com.mmnaseri.cs.clrs.ch13.s3;

import org.testng.annotations.Test;

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

    @Test
    public void testBulkInsertion() throws Exception {
        final RedBlackTree<Integer> tree = tree();
        tree.insert(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
    }

    @Test
    public void testBulkDeletion() throws Exception {
        final RedBlackTree<Integer> tree = tree();
        tree.insert(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        System.out.println(tree.getRoot());
        tree.delete(1);
        tree.delete(2);
        tree.delete(3);
        tree.delete(4);
        tree.delete(5);
        tree.delete(6);
        tree.delete(7);
        tree.delete(8);
        tree.delete(9);
        tree.delete(10);
        tree.delete(11);
        tree.delete(12);
        tree.delete(13);
        tree.delete(14);
        tree.delete(15);
    }
}