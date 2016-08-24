package com.mmnaseri.cs.clrs.ch13.s3;

import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import com.mmnaseri.cs.clrs.ch13.s1.NodeColor;
import com.mmnaseri.cs.clrs.ch13.s1.RedBlackTreeNode;
import com.mmnaseri.cs.clrs.ch13.s2.RotatingBinarySearchTree;
import com.mmnaseri.cs.qa.annotation.Monitored;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/19/15, 11:55 PM)
 */
@Quality(value = Stage.TESTED)
@Monitored(MonitoredRedBlackTree.class)
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
        while (!node.isRoot() && node.getParent().getColor().equals(NodeColor.RED)) {
            if (node.getParent() == node.getParent().getParent().getLeftChild()) {
                final RedBlackTreeNode<E> uncle = node.getParent().getParent().getRightChild();
                if (uncle == null || NodeColor.BLACK.equals(uncle.getColor())) {
                    if (node == node.getParent().getRightChild()) {
                        node = node.getParent();
                        rotateLeft(node);
                    }
                    node.getParent().setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    rotateRight(node.getParent().getParent());
                } else {
                    node.getParent().setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    node = node.getParent().getParent();
                }
            } else {
                final RedBlackTreeNode<E> uncle = node.getParent().getParent().getLeftChild();
                if (uncle == null || NodeColor.BLACK.equals(uncle.getColor())) {
                    if (node == node.getParent().getLeftChild()) {
                        node = node.getParent();
                        rotateRight(node);
                    }
                    node.getParent().setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    rotateLeft(node.getParent().getParent());
                } else {
                    node.getParent().setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    node = node.getParent().getParent();
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
        if (target != null && NodeColor.BLACK.equals(originalColor)) {
            deleteFix(target);
        }
        return node;
    }

    protected void deleteFix(RedBlackTreeNode<E> node) {
        while (!node.isRoot() && NodeColor.BLACK.equals(node.getColor())) {
            if (node == node.getParent().getLeftChild()) {
                RedBlackTreeNode<E> sibling = node.getParent().getRightChild();
                if (NodeColor.RED.equals(sibling.getColor())) {
                    sibling.setColor(NodeColor.BLACK);
                    node.getParent().setColor(NodeColor.RED);
                    rotateLeft(node.getParent());
                    sibling = node.getParent().getRightChild();
                }
                if (NodeColor.BLACK.equals(sibling.getLeftChild().getColor()) && NodeColor.BLACK.equals(sibling.getRightChild().getColor())) {
                    sibling.setColor(NodeColor.RED);
                    node = node.getParent();
                } else {
                    if (NodeColor.BLACK.equals(sibling.getRightChild().getColor())) {
                        sibling.getLeftChild().setColor(NodeColor.BLACK);
                        sibling.setColor(NodeColor.RED);
                        rotateRight(sibling);
                        sibling = node.getParent().getRightChild();
                    }
                    sibling.setColor(node.getParent().getColor());
                    sibling.getParent().setColor(NodeColor.BLACK);
                    sibling.getRightChild().setColor(NodeColor.BLACK);
                    rotateLeft(node.getParent());
                    node = getRoot();
                }
            } else {
                RedBlackTreeNode<E> sibling = node.getParent().getLeftChild();
                if (NodeColor.RED.equals(sibling.getColor())) {
                    sibling.setColor(NodeColor.BLACK);
                    node.getParent().setColor(NodeColor.RED);
                    rotateRight(node.getParent());
                    sibling = node.getParent().getLeftChild();
                }
                if (NodeColor.BLACK.equals(sibling.getLeftChild().getColor()) && NodeColor.BLACK.equals(sibling.getRightChild().getColor())) {
                    sibling.setColor(NodeColor.RED);
                    node = node.getParent();
                } else {
                    if (NodeColor.BLACK.equals(sibling.getLeftChild().getColor())) {
                        sibling.getRightChild().setColor(NodeColor.BLACK);
                        sibling.setColor(NodeColor.RED);
                        rotateLeft(sibling);
                        sibling = node.getParent().getLeftChild();
                    }
                    sibling.setColor(node.getParent().getColor());
                    sibling.getParent().setColor(NodeColor.BLACK);
                    sibling.getLeftChild().setColor(NodeColor.BLACK);
                    rotateRight(node.getParent());
                    node = getRoot();
                }
            }
        }
        node.setColor(NodeColor.BLACK);
    }

}
