package com.mmnaseri.cs.clrs.ch12.s2;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/18/15, 7:45 PM)
 */
@SuppressWarnings("unchecked")
@Quality(Stage.TESTED)
public abstract class AbstractBinarySearchTree<E, N extends BinaryTreeNode<E>> extends AbstractSearchTree<E, N> {

    protected AbstractBinarySearchTree(Comparator<E> comparator, TreeNodeFactory<E, N> factory) {
        super(comparator, factory);
    }

    @Override
    public N find(E value) {
        return find(getRoot(), value);
    }

    @Override
    public N find(N root, E value) {
        if (root.getValue() == null && value == null || root.getValue() != null && root.getValue().equals(value)) {
            return root;
        }
        if (root.getValue() == null) {
            return null;
        }
        if (lessThan(value, root.getValue())) {
            return (root.getLeftChild() == null ? null : find((N) root.getLeftChild(), value));
        } else {
            return (root.getRightChild() == null ? null : find((N) root.getRightChild(), value));
        }
    }

    @Override
    public N minimum() {
        return minimum(getRoot());
    }

    @Override
    public N minimum(N root) {
        return root.getLeftChild() == null ? root : minimum((N) root.getLeftChild());
    }

    @Override
    public N maximum() {
        return maximum(getRoot());
    }

    @Override
    public N maximum(N root) {
        return root.getRightChild() == null ? root : maximum((N) root.getRightChild());
    }

    @Override
    public N predecessor(N node) {
        if (node.getLeftChild() != null) {
            return maximum((N) node.getLeftChild());
        }
        BinaryTreeNode<E> current = node;
        BinaryTreeNode<E> parent = current.getParent();
        while (parent != null && current == parent.getLeftChild()) {
            current = parent;
            parent = parent.getParent();
        }
        return (N) parent;
    }

    @Override
    public N successor(N node) {
        if (node.getRightChild() != null) {
            return minimum((N) node.getRightChild());
        }
        BinaryTreeNode<E> current = node;
        BinaryTreeNode<E> parent = current.getParent();
        while (parent != null && current == parent.getRightChild()) {
            current = parent;
            parent = parent.getParent();
        }
        return (N) parent;
    }

    @Override
    public N insert(E value) {
        if (getRoot() == null) {
            final N root = getFactory().createNode(value);
            setRoot(root);
            return root;
        }
        return insert(getRoot(), value);
    }

    protected N delete(N root, E value) {
        final N node = find(root, value);
        if (node == null) {
            return null;
        }
        delete(node);
        return node;
    }

    @Override
    public N delete(E value) {
        return delete(getRoot(), value);
    }

    abstract protected N insert(N root, E value);

    abstract protected void delete(N node);

}
