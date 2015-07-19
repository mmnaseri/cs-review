package com.mmnaseri.cs.clrs.ch12.s3;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/18/15, 9:44 PM)
 */
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
