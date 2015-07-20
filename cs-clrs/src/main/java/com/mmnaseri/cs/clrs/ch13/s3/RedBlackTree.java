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
                if (target == null) {
                    node = node.getParent().getParent();
                    continue;
                }
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
                if (target == null) {
                    node = node.getParent().getParent();
                    continue;
                }
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

    @Override
    public RedBlackTreeNode<E> delete(E value) {
        final RedBlackTreeNode<E> node = find(value);
        if (node == null) {
            return null;
        }
        NodeColor originalColor = node.getColor();
        RedBlackTreeNode<E> current;
        RedBlackTreeNode<E> target;
        if (node.getLeftChild() == null) {
            target = node.getRightChild();
            transplant(node, node.getRightChild());
        } else if (node.getRightChild() == null) {
            target = node.getLeftChild();
            transplant(node, node.getLeftChild());
        } else {
            current = minimum(node.getRightChild());
            originalColor = current.getColor();
            target = current.getRightChild();
            if (node == current.getParent()) {
                target.setParent(current);
            } else {
                transplant(current, current.getRightChild());
                current.setRightChild(node.getRightChild());
            }
            transplant(node, current);
            current.setLeftChild(node.getLeftChild());
            current.setColor(node.getColor());
        }
        if (NodeColor.BLACK.equals(originalColor)) {
            deleteFix(target);
        }
        return node;
    }

    protected void deleteFix(RedBlackTreeNode<E> node) {
        while (!node.isRoot() && NodeColor.BLACK.equals(node.getColor())) {
            if (node == node.getParent().getLeftChild()) {
                RedBlackTreeNode<E> target = node.getParent().getRightChild();
                if (target.getColor().equals(NodeColor.RED)) {
                    target.setColor(NodeColor.BLACK);
                    target.getParent().setColor(NodeColor.RED);
                    rotateLeft(node.getParent());
                    target = node.getParent().getRightChild();
                }
                if (target.getLeftChild() != null && target.getRightChild() != null) {
                    if (target.getLeftChild().getColor().equals(NodeColor.BLACK) && target.getRightChild().getColor().equals(NodeColor.BLACK)) {
                        target.setColor(NodeColor.RED);
                        node = node.getParent();
                    } else if (target.getRightChild().getColor().equals(NodeColor.BLACK)) {
                        target.getLeftChild().setColor(NodeColor.BLACK);
                        target.setColor(NodeColor.RED);
                        rotateRight(target);
                        target = node.getParent().getRightChild();
                    }
                }
                target.setColor(node.getParent().getColor());
                node.getParent().setColor(NodeColor.BLACK);
                target.getRightChild().setColor(NodeColor.BLACK);
                rotateLeft(node.getParent());
                node = getRoot();
            } else {
                RedBlackTreeNode<E> target = node.getParent().getLeftChild();
                if (target.getColor().equals(NodeColor.RED)) {
                    target.setColor(NodeColor.BLACK);
                    target.getParent().setColor(NodeColor.RED);
                    rotateRight(node.getParent());
                    target = node.getParent().getLeftChild();
                }
                if (target.getLeftChild() != null && target.getRightChild() != null) {
                    if (target.getLeftChild().getColor().equals(NodeColor.BLACK) && target.getRightChild().getColor().equals(NodeColor.BLACK)) {
                        target.setColor(NodeColor.RED);
                        node = node.getParent();
                    } else if (target.getLeftChild().getColor().equals(NodeColor.BLACK)) {
                        target.getRightChild().setColor(NodeColor.BLACK);
                        target.setColor(NodeColor.RED);
                        rotateLeft(target);
                        target = node.getParent().getLeftChild();
                    }
                }
                target.setColor(node.getParent().getColor());
                node.getParent().setColor(NodeColor.BLACK);
                target.getLeftChild().setColor(NodeColor.BLACK);
                rotateRight(node.getParent());
                node = getRoot();
            }
        }
        node.setColor(NodeColor.BLACK);
    }

}
