package com.mmnaseri.cs.clrs.ch13.s2;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsNot.not;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/19/15, 11:06 PM)
 */
public class AbstractRotatingBinarySearchTreeTest {

    protected AbstractRotatingBinarySearchTree<Integer, BinaryTreeNode<Integer>> tree() {
        return new AbstractRotatingBinarySearchTree<Integer, BinaryTreeNode<Integer>>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }, new TreeNodeFactory<Integer, BinaryTreeNode<Integer>>() {
            @Override
            public BinaryTreeNode<Integer> createNode(Integer value) {
                final BinaryTreeNode<Integer> node = new BinaryTreeNode<>();
                node.setValue(value);
                return node;
            }
        }){};
    }

    @Test
    public void testRotateLeft() throws Exception {
        final AbstractRotatingBinarySearchTree<Integer, BinaryTreeNode<Integer>> tree = tree();
        tree.insert(5, 3, 7, 1, 4, 6, 9, 8, 10);
        final BinaryTreeNode<Integer> node = tree.find(7);
        tree.rotateLeft(node);
        assertThat(node.getParent(), is(notNullValue()));
        assertThat(node.getParent().getValue(), is(9));
        assertThat(node.getLeftChild(), is(notNullValue()));
        assertThat(node.getLeftChild().getValue(), is(6));
        assertThat(node.getRightChild(), is(notNullValue()));
        assertThat(node.getRightChild().getValue(), is(8));
    }

    @Test
    public void testRotateRight() throws Exception {
        final AbstractRotatingBinarySearchTree<Integer, BinaryTreeNode<Integer>> tree = tree();
        tree.insert(5, 3, 9, 1, 4, 7, 10, 6, 8);
        final BinaryTreeNode<Integer> node = tree.find(9);
        tree.rotateRight(node);
        assertThat(node.getParent(), is(notNullValue()));
        assertThat(node.getParent().getValue(), is(7));
        assertThat(node.getLeftChild(), is(notNullValue()));
        assertThat(node.getLeftChild().getValue(), is(8));
        assertThat(node.getRightChild(), is(notNullValue()));
        assertThat(node.getRightChild().getValue(), is(10));
    }

    @Test
    public void testSymmetry() throws Exception {
        final AbstractRotatingBinarySearchTree<Integer, BinaryTreeNode<Integer>> tree = tree();
        tree.insert(5, 3, 7, 1, 4, 6, 8);
        final String original = tree.toString();
        tree.rotateLeft(tree.getRoot());
        assertThat(tree.toString(), is(not(original)));
        tree.rotateRight(tree.getRoot());
        assertThat(tree.toString(), is(original));
    }
}