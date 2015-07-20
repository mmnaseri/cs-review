package com.mmnaseri.cs.clrs.ch14.s1;

import com.mmnaseri.cs.clrs.ch13.s1.RedBlackTreeNode;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15, 3:34 AM)
 */
public class OrderStatisticTreeNode<E> extends RedBlackTreeNode<E> {

    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public OrderStatisticTreeNode<E> getParent() {
        return (OrderStatisticTreeNode<E>) super.getParent();
    }

    @Override
    public OrderStatisticTreeNode<E> getLeftChild() {
        return (OrderStatisticTreeNode<E>) super.getLeftChild();
    }

    @Override
    public OrderStatisticTreeNode<E> getRightChild() {
        return (OrderStatisticTreeNode<E>) super.getRightChild();
    }

}
