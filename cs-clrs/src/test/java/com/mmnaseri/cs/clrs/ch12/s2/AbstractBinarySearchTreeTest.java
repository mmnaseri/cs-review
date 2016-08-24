package com.mmnaseri.cs.clrs.ch12.s2;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/18/15, 7:54 PM)
 */
public class AbstractBinarySearchTreeTest {

    protected BinaryTreeNode<Integer> create(Integer value) {
        return create(value, null, null);
    }

    protected BinaryTreeNode<Integer> create(Integer value, BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
        final BinaryTreeNode<Integer> node = new BinaryTreeNode<>();
        node.setValue(value);
        node.setLeftChild(left);
        node.setRightChild(right);
        return node;
    }

    protected AbstractBinarySearchTree<Integer, BinaryTreeNode<Integer>> tree(Integer root) {
        return tree(create(root));
    }
    
    protected AbstractBinarySearchTree<Integer, BinaryTreeNode<Integer>> tree(BinaryTreeNode<Integer> root) {
        return new UnmodifiableBinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }, root);
    }

    @Test
    public void testFindingByValue() throws Exception {
        final BinaryTreeNode<Integer> four = create(18);
        final BinaryTreeNode<Integer> three = create(20, four, null);
        final BinaryTreeNode<Integer> two = create(13);
        final BinaryTreeNode<Integer> one = create(4);
        final BinaryTreeNode<Integer> root = create(10, create(7, one, create(8)), create(16, two, three));
        final AbstractBinarySearchTree<Integer, BinaryTreeNode<Integer>> tree = tree(root);
        assertThat(tree.find(10), is(notNullValue()));
        assertThat(tree.find(10), is(root));
        assertThat(tree.find(18), is(four));
        assertThat(tree.find(four, 18), is(four));
        assertThat(tree.find(three, 18), is(four));
        assertThat(tree.find(-1), is(nullValue()));
        assertThat(tree.find(two, 18), is(nullValue()));
        assertThat(tree.find(13), is(two));
        assertThat(tree.find(4), is(one));
    }

    @Test
    public void testFindingTheMinimumOnLeaf() throws Exception {
        assertThat(tree(6).minimum(), is(notNullValue()));
        assertThat(tree(6).minimum().getValue(), is(6));
    }

    @Test
    public void testFindingTheMinimumWithLeftBranch() throws Exception {
        assertThat(tree(create(7, create(6, create(5), null), null)).minimum(), is(notNullValue()));
        assertThat(tree(create(7, create(6, create(5), null), null)).minimum().getValue(), is(5));
    }

    @Test
    public void testFindingTheMinimumWithRightBranch() throws Exception {
        assertThat(tree(create(7, null, create(8, null, create(9)))).minimum(), is(notNullValue()));
        assertThat(tree(create(7, null, create(8, null, create(9)))).minimum().getValue(), is(7));
    }

    @Test
    public void testFindingTheMaximumOnLeaf() throws Exception {
        assertThat(tree(create(6)).maximum(), is(notNullValue()));
        assertThat(tree(create(6)).maximum().getValue(), is(6));
    }

    @Test
    public void testFindingTheMaximumWithLeftBranch() throws Exception {
        assertThat(tree(create(7, create(6, create(5), null), null)).maximum(), is(notNullValue()));
        assertThat(tree(create(7, create(6, create(5), null), null)).maximum().getValue(), is(7));
    }

    @Test
    public void testFindingTheMaximumWithRightBranch() throws Exception {
        assertThat(tree(create(7, null, create(8, null, create(9)))).maximum(), is(notNullValue()));
        assertThat(tree(create(7, null, create(8, null, create(9)))).maximum().getValue(), is(9));
    }

    @Test
    public void testFindingThePredecessorOnFullTree() throws Exception {
        final BinaryTreeNode<Integer> one = create(1);
        final BinaryTreeNode<Integer> two = create(5);
        final AbstractBinarySearchTree<Integer, BinaryTreeNode<Integer>> tree = tree(create(6, create(3, one, two), create(9, create(7), create(11, create(10), create(12)))));
        assertThat(tree.predecessor(tree.getRoot()), is(notNullValue()));
        assertThat(tree.predecessor(tree.getRoot()).getValue(), is(5));
        assertThat(tree.predecessor(one), is(nullValue()));
        assertThat(tree.predecessor(two), is(notNullValue()));
        assertThat(tree.predecessor(two).getValue(), is(3));
    }

    @Test
    public void testFindingTheSuccessorOnFullTree() throws Exception {
        final BinaryTreeNode<Integer> one = create(12);
        final BinaryTreeNode<Integer> two = create(9, create(7), create(11, create(10), one));
        final AbstractBinarySearchTree<Integer, BinaryTreeNode<Integer>> tree = tree(create(6, create(3, create(1), create(5)), two));
        assertThat(tree.successor(tree.getRoot()), is(notNullValue()));
        assertThat(tree.successor(tree.getRoot()).getValue(), is(7));
        assertThat(tree.successor(one), is(nullValue()));
        assertThat(tree.successor(two), is(notNullValue()));
        assertThat(tree.successor(two).getValue(), is(10));
    }


    private static class UnmodifiableBinarySearchTree<E, N extends BinaryTreeNode<E>> extends AbstractBinarySearchTree<E, N> {

        protected UnmodifiableBinarySearchTree(Comparator<E> comparator, N root) {
            super(comparator, null);
            super.setRoot(root);
        }

        @Override
        public N insert(N root, E value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void delete(N node) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setRoot(N root) {
            throw new UnsupportedOperationException();
        }

    }

}