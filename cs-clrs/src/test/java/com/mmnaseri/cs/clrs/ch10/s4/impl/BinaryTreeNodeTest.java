package com.mmnaseri.cs.clrs.ch10.s4.impl;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsEmptyCollection.empty;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/13/15, 12:43 AM)
 */
public class BinaryTreeNodeTest {

    private BinaryTreeNode<Integer> createNode(Integer value, BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
        final BinaryTreeNode<Integer> node = new BinaryTreeNode<>();
        node.setValue(value);
        node.setLeftChild(left);
        node.setRightChild(right);
        return node;
    }

    @Test
    public void testInitialValue() throws Exception {
        final int value = 10;
        final BinaryTreeNode<Integer> node = createNode(value, null, null);
        assertThat(node.getValue(), is(notNullValue()));
        assertThat(node.getValue(), is(value));
        assertThat(node.isLeaf(), is(true));
        assertThat(node.isRoot(), is(true));
        assertThat(node.getChildren(), is(empty()));
        assertThat(node.getLeftChild(), is(nullValue()));
        assertThat(node.getRightChild(), is(nullValue()));
    }

    @Test
    public void testLeftChild() throws Exception {
        final BinaryTreeNode<Integer> node = createNode(1, createNode(2, null, null), null);
        assertThat(node.getValue(), is(notNullValue()));
        assertThat(node.isLeaf(), is(false));
        assertThat(node.isRoot(), is(true));
        assertThat(node.getChildren(), hasSize(1));
        assertThat(node.getRightChild(), is(nullValue()));
        assertThat(node.getLeftChild(), is(notNullValue()));
        assertThat(node.getLeftChild().getValue(), is(2));
        assertThat(node.getLeftChild().isLeaf(), is(true));
        assertThat(node.getLeftChild().isRoot(), is(false));
        assertThat(node.getLeftChild().getParent(), is(node));
        assertThat(node.getChildren().get(0), is(node.getLeftChild()));
    }

    @Test
    public void testRightChild() throws Exception {
        final BinaryTreeNode<Integer> node = createNode(1, null, createNode(2, null, null));
        assertThat(node.getValue(), is(notNullValue()));
        assertThat(node.isLeaf(), is(false));
        assertThat(node.isRoot(), is(true));
        assertThat(node.getChildren(), hasSize(1));
        assertThat(node.getRightChild(), is(notNullValue()));
        assertThat(node.getRightChild().getValue(), is(2));
        assertThat(node.getRightChild().isLeaf(), is(true));
        assertThat(node.getRightChild().isRoot(), is(false));
        assertThat(node.getRightChild().getParent(), is(node));
        assertThat(node.getChildren().get(0), is(node.getRightChild()));
        assertThat(node.getLeftChild(), is(nullValue()));
    }

}