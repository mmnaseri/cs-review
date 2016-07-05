package com.mmnaseri.cs.clrs.ch06.s1;

import com.mmnaseri.cs.clrs.common.BaseHeapTest;
import com.mmnaseri.cs.clrs.common.Heap;
import com.mmnaseri.cs.clrs.common.impl.MinHeapProperty;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (7/5/16, 2:38 PM)
 */
public class ArrayHeapTest extends BaseHeapTest {

    @Override
    protected Heap<String> getMinHeap() {
        return new ArrayHeap<>(new MinHeapProperty<String>());
    }

    @Test
    public void testCreatingAHeapOutOfACollection() throws Exception {
        final List<Integer> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add(new Random().nextInt(200));
        }
        final Heap<Integer> heap = new ArrayHeap<>(new MinHeapProperty<Integer>(), items);
        assertThat(heap.size(), is(items.size()));
        Integer previous = Integer.MIN_VALUE;
        while (!heap.isEmpty()) {
            int size = heap.size();
            final Integer top = heap.peek();
            assertThat(heap.size(), is(size));
            assertThat(heap.pop(), is(top));
            assertThat(heap.size(), is(size - 1));
            assertThat(top, is(greaterThanOrEqualTo(previous)));
            previous = top;
        }
    }

    @Test
    public void testAddingToTheHeap() throws Exception {
        final Heap<String> heap = new ArrayHeap<>(new MinHeapProperty<String>(), 0);
        assertThat(heap.size(), is(0));
        heap.add("b");
        assertThat(heap.peek(), is("b"));
        heap.add("a");
        assertThat(heap.peek(), is("a"));
        heap.add("c");
        assertThat(heap.peek(), is("a"));
        assertThat(heap.pop(), is("a"));
        assertThat(heap.pop(), is("b"));
        assertThat(heap.pop(), is("c"));
        assertThat(heap.isEmpty(), is(true));
    }

}