package com.mmnaseri.cs.skiena.ch03.s3;

import com.mmnaseri.cs.clrs.ch13.s1.RedBlackTreeNode;
import com.mmnaseri.cs.clrs.ch13.s3.RedBlackTree;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 10:34 AM)
 */
@Quality(Stage.TESTED)
public class MinimumSuccessorBalancedTreeSorter<E extends Comparable<E>> extends AbstractBalancedTreeSorter<E> {

    public MinimumSuccessorBalancedTreeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void sort(E[] items, RedBlackTree<E> tree) {
        RedBlackTreeNode<E> current = tree.minimum();
        for (int i = 0; i < items.length; i++) {
            items[i] = current.getValue();
            current = tree.successor(current);
        }
    }

}
