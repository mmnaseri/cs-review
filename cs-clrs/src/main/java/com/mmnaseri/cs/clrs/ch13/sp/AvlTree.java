package com.mmnaseri.cs.clrs.ch13.sp;

import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import com.mmnaseri.cs.clrs.ch13.s2.RotatingBinarySearchTree;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/20/15, 12:54 AM)
 */
@Quality(Stage.UNTESTED)
public class AvlTree<E> extends RotatingBinarySearchTree<E, AvlTreeNode<E>> {

    public AvlTree(Comparator<E> comparator) {
        super(comparator, new TreeNodeFactory<E, AvlTreeNode<E>>() {
            @Override
            public AvlTreeNode<E> createNode(E value) {
                final AvlTreeNode<E> node = new AvlTreeNode<>();
                node.setValue(value);
                return node;
            }
        });
    }

    @Override
    public AvlTreeNode<E> insert(E value) {
        final AvlTreeNode<E> node = getFactory().createNode(value);
        AvlTreeNode<E> current = getRoot();
        AvlTreeNode<E> parent = null;
        while (current != null) {
            parent = current;
            if (lessThan(value, current.getValue())) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }
        if (parent == null) {
            setRoot(node);
        } else if (lessThan(value, parent.getValue())) {
            parent.setLeftChild(node);
        } else {
            parent.setRightChild(node);
        }
        insertFix(node);
        return node;
    }

    protected void insertFix(AvlTreeNode<E> node) {
        while (node != null) {
            int balancingFactor = node.getBalancingFactor();
            if (balancingFactor < -1) {
                rotateRight(node);
                node = node.getParent();
            } else if (balancingFactor > 1) {
                rotateLeft(node);
                node = node.getParent();
            }
            node = node.getParent();
        }
    }

    @Override
    public AvlTreeNode<E> delete(E value) {
        AvlTreeNode<E> node = super.delete(value);
        if (!node.isRoot()) {
            deleteFix(node);
        }
        return node;
    }

    private void deleteFix(AvlTreeNode<E> node) {
        while (node != null) {
            int balancingFactor = node.getBalancingFactor();
            if (balancingFactor < -1) {
                AvlTreeNode<E> leftChild = node.getLeftChild();
                if (leftChild.getBalancingFactor() > 0) {
                    rotateLeft(leftChild);
                }
                rotateRight(node);
            } else if (balancingFactor > 1) {
                AvlTreeNode<E> rightChild = node.getRightChild();
                if (rightChild.getBalancingFactor() < 0) {
                    rotateRight(rightChild);
                }
                rotateLeft(node);
            }
            node = node.getParent();
        }
    }

}
