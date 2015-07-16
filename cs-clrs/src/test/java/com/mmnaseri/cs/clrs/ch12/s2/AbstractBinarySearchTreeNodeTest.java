package com.mmnaseri.cs.clrs.ch12.s2;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/15/15, 3:16 PM)
 */
public class AbstractBinarySearchTreeNodeTest {

    protected SearchTreeNode<Integer> create(Integer value) {
        return create(value, null, null);
    }

    protected SearchTreeNode<Integer> create(Integer value, SearchTreeNode<Integer> left, SearchTreeNode<Integer> right) {
        final NonInsertingBinarySearchTreeNode<Integer> node = new NonInsertingBinarySearchTreeNode<>();
        node.setValue(value);
        node.setLeftChild(left);
        node.setRightChild(right);
        return node;
    }

    @Test
    public void testFindingByValue() throws Exception {
        final SearchTreeNode<Integer> four = create(18);
        final SearchTreeNode<Integer> three = create(20, four, null);
        final SearchTreeNode<Integer> two = create(13);
        final SearchTreeNode<Integer> one = create(4);
        final SearchTreeNode<Integer> root = create(10, create(7, one, create(8)), create(16, two, three));
        assertThat(root.find(10), is(notNullValue()));
        assertThat(root.find(10), is(root));
        assertThat(root.find(18), is(four));
        assertThat(four.find(18), is(four));
        assertThat(three.find(18), is(four));
        assertThat(root.find(-1), is(nullValue()));
        assertThat(two.find(18), is(nullValue()));
        assertThat(root.find(13), is(two));
        assertThat(root.find(4), is(one));
    }

    @Test
    public void testFindingTheMinimumOnLeaf() throws Exception {
        assertThat(create(6).minimum(), is(notNullValue()));
        assertThat(create(6).minimum().getValue(), is(6));
    }

    @Test
    public void testFindingTheMinimumWithLeftBranch() throws Exception {
        assertThat(create(7, create(6, create(5), null), null).minimum(), is(notNullValue()));
        assertThat(create(7, create(6, create(5), null), null).minimum().getValue(), is(5));
    }

    @Test
    public void testFindingTheMinimumWithRightBranch() throws Exception {
        assertThat(create(7, null, create(8, null, create(9))).minimum(), is(notNullValue()));
        assertThat(create(7, null, create(8, null, create(9))).minimum().getValue(), is(7));
    }

    @Test
    public void testFindingTheMaximumOnLeaf() throws Exception {
        assertThat(create(6).maximum(), is(notNullValue()));
        assertThat(create(6).maximum().getValue(), is(6));
    }

    @Test
    public void testFindingTheMaximumWithLeftBranch() throws Exception {
        assertThat(create(7, create(6, create(5), null), null).maximum(), is(notNullValue()));
        assertThat(create(7, create(6, create(5), null), null).maximum().getValue(), is(7));
    }

    @Test
    public void testFindingTheMaximumWithRightBranch() throws Exception {
        assertThat(create(7, null, create(8, null, create(9))).maximum(), is(notNullValue()));
        assertThat(create(7, null, create(8, null, create(9))).maximum().getValue(), is(9));
    }

    @Test
    public void testFindingThePredecessorOnFullTree() throws Exception {
        final SearchTreeNode<Integer> one = create(1);
        final SearchTreeNode<Integer> two = create(5);
        final SearchTreeNode<Integer> tree = create(6, create(3, one, two), create(9, create(7), create(11, create(10), create(12))));
        assertThat(tree.predecessor(), is(notNullValue()));
        assertThat(tree.predecessor().getValue(), is(5));
        assertThat(one.predecessor(), is(nullValue()));
        assertThat(two.predecessor(), is(notNullValue()));
        assertThat(two.predecessor().getValue(), is(3));
    }

    @Test
    public void testFindingTheSuccessorOnFullTree() throws Exception {
        final SearchTreeNode<Integer> one = create(12);
        final SearchTreeNode<Integer> two = create(9, create(7), create(11, create(10), one));
        final SearchTreeNode<Integer> tree = create(6, create(3, create(1), create(5)), two);
        assertThat(tree.successor(), is(notNullValue()));
        assertThat(tree.successor().getValue(), is(7));
        assertThat(one.successor(), is(nullValue()));
        assertThat(two.successor(), is(notNullValue()));
        assertThat(two.successor().getValue(), is(10));
    }

    private static class NonInsertingBinarySearchTreeNode<E extends Comparable<E>> extends AbstractBinarySearchTreeNode<E> {

        @Override
        public SearchTreeNode<E> insert(E value) {
            return null;
        }

        @Override
        public void delete(E value) {
        }

        @Override
        public void delete() {
        }

    }

}