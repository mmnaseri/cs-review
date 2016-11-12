package com.mmnaseri.cs.skiena.ch03.s3;

import com.mmnaseri.cs.clrs.ch13.s1.RedBlackTreeNode;
import com.mmnaseri.cs.clrs.ch13.s3.RedBlackTree;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 10:38 AM)
 */
@Quality(Stage.TESTED)
public class MinimumDeleteBalancedTreeSorter<E extends Comparable<E>> extends AbstractBalancedTreeSorter<E> {

    public MinimumDeleteBalancedTreeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void sort(E[] items, RedBlackTree<E> tree) {
        RedBlackTreeNode<E> current = tree.minimum();
        int index = 0;
        while (current != null) {
            items[index ++] = current.getValue();
            tree.delete(current);
            current = tree.minimum();
        }
    }

}
