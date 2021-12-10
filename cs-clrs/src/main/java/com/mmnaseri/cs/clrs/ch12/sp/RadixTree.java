package com.mmnaseri.cs.clrs.ch12.sp;

import com.mmnaseri.cs.clrs.ch12.s1.PreOrderTreeWalk;
import com.mmnaseri.cs.clrs.ch12.s1.TreeWalk;
import com.mmnaseri.cs.clrs.ch12.s1.TreeWalkCallback;
import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import com.mmnaseri.cs.clrs.ch12.s3.BinarySearchTree;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/18/15, 10:10 PM)
 */
@Quality(Stage.TESTED)
public class RadixTree extends BinarySearchTree<Bit, RadixTreeNode> {

  private final RadixTreeNode root = getFactory().createNode(Bit.NEUTRAL);

  public RadixTree() {
    super(
        null,
        value -> {
          final RadixTreeNode node = new RadixTreeNode();
          node.setValue(value);
          return node;
        });
  }

  public RadixTreeNode insert(int number) {
    final Bit[] bits = BitUtils.toBits(number);
    RadixTreeNode current = getRoot();
    for (int i = 0; i < bits.length; i++) {
      final Bit bit = bits[i];
      if (bit.equals(Bit.ZERO)) {
        if (current.getLeftChild() == null) {
          current.setLeftChild(getFactory().createNode(bit));
        }
        current = (RadixTreeNode) current.getLeftChild();
      } else {
        if (current.getRightChild() == null) {
          current.setRightChild(getFactory().createNode(bit));
        }
        current = (RadixTreeNode) current.getRightChild();
      }
      if (i == bits.length - 1) {
        current.setLastBit(true);
      }
    }
    return current;
  }

  public List<RadixTreeNode> insert(int... numbers) {
    final List<RadixTreeNode> nodes = new ArrayList<>();
    for (int number : numbers) {
      nodes.add(insert(number));
    }
    return nodes;
  }

  public List<Integer> list() {
    final TreeWalk<Bit, RadixTreeNode> walk = new PreOrderTreeWalk<>();
    final List<RadixTreeNode> leaves =
        walk.perform(getRoot(), new LeafCollectingTreeWalkCallback()).getLeaves();
    final List<List<Bit>> numbers = new ArrayList<>();
    for (RadixTreeNode leaf : leaves) {
      final ArrayList<Bit> number = new ArrayList<>();
      numbers.add(number);
      while (!leaf.isRoot()) {
        number.add(0, leaf.getValue());
        leaf = (RadixTreeNode) leaf.getParent();
      }
    }
    final ArrayList<Integer> integers = new ArrayList<>();
    for (List<Bit> number : numbers) {
      integers.add(BitUtils.fromBits(number.toArray(new Bit[0])));
    }
    return integers;
  }

  public RadixTreeNode find(int number) {
    final Bit[] bits = BitUtils.toBits(number);
    RadixTreeNode current = getRoot();
    for (Bit bit : bits) {
      if (bit.equals(Bit.ZERO)) {
        current = (RadixTreeNode) current.getLeftChild();
      } else {
        current = (RadixTreeNode) current.getRightChild();
      }
      if (current == null) {
        return null;
      }
    }
    if (current.isLastBit()) {
      return current;
    }
    return null;
  }

  public void delete(int number) {
    RadixTreeNode node = find(number);
    if (node == null) {
      return;
    }
    node.setLastBit(false);
    if (node.isLeaf()) {
      while (true) {
        if (node.isLastBit()) {
          return;
        }
        if (!node.isLeaf()) {
          return;
        }
        if (node.isRoot()) {
          return;
        }
        final RadixTreeNode parent = (RadixTreeNode) node.getParent();
        if (parent.getLeftChild() == node) {
          parent.setLeftChild(null);
        } else {
          parent.setRightChild(null);
        }
        node = parent;
      }
    }
  }

  @Override
  public void setRoot(RadixTreeNode root) {
    throw new UnsupportedOperationException();
  }

  @Override
  public RadixTreeNode getRoot() {
    return root;
  }

  @Override
  public RadixTreeNode insert(Bit value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public RadixTreeNode delete(Bit value) {
    throw new UnsupportedOperationException();
  }

  private static class LeafCollectingTreeWalkCallback
      implements TreeWalkCallback<Bit, RadixTreeNode> {

    private final List<RadixTreeNode> leaves = new ArrayList<>();

    @Override
    public void apply(RadixTreeNode node) {
      if (!node.isRoot() && (node.isLeaf() || node.isLastBit())) {
        leaves.add(node);
      }
    }

    public List<RadixTreeNode> getLeaves() {
      return leaves;
    }
  }
}
