package com.mmnaseri.cs.clrs.ch16.s3;

import com.mmnaseri.cs.clrs.ch06.s1.ArrayHeap;
import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import com.mmnaseri.cs.clrs.ch12.s1.PreOrderTreeWalk;
import com.mmnaseri.cs.clrs.ch12.s1.TreeWalkCallback;
import com.mmnaseri.cs.clrs.ch12.sp.Bit;
import com.mmnaseri.cs.clrs.common.Heap;
import com.mmnaseri.cs.clrs.common.impl.MinHeapProperty;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/25/15, 12:14 AM)
 */
@SuppressWarnings("unused")
@Quality(
    value = Stage.UNTESTED,
    explanation = "Works with the example in the book but hasn't been tested really")
public class HuffmanCodeBuilder {

  public List<CharacterCode> build(CharacterComponent... components) {
    final List<CharacterCode> result = new ArrayList<>();
    final List<ComparableBinaryTreeNode<CharacterComponent>> nodes = new ArrayList<>();
    for (CharacterComponent component : components) {
      final ComparableBinaryTreeNode<CharacterComponent> node = new ComparableBinaryTreeNode<>();
      node.setValue(component);
      nodes.add(node);
    }
    final MinHeapProperty<ComparableBinaryTreeNode<CharacterComponent>> heapProperty =
        new MinHeapProperty<>();
    final Heap<ComparableBinaryTreeNode<CharacterComponent>> heap =
        new ArrayHeap<>(heapProperty, nodes);
    while (heap.size() > 1) {
      final ComparableBinaryTreeNode<CharacterComponent> left = heap.pop();
      final ComparableBinaryTreeNode<CharacterComponent> right = heap.pop();
      final ComparableBinaryTreeNode<CharacterComponent> root = new ComparableBinaryTreeNode<>();
      root.setLeftChild(left);
      root.setRightChild(right);
      root.setValue(
          new CharacterComponent(
              null, left.getValue().getFrequency() + right.getValue().getFrequency()));
      heap.add(root);
    }
    if (heap.isEmpty()) {
      return result;
    }
    final ComparableBinaryTreeNode<CharacterComponent> root = heap.pop();
    final PreOrderTreeWalk<CharacterComponent, ComparableBinaryTreeNode<CharacterComponent>> walk =
        new PreOrderTreeWalk<>();
    final List<ComparableBinaryTreeNode<CharacterComponent>> leaves =
        walk.perform(root, new Walker()).getLeaves();
    for (ComparableBinaryTreeNode<CharacterComponent> leaf : leaves) {
      final List<Bit> code = new ArrayList<>();
      final Character character = leaf.getValue().getCharacter();
      while (!leaf.isRoot()) {
        if (leaf == leaf.getParent().getLeftChild()) {
          code.add(0, Bit.ZERO);
        } else {
          code.add(0, Bit.ONE);
        }
        leaf = (ComparableBinaryTreeNode<CharacterComponent>) leaf.getParent();
      }
      result.add(new CharacterCode(character, code.toArray(new Bit[0])));
    }
    return result;
  }

  private static class ComparableBinaryTreeNode<E extends Comparable<E>> extends BinaryTreeNode<E>
      implements Comparable<ComparableBinaryTreeNode<E>> {

    @Override
    public int compareTo(ComparableBinaryTreeNode<E> that) {
      return getValue().compareTo(that.getValue());
    }
  }

  private static class Walker
      implements TreeWalkCallback<
          CharacterComponent, ComparableBinaryTreeNode<CharacterComponent>> {

    private final List<ComparableBinaryTreeNode<CharacterComponent>> leaves = new ArrayList<>();

    @Override
    public void apply(ComparableBinaryTreeNode<CharacterComponent> node) {
      if (node.isLeaf()) {
        leaves.add(node);
      }
    }

    public List<ComparableBinaryTreeNode<CharacterComponent>> getLeaves() {
      return leaves;
    }
  }
}
