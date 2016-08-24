package com.mmnaseri.cs.clrs.ch12.s3;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/18/15, 9:45 PM)
 */
public class SimpleBinarySearchTreeTest {

    protected SimpleBinarySearchTree<Integer> tree() {
        return new SimpleBinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
    }

    @Test
    public void testInsertingIntoTheTree() throws Exception {
        final BinarySearchTree<Integer, ?> tree = tree();
        assertThat(tree.getRoot(), is(nullValue()));
        final BinaryTreeNode<Integer> root = tree.insert(5);
        assertThat(root, is(tree.getRoot()));
        assertThat(root.isRoot(), is(true));
        assertThat(root.isLeaf(), is(true));
        final BinaryTreeNode<Integer> one = tree.insert(10);
        assertThat(one.isRoot(), is(false));
        assertThat(one.isLeaf(), is(true));
        assertThat(root.isLeaf(), is(false));
        assertThat(one.getParent(), is(root));
        assertThat(root.getRightChild(), is(one));
        final BinaryTreeNode<Integer> two = tree.insert(7);
        assertThat(two.isRoot(), is(false));
        assertThat(two.isLeaf(), is(true));
        assertThat(one.isLeaf(), is(false));
        assertThat(two.getParent(), is(one));
        assertThat(one.getLeftChild(), is(two));
    }

    @Test
    public void testDeletingFromTheTree() throws Exception {
        final SimpleBinarySearchTree<Integer> tree = tree();
        tree.insert(5, 10, 15, 7, 13, 9, 2, 1, 4, 3);
        final BinaryTreeNode<Integer> found = tree.find(3);
        assertThat(found, is(notNullValue()));
        assertThat(found.getValue(), is(3));
        assertThat(found.getParent(), is(notNullValue()));
        assertThat(found.getParent().getValue(), is(4));
        final BinaryTreeNode<Integer> deleted = tree.delete(3);
        assertThat(deleted, is(notNullValue()));
        assertThat(deleted, is(found));
        assertThat(tree.find(3), is(nullValue()));
        final BinaryTreeNode<Integer> successor = tree.successor(tree.getRoot());
        tree.delete(5);
        assertThat(tree.getRoot(), is(notNullValue()));
        assertThat(tree.getRoot(), is(successor));
    }

}