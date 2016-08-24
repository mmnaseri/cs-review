package com.mmnaseri.cs.clrs.common;

import com.mmnaseri.cs.clrs.common.Heap;
import com.mmnaseri.cs.clrs.common.TestTools;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/1/15, 11:30 AM)
 */
public abstract class BaseHeapTest {

    protected abstract Heap<String> getMinHeap();

    @Test
    public void testInitialState() throws Exception {
        final Heap<String> heap = getMinHeap();
        assertThat(heap.size(), is(0));
        assertThat(heap.isEmpty(), is(true));
    }

    @Test(expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void testPoppingFromEmptyHeap() throws Exception {
        final Heap<String> heap = getMinHeap();
        heap.pop();
    }

    @Test
    public void testClearingTheHeap() throws Exception {
        final Heap<String> heap = getMinHeap();
        heap.add("1");
        heap.add("2");
        heap.add("3");
        heap.add("4");
        heap.add("5");
        heap.add("6");
        heap.add("7");
        heap.add("8");
        heap.add("9");
        heap.pop();
        assertThat(heap.size(), is(8));
        assertThat(heap.isEmpty(), is(false));
        heap.clear();
        assertThat(heap.size(), is(0));
        assertThat(heap.isEmpty(), is(true));
    }

    @DataProvider
    public Object[][] stressTestDataProvider() {
        final List<String> sample = TestTools.sampleProducer(5);
        final List<Object[]> output = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            output.add(new Object[]{sample, i});
        }
        return output.toArray(new Object[output.size()][]);
    }
    
    @Test(dataProvider = "stressTestDataProvider")
    public void stressTest(List<String> input, int size) {
        final Heap<String> heap = getMinHeap();
        assertThat(heap.isEmpty(), is(true));
        for (int i = size; i > 0; i--) {
            assertThat(heap.size(), is(size - i));
            heap.add(input.get(i - 1));
            assertThat(heap.size(), is(size - i + 1));
            assertThat(heap.isEmpty(), is(false));
        }
        final List<String> inserted = new ArrayList<>(input).subList(0, size);
        Collections.sort(inserted);
        for (int i = 0; i < inserted.size(); i++) {
            assertThat(heap.size(), is(inserted.size() - i));
            assertThat(heap.peek(), is(inserted.get(i)));
            assertThat(heap.pop(), is(inserted.get(i)));
            assertThat(heap.size(), is(inserted.size() - i - 1));
        }
        assertThat(heap.isEmpty(), is(true));
    }

    @Test(expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void testEmptyingAnEmptyHeap() throws Exception {
        final Heap<String> heap = getMinHeap();
        heap.pop();
    }

}