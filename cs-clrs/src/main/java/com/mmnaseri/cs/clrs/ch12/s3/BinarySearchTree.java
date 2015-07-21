package com.mmnaseri.cs.clrs.ch12.s3;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import com.mmnaseri.cs.clrs.ch12.s2.AbstractBinarySearchTree;
import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/18/15, 8:05 PM)
 */
@SuppressWarnings("unchecked")
@Quality(Stage.TESTED)
public class BinarySearchTree<E, N extends BinaryTreeNode<E>> extends AbstractBinarySearchTree<E, N> {

    public BinarySearchTree(Comparator<E> comparator, TreeNodeFactory<E, N> factory) {
        super(comparator, factory);
    }

    @Override
    protected N insert(N root, E value) {
        if (lessThan(value, root.getValue())) {
            if (root.getLeftChild() == null) {
                final N node = getFactory().createNode(value);
                root.setLeftChild(node);
                return node;
            } else {
                return insert((N) root.getLeftChild(), value);
            }
        } else {
            if (root.getRightChild() == null) {
                final N node = getFactory().createNode(value);
                root.setRightChild(node);
                return node;
            } else {
                return insert((N) root.getRightChild(), value);
            }
        }
    }

    @Override
    protected void delete(N node) {
        if (node.getLeftChild() == null) {
            transplant(node, (N) node.getRightChild());
        } else if (node.getRightChild() == null) {
            transplant(node, (N) node.getLeftChild());
        } else {
            final N nextOfKin = minimum((N) node.getRightChild());
            if (nextOfKin.getParent() != node) {
                transplant(nextOfKin, (N) nextOfKin.getRightChild());
                nextOfKin.setRightChild(node.getRightChild());
            }
            transplant(node, nextOfKin);
            nextOfKin.setLeftChild(node.getLeftChild());
        }
    }

    protected void transplant(N original, N substitute) {
        if (original.isRoot()) {
            setRoot(substitute);
        } else if (original.getParent().getLeftChild() == original) {
            original.getParent().setLeftChild(substitute);
        } else {
            original.getParent().setRightChild(substitute);
        }
        if (substitute != null) {
            substitute.setParent(original.getParent());
        }
    }

}
