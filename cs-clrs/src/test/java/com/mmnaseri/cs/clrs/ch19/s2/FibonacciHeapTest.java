package com.mmnaseri.cs.clrs.ch19.s2;

import com.mmnaseri.cs.clrs.ch06.s1.ArrayHeap;
import com.mmnaseri.cs.clrs.common.BaseHeapTest;
import com.mmnaseri.cs.clrs.common.Heap;
import com.mmnaseri.cs.clrs.common.MergeableHeap;
import com.mmnaseri.cs.clrs.common.impl.MinHeapProperty;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/1/15, 11:30 AM)
 */
public class FibonacciHeapTest extends BaseHeapTest {

    protected MergeableHeap<String> getMinHeap() {
        return new FibonacciHeap<>(new MinHeapProperty<String>());
    }

    @Test
    public void testMergingTwoHeaps() throws Exception {
        final MergeableHeap<String> first = getMinHeap();
        final MergeableHeap<String> second = getMinHeap();
        first.add("6");
        first.add("5");
        first.add("4");
        first.add("3");
        first.add("2");
        first.add("1");
        first.pop();
        assertThat(first.size(), is(5));
        assertThat(first.isEmpty(), is(false));
        assertThat(first.peek(), is("2"));
        second.add("9");
        second.add("8");
        second.add("7");
        second.add("6");
        second.add("5");
        second.pop();
        assertThat(second.size(), is(4));
        assertThat(second.isEmpty(), is(false));
        assertThat(second.peek(), is("6"));
        second.merge(first);
        assertThat(first.isEmpty(), is(true));
        assertThat(first.size(), is(0));
        assertThat(second.isEmpty(), is(false));
        assertThat(second.size(), is(9));
        assertThat(second.peek(), is("2"));
    }

    @Test
    public void testMergingEmptyHeapWithNonEmptyHeap() throws Exception {
        final MergeableHeap<String> first = getMinHeap();
        final MergeableHeap<String> second = getMinHeap();
        second.add("1");
        second.add("2");
        second.add("3");
        second.add("4");
        second.pop();
        assertThat(first.isEmpty(), is(true));
        assertThat(second.isEmpty(), is(false));
        assertThat(second.size(), is(3));
        assertThat(second.peek(), is("2"));
        first.merge(second);
        assertThat(second.isEmpty(), is(true));
        assertThat(second.size(), is(0));
        assertThat(first.size(), is(3));
        assertThat(first.isEmpty(), is(false));
        assertThat(first.peek(), is("2"));
    }

    @Test
    public void testMergingNonEmptyHeapWithEmptyHeap() throws Exception {
        final MergeableHeap<String> first = getMinHeap();
        final MergeableHeap<String> second = getMinHeap();
        second.add("1");
        second.add("2");
        second.add("3");
        second.add("4");
        second.pop();
        assertThat(first.isEmpty(), is(true));
        assertThat(second.isEmpty(), is(false));
        assertThat(second.size(), is(3));
        assertThat(second.peek(), is("2"));
        second.merge(first);
        assertThat(first.isEmpty(), is(true));
        assertThat(first.size(), is(0));
        assertThat(second.size(), is(3));
        assertThat(second.isEmpty(), is(false));
        assertThat(second.peek(), is("2"));
    }

    @Test
    public void testMergingWithNonMergeableHeap() throws Exception {
        final MergeableHeap<String> heap = getMinHeap();
        heap.add("1");
        heap.add("2");
        heap.add("3");
        heap.add("4");
        heap.pop();
        assertThat(heap.size(), is(3));
        assertThat(heap.isEmpty(), is(false));
        assertThat(heap.peek(), is("2"));
        final Heap<String> secondary = new ArrayHeap<>(new MinHeapProperty<String>());
        secondary.add("0");
        secondary.add("1");
        secondary.add("5");
        secondary.add("9");
        secondary.add("14");
        secondary.pop();
        assertThat(secondary.size(), is(4));
        assertThat(secondary.isEmpty(), is(false));
        assertThat(secondary.peek(), is("1"));
        heap.merge(secondary);
        assertThat(secondary.size(), is(0));
        assertThat(secondary.isEmpty(), is(true));
        assertThat(heap.size(), is(7));
        assertThat(heap.isEmpty(), is(false));
        assertThat(heap.peek(), is("1"));
    }

}