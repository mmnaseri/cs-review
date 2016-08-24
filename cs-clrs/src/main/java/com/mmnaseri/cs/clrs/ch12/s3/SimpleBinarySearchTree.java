package com.mmnaseri.cs.clrs.ch12.s3;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/18/15, 9:44 PM)
 */
@Quality(Stage.TESTED)
public class SimpleBinarySearchTree<E> extends BinarySearchTree<E, BinaryTreeNode<E>> {

    public SimpleBinarySearchTree(Comparator<E> comparator) {
        super(comparator, new TreeNodeFactory<E, BinaryTreeNode<E>>() {
            @Override
            public BinaryTreeNode<E> createNode(E value) {
                final BinaryTreeNode<E> node = new BinaryTreeNode<>();
                node.setValue(value);
                return node;
            }
        });
    }

}
