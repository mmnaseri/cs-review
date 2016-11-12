package com.mmnaseri.cs.skiena.ch03.s3;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;
import com.mmnaseri.cs.clrs.ch12.s2.SearchTree;
import com.mmnaseri.cs.clrs.ch13.s3.RedBlackTree;
import com.mmnaseri.cs.clrs.common.Sorter;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 10:20 AM)
 */
public abstract class AbstractBalancedTreeSorter<E extends Comparable<E>> implements Sorter<E> {

    private final Comparator<E> comparator;

    public AbstractBalancedTreeSorter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(E[] items) {
        final RedBlackTree<E> tree = new RedBlackTree<>(comparator);
        for (E item : items) {
            tree.insert(item);
        }
        sort(items, tree);
    }

    protected abstract void sort(E[] items, RedBlackTree<E> tree);

}
