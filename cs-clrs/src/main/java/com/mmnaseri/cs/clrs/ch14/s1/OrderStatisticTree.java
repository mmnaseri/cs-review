package com.mmnaseri.cs.clrs.ch14.s1;

import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import com.mmnaseri.cs.clrs.ch13.s1.RedBlackTreeNode;
import com.mmnaseri.cs.clrs.ch13.s3.RedBlackTree;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15, 3:34 AM)
 */
public class OrderStatisticTree<E> extends RedBlackTree<E> {

    private final TreeNodeFactory<E, RedBlackTreeNode<E>> factory;

    public OrderStatisticTree(Comparator<E> comparator) {
        super(comparator);
        factory = new TreeNodeFactory<E, RedBlackTreeNode<E>>() {
            @Override
            public RedBlackTreeNode<E> createNode(E value) {
                final OrderStatisticTreeNode<E> node = new OrderStatisticTreeNode<>();
                node.setValue(value);
                return node;
            }
        };
    }

    @Override
    protected TreeNodeFactory<E, RedBlackTreeNode<E>> getFactory() {
        return factory;
    }

    @Override
    public OrderStatisticTreeNode<E> insert(E value) {
        final OrderStatisticTreeNode<E> result = (OrderStatisticTreeNode<E>) super.insert(value);
        OrderStatisticTreeNode<E> node = result;
        while (node != null) {
            node.setSize(node.getSize() + 1);
            node = node.getParent();
        }
        return result;
    }

    @Override
    public OrderStatisticTreeNode<E> getRoot() {
        return (OrderStatisticTreeNode<E>) super.getRoot();
    }

    @Override
    public OrderStatisticTreeNode<E> find(E value) {
        return (OrderStatisticTreeNode<E>) super.find(value);
    }

    @Override
    public OrderStatisticTreeNode<E> delete(E value) {
        final OrderStatisticTreeNode<E> result = (OrderStatisticTreeNode<E>) super.delete(value);
        if (result == null) {
            return null;
        }
        OrderStatisticTreeNode<E> node = result.getParent();
        while (node != null) {
            node.setSize(node.getSize() - 1);
            node = node.getParent();
        }
        return result;
    }

    @Override
    protected void rotateRight(RedBlackTreeNode<E> target) {
        final OrderStatisticTreeNode<E> node = (OrderStatisticTreeNode<E>) target;
        final OrderStatisticTreeNode<E> substitute = node.getLeftChild();
        super.rotateRight(target);
        substitute.setSize(node.getSize());
        final int leftSize = node.getLeftChild() == null ? 0 : node.getLeftChild().getSize();
        final int rightSize = node.getRightChild() == null ? 0 : node.getRightChild().getSize();
        node.setSize(leftSize + rightSize + 1);
    }

    @Override
    protected void rotateLeft(RedBlackTreeNode<E> target) {
        final OrderStatisticTreeNode<E> node = (OrderStatisticTreeNode<E>) target;
        final OrderStatisticTreeNode<E> substitute = node.getRightChild();
        super.rotateLeft(target);
        substitute.setSize(node.getSize());
        final int leftSize = node.getLeftChild() == null ? 0 : node.getLeftChild().getSize();
        final int rightSize = node.getRightChild() == null ? 0 : node.getRightChild().getSize();
        node.setSize(leftSize + rightSize + 1);
    }

    protected OrderStatisticTreeNode<E> select(OrderStatisticTreeNode<E> node, int rank) {
        if (node == null) {
            return null;
        }
        int current = (node.getLeftChild() == null ? 0 : node.getLeftChild().getSize()) + 1;
        if (rank == current) {
            return node;
        } else if (rank < current) {
            return select(node.getLeftChild(), rank);
        } else {
            return select(node.getRightChild(), rank - current);
        }
    }

    public OrderStatisticTreeNode<E> select(int rank) {
        return select(getRoot(), rank + 1);
    }

    public int rank(OrderStatisticTreeNode<E> node) {
        if (node == null) {
            return -1;
        }
        int rank = (node.getLeftChild() == null ? 0 : node.getLeftChild().getSize()) + 1;
        OrderStatisticTreeNode<E> target = node;
        while (!target.isRoot()) {
            if (target == target.getParent().getRightChild()) {
                rank += target.getParent().getSize() + 1;
            }
            target = target.getParent();
        }
        return rank;
    }

    public int rank(E value) {
        final OrderStatisticTreeNode<E> node = find(value);
        return rank(node);
    }

}
