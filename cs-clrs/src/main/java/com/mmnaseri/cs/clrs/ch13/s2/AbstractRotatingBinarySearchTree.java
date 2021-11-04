package com.mmnaseri.cs.clrs.ch13.s2;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import com.mmnaseri.cs.clrs.ch12.s3.BinarySearchTree;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/19/15, 10:57 PM)
 */
@Quality(Stage.TESTED)
public abstract class AbstractRotatingBinarySearchTree<E, N extends BinaryTreeNode<E>>
    extends BinarySearchTree<E, N> {

  public AbstractRotatingBinarySearchTree(Comparator<E> comparator, TreeNodeFactory<E, N> factory) {
    super(comparator, factory);
  }

  protected void postInsert(E value, N node, N parent) {
    if (parent == null) {
      setRoot(node);
    } else if (lessThan(value, parent.getValue())) {
      parent.setLeftChild(node);
    } else {
      parent.setRightChild(node);
    }
  }

  protected void rotateRight(N target) {
    //noinspection unchecked
    final N substitute = (N) target.getLeftChild();
    if (substitute == null) {
      throw new IllegalStateException();
    }
    initiateRotation(target, substitute);
    target.setLeftChild(substitute.getRightChild());
    substitute.setParent(target.getParent());
    substitute.setRightChild(target);
    target.setParent(substitute);
  }

  protected void rotateLeft(N target) {
    //noinspection unchecked
    final N substitute = (N) target.getRightChild();
    if (substitute == null) {
      throw new IllegalStateException();
    }
    initiateRotation(target, substitute);
    target.setRightChild(substitute.getLeftChild());
    substitute.setParent(target.getParent());
    substitute.setLeftChild(target);
    target.setParent(substitute);
  }

  private void initiateRotation(N target, N substitute) {
    if (target.getParent() != null) {
      if (target.getParent().getRightChild() == target) {
        target.getParent().setRightChild(substitute);
      } else {
        target.getParent().setLeftChild(substitute);
      }
    } else {
      setRoot(substitute);
    }
  }
}
