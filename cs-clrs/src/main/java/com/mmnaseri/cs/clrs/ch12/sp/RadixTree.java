package com.mmnaseri.cs.clrs.ch12.sp;

import com.mmnaseri.cs.clrs.ch12.s1.PreOrderTreeWalk;
import com.mmnaseri.cs.clrs.ch12.s1.TreeWalk;
import com.mmnaseri.cs.clrs.ch12.s1.TreeWalkCallback;
import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import com.mmnaseri.cs.clrs.ch12.s3.BinarySearchTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/18/15, 10:10 PM)
 */
public class RadixTree extends BinarySearchTree<Bit, RadixTreeNode> {

    private final RadixTreeNode root = getFactory().createNode(Bit.NEUTRAL);

    public RadixTree() {
        super(new Comparator<Bit>() {
            @Override
            public int compare(Bit first, Bit second) {
                return Integer.compare(first.getValue(), second.getValue());
            }
        }, new TreeNodeFactory<Bit, RadixTreeNode>() {
            @Override
            public RadixTreeNode createNode(Bit value) {
                final RadixTreeNode node = new RadixTreeNode();
                node.setValue(value);
                return node;
            }
        });
    }

    public RadixTreeNode insert(int number) {
        final Bit[] bits = toBits(number);
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

    public List<Integer> lookup() {
        final List<RadixTreeNode> leaves = new ArrayList<>();
        final TreeWalk<Bit, RadixTreeNode> walk = new PreOrderTreeWalk<>();
        walk.perform(getRoot(), new TreeWalkCallback<Bit, RadixTreeNode>() {
            @Override
            public void apply(RadixTreeNode node) {
                if (node.isLeaf() || node.isLastBit()) {
                    leaves.add(node);
                }
            }
        });
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
            integers.add(fromBits(number.toArray(new Bit[number.size()])));
        }
        return integers;
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

    private static Bit[] toBits(int number) {
        final int digits = (int) Math.ceil(Math.log(number) / Math.log(2));
        final Bit[] bits = new Bit[digits];
        for (int i = 0; i < digits; i ++) {
            bits[bits.length - i - 1] = number % 2 == 0 ? Bit.ZERO : Bit.ONE;
            number = number >> 1;
        }
        return bits;
    }

    private static int fromBits(Bit[] bits) {
        int result = 0;
        for (Bit bit : bits) {
            result = result << 1;
            result += (bit.equals(Bit.ONE) ? 1 : 0);
        }
        return result;
    }

}
