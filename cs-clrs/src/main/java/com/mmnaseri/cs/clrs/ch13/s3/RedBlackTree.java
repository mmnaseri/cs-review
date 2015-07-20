package com.mmnaseri.cs.clrs.ch13.s3;

import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import com.mmnaseri.cs.clrs.ch13.s1.NodeColor;
import com.mmnaseri.cs.clrs.ch13.s1.RedBlackTreeNode;
import com.mmnaseri.cs.clrs.ch13.s2.RotatingBinarySearchTree;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/19/15, 11:55 PM)
 */
public class RedBlackTree<E> extends RotatingBinarySearchTree<E, RedBlackTreeNode<E>> {

    public RedBlackTree(Comparator<E> comparator) {
        super(comparator, new TreeNodeFactory<E, RedBlackTreeNode<E>>() {
            @Override
            public RedBlackTreeNode<E> createNode(E value) {
                final RedBlackTreeNode<E> node = new RedBlackTreeNode<>();
                node.setValue(value);
                return node;
            }
        });
    }

    @Override
    public RedBlackTreeNode<E> insert(E value) {
        final RedBlackTreeNode<E> node = getFactory().createNode(value);
        RedBlackTreeNode<E> current = getRoot();
        RedBlackTreeNode<E> parent = null;
        while (current != null) {
            parent = current;
            if (lessThan(value, current.getValue())) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }
        node.setParent(parent);
        if (parent == null) {
            setRoot(node);
        } else if (lessThan(value, parent.getValue())) {
            parent.setLeftChild(node);
        } else {
            parent.setRightChild(node);
        }
        node.setColor(NodeColor.RED);
        insertFix(node);
        return node;
    }

    protected void insertFix(RedBlackTreeNode<E> node) {
        while (node != null && node.getParent() != null && node.getParent().getColor().equals(NodeColor.RED)) {
            if (node.getParent() == node.getParent().getParent().getLeftChild()) {
                final RedBlackTreeNode<E> target = node.getParent().getParent().getRightChild();
                if (target.getColor().equals(NodeColor.RED)) {
                    node.getParent().setColor(NodeColor.BLACK);
                    target.setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getRightChild()) {
                        node = node.getParent();
                        rotateLeft(node);
                    }
                    node.getParent().setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    rotateRight(node.getParent().getParent());
                }
            } else {
                final RedBlackTreeNode<E> target = node.getParent().getParent().getLeftChild();
                if (target.getColor().equals(NodeColor.RED)) {
                    node.getParent().setColor(NodeColor.BLACK);
                    target.setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getLeftChild()) {
                        node = node.getParent();
                        rotateRight(node);
                    }
                    node.getParent().setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    rotateLeft(node.getParent().getParent());
                }
            }
        }
        getRoot().setColor(NodeColor.BLACK);
    }

}
