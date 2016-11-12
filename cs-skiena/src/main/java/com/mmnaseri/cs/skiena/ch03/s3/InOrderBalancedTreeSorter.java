package com.mmnaseri.cs.skiena.ch03.s3;

import com.mmnaseri.cs.clrs.ch12.s1.NonRecursiveInOrderTreeWalk;
import com.mmnaseri.cs.clrs.ch12.s1.TreeWalk;
import com.mmnaseri.cs.clrs.ch12.s1.TreeWalkCallback;
import com.mmnaseri.cs.clrs.ch13.s1.RedBlackTreeNode;
import com.mmnaseri.cs.clrs.ch13.s3.RedBlackTree;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 10:23 AM)
 */
@Quality(Stage.TESTED)
public class InOrderBalancedTreeSorter<E extends Comparable<E>> extends AbstractBalancedTreeSorter<E> {

    public InOrderBalancedTreeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void sort(final E[] items, RedBlackTree<E> tree) {
        final AtomicInteger index = new AtomicInteger(0);
        final TreeWalk<E, RedBlackTreeNode<E>> walk = new NonRecursiveInOrderTreeWalk<>();
        walk.perform(tree.getRoot(), new TreeWalkCallback<E, RedBlackTreeNode<E>>() {
            @Override
            public void apply(RedBlackTreeNode<E> node) {
                items[index.getAndIncrement()] = node.getValue();
            }
        });
    }

}
