package com.mmnaseri.cs.clrs.ch10.s4.impl;

import com.mmnaseri.cs.clrs.ch10.s4.TreeNode;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/13/15, 12:27 AM)
 */
public class SimpleTreeNodeTest {

    @SafeVarargs
    private final TreeNode<Integer> createNode(Integer value, TreeNode<Integer>... children) {
        final SimpleTreeNode<Integer> node = new SimpleTreeNode<>();
        node.setValue(value);
        for (TreeNode<Integer> child : children) {
            node.addChild(child);
        }
        return node;
    }

    @Test
    public void testInitialValue() throws Exception {
        final int value = 10;
        final TreeNode<Integer> node = createNode(value);
        assertThat(node, is(notNullValue()));
        assertThat(node.getValue(), is(notNullValue()));
        assertThat(node.getValue(), is(value));
        assertThat(node.isRoot(), is(true));
        assertThat(node.isLeaf(), is(true));
    }

    @Test
    public void testChildren() throws Exception {
        final int first = 3;
        final int second = 4;
        final int third = 5;
        final TreeNode<Integer> node = createNode(10,
                createNode(first),
                createNode(second),
                createNode(third)
        );
        assertThat(node.getChildren().isEmpty(), is(false));
        assertThat(node.getChildren().size(), is(3));
        assertThat(node.isLeaf(), is(false));
        assertThat(node.getChildren().get(0), is(notNullValue()));
        assertThat(node.getChildren().get(0).getValue(), is(notNullValue()));
        assertThat(node.getChildren().get(0).getValue(), is(first));
        assertThat(node.getChildren().get(0).isLeaf(), is(true));
        assertThat(node.getChildren().get(0).isRoot(), is(false));
        assertThat(node.getChildren().get(0).getParent(), is(node));
        assertThat(node.getChildren().get(1), is(notNullValue()));
        assertThat(node.getChildren().get(1).getValue(), is(notNullValue()));
        assertThat(node.getChildren().get(1).getValue(), is(second));
        assertThat(node.getChildren().get(1).isLeaf(), is(true));
        assertThat(node.getChildren().get(1).isRoot(), is(false));
        assertThat(node.getChildren().get(1).getParent(), is(node));
        assertThat(node.getChildren().get(2), is(notNullValue()));
        assertThat(node.getChildren().get(2).getValue(), is(notNullValue()));
        assertThat(node.getChildren().get(2).getValue(), is(third));
        assertThat(node.getChildren().get(2).isLeaf(), is(true));
        assertThat(node.getChildren().get(2).isRoot(), is(false));
        assertThat(node.getChildren().get(2).getParent(), is(node));
    }

    @Test
    public void testDeletingAChild() throws Exception {
        final TreeNode<Integer> node = createNode(1,
                createNode(2)
        );
        assertThat(node.isRoot(), is(true));
        assertThat(node.isLeaf(), is(false));
        node.deleteChild(0);
        assertThat(node.isRoot(), is(true));
        assertThat(node.isLeaf(), is(true));
    }

}