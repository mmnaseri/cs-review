package com.mmnaseri.cs.clrs.ch10.s4.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (9/12/18)
 */
public class LevelLinkedTreeNodeTest {

    private LevelLinkedTreeNode<Integer> node;
    private LevelLinkedTreeNode<Integer> firstNode;
    private LevelLinkedTreeNode<Integer> secondNode;
    private LevelLinkedTreeNode<Integer> thirdNode;

    @BeforeMethod
    public void setUp() {
        node = new LevelLinkedTreeNode<>();
        firstNode = createNode(1);
        secondNode = createNode(2);
        thirdNode = createNode(3);
        node = createNode(6,
                firstNode,
                secondNode,
                thirdNode
        );
    }

    @Test
    public void assertChildren() {
        assertThat(node.isRoot(), is(true));
        assertThat(node.getChildren().isEmpty(), is(false));
        assertThat(node.getChildren().size(), is(3));
        assertThat(node.isLeaf(), is(false));
        List<? extends LevelLinkedTreeNode<Integer>> children = node.getChildren();
        assertChildrenValues(children);
    }

    @Test
    public void assertFirstChildDeleteOperation() {
        node.deleteChild(0);
        assertThat(node.getChildren().isEmpty(), is(false));
        assertThat(node.getChildren().size(), is(2));
        assertThat(node.getChildren().get(0), is(notNullValue()));
        assertThat(node.getChildren().get(0).getValue(), is(notNullValue()));
        assertThat(node.getChildren().get(0), is(secondNode));
        assertThat(node.getChildren().get(0).getPreviousSibling(), is(nullValue()));
        assertThat(node.getChildren().get(0).getNextSibling(), is(thirdNode));
        assertThat(node.getChildren().get(1), is(notNullValue()));
        assertThat(node.getChildren().get(1).getValue(), is(notNullValue()));
        assertThat(node.getChildren().get(1), is(thirdNode));
        assertThat(node.getChildren().get(1).getPreviousSibling(), is(secondNode));
        assertThat(node.getChildren().get(1).getNextSibling(), is(nullValue()));
    }

    @Test
    public void assertMiddleChildDeleteOperation() {
        node.deleteChild(1);
        assertThat(node.getChildren().isEmpty(), is(false));
        assertThat(node.getChildren().size(), is(2));
        assertThat(node.getChildren().get(0), is(notNullValue()));
        assertThat(node.getChildren().get(0).getValue(), is(notNullValue()));
        assertThat(node.getChildren().get(0), is(firstNode));
        assertThat(node.getChildren().get(0).getPreviousSibling(), is(nullValue()));
        assertThat(node.getChildren().get(0).getNextSibling(), is(thirdNode));
        assertThat(node.getChildren().get(1), is(notNullValue()));
        assertThat(node.getChildren().get(1).getValue(), is(notNullValue()));
        assertThat(node.getChildren().get(1), is(thirdNode));
        assertThat(node.getChildren().get(1).getPreviousSibling(), is(notNullValue()));
        assertThat(node.getChildren().get(1).getPreviousSibling(), is(firstNode));
        assertThat(node.getChildren().get(1).getNextSibling(), is(nullValue()));
    }

    @Test
    public void assertLastChildDeleteOperation() {
        node.deleteChild(2);
        assertThat(node.getChildren().isEmpty(), is(false));
        assertThat(node.getChildren().size(), is(2));
        List<? extends LevelLinkedTreeNode<Integer>> children = node.getChildren();
        assertChildrenValues(children);
    }

    @Test
    public void assertDeleteAll() {
        node.deleteChild(thirdNode);
        node.deleteChild(firstNode);
        node.deleteChild(secondNode);
        assertThat(node.getChildren().isEmpty(), is(true));
        assertThat(node.isRoot(), is(true));
        assertThat(node.isLeaf(), is(true));
    }

    private void assertChildrenValues(List<? extends LevelLinkedTreeNode<Integer>> children) {
        for (int i = 0; i < children.size(); i++) {
            LevelLinkedTreeNode<Integer> child = children.get(i);
            assertThat(child.getValue(), is(i + 1));
            if (i != 0 && i < children.size() - 1) {
                assertThat(child.getNextSibling().getValue(), is(i + 2));
                assertThat(child.getPreviousSibling().getValue(), is(i));
            } else if (i == 0) {
                assertThat(child.getNextSibling().getValue(), is(i + 2));
                assertThat(child.getPreviousSibling(), is(nullValue()));
            } else {
                assertThat(child.getNextSibling(), is(nullValue()));
                assertThat(child.getPreviousSibling().getValue(), is(i));
            }
            assertThat(child.getParent(), is(node));
            assertThat(child.isLeaf(), is(true));
            assertThat(child.isRoot(), is(false));
        }
    }

    @SafeVarargs
    private final LevelLinkedTreeNode<Integer> createNode(Integer value, LevelLinkedTreeNode<Integer>... children) {
        final LevelLinkedTreeNode<Integer> node = new LevelLinkedTreeNode<>();
        node.setValue(value);
        for (LevelLinkedTreeNode<Integer> child : children) {
            node.addChild(child);
        }
        return node;
    }
}