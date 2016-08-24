package com.mmnaseri.cs.clrs.ch12.sp;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/19/15, 9:43 PM)
 */
public class RadixTreeTest {

    @Test
    public void testEmptyTree() throws Exception {
        final RadixTree tree = new RadixTree();
        assertThat(tree.getRoot(), is(notNullValue()));
        assertThat(tree.getRoot().getLeftChild(), is(nullValue()));
        assertThat(tree.getRoot().getRightChild(), is(nullValue()));
        assertThat(tree.getRoot().getValue(), is(Bit.NEUTRAL));
        assertThat(tree.list(), emptyCollectionOf(Integer.class));
    }

    @Test
    public void testInsertingNumbers() throws Exception {
        final RadixTree tree = new RadixTree();
        assertThat(tree.list(), hasSize(0));
        tree.insert(1);
        assertThat(tree.list(), hasSize(1));
        tree.insert(1);
        assertThat(tree.list(), hasSize(1));
        tree.insert(2);
        assertThat(tree.list(), hasSize(2));
        tree.insert(3);
        assertThat(tree.list(), hasSize(3));
    }

    @Test
    public void testDeletingNumbers() throws Exception {
        final RadixTree tree = new RadixTree();
        tree.insert(1, 2, 3, 4);
        assertThat(tree.list(), hasSize(4));
        tree.delete(1);
        assertThat(tree.list(), hasSize(3));
        tree.delete(2);
        assertThat(tree.list(), hasSize(2));
        tree.delete(3);
        assertThat(tree.list(), hasSize(1));
        tree.delete(3);
        assertThat(tree.list(), hasSize(1));
        tree.delete(5);
        assertThat(tree.list(), hasSize(1));
        tree.delete(4);
        assertThat(tree.list(), hasSize(0));
        tree.insert(0b111, 0b1111);
        assertThat(tree.list(), hasSize(2));
        tree.delete(0b1111);
        assertThat(tree.list(), hasSize(1));
        tree.delete(0b11);
        assertThat(tree.list(), hasSize(1));
        tree.delete(0b111);
        assertThat(tree.list(), hasSize(0));
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testBitInsertion() throws Exception {
        new RadixTree().insert(Bit.NEUTRAL);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testBitDeletion() throws Exception {
        new RadixTree().delete(Bit.NEUTRAL);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testRootModification() throws Exception {
        new RadixTree().setRoot(new RadixTreeNode());
    }

    @Test
    public void testFinding() throws Exception {
        final RadixTree tree = new RadixTree();
        tree.insert(0b11111, 0b111);
        assertThat(tree.find(0b1), is(nullValue()));
        assertThat(tree.find(0b11), is(nullValue()));
        assertThat(tree.find(0b111), is(notNullValue()));
        assertThat(tree.find(0b1111), is(nullValue()));
        assertThat(tree.find(0b11111), is(notNullValue()));
        assertThat(tree.find(0b111111), is(nullValue()));
    }
}