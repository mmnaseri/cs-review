package com.mmnaseri.cs.clrs.ch13.s1;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/19/15, 11:54 PM)
 */
public class RedBlackTreeNode<E> extends BinaryTreeNode<E> {

  private NodeColor color;

  public NodeColor getColor() {
    return color;
  }

  public void setColor(NodeColor color) {
    this.color = color;
  }

  @Override
  public RedBlackTreeNode<E> getParent() {
    return (RedBlackTreeNode<E>) super.getParent();
  }

  @Override
  public RedBlackTreeNode<E> getLeftChild() {
    return (RedBlackTreeNode<E>) super.getLeftChild();
  }

  @Override
  public RedBlackTreeNode<E> getRightChild() {
    return (RedBlackTreeNode<E>) super.getRightChild();
  }

  @Override
  public String toString() {
    return (NodeColor.RED.equals(color) ? "R" : "B") + super.toString();
  }
}
