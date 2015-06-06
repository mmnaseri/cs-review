package com.mmnaseri.cs.algorithm.clrs.ch6.s1;

import com.mmnaseri.cs.algorithm.common.Heap;
import com.mmnaseri.cs.algorithm.common.HeapProperty;
import com.mmnaseri.cs.algorithm.common.impl.MinHeapProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (6/6/15, 3:21 PM)
 */
public class ArrayHeap<E extends Comparable<E>> implements Heap<E> {

    public static final int DEFAULT_CAPACITY = 256;
    private final HeapProperty<E> heapProperty;
    private Object[] data;
    private int size = 0;
    private long timestamp;

    public ArrayHeap(HeapProperty<E> heapProperty) {
        this(heapProperty, DEFAULT_CAPACITY);
    }

    public ArrayHeap(HeapProperty<E> heapProperty, int initialCapacity) {
        this.heapProperty = heapProperty;
        data = new Object[Math.max(4, initialCapacity)];
        mark();
    }

    public ArrayHeap(HeapProperty<E> heapProperty, Collection<E> items) {
        this(heapProperty, items.size());
        size = items.size();
        final Iterator<E> iterator = items.iterator();
        for (int i = 0; i < items.size(); i ++) {
            data[i] = iterator.next();
        }
        for (int i = size / 2; i >= 0; i --) {
            heapify(i);
        }
    }

    private void checkCapacity() {
        final Object[] temp;
        if (size > 3 * (data.length / 4.0)) {
            temp = new Object[data.length * 2];
            System.arraycopy(data, 0, temp, 0, size);
        } else if (size < data.length / 4.0) {
            temp = new Object[data.length / 2];
            System.arraycopy(data, 0, temp, 0, size);
        } else {
            temp = data;
        }
        data = temp;
    }

    private void mark() {
        timestamp = System.currentTimeMillis();
    }

    private int left(int index) {
        return index * 2;
    }

    private int right(int index) {
        return index * 2 + 1;
    }

    private int parent(int index) {
        return (int) Math.floor(index / 2);
    }

    private void swap(int i, int j) {
        final Object temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private void heapify(int index) {
        final int left = left(index);
        final int right = right(index);
        int value;
        if (left < size && heapProperty.compare(get(index), get(left)) > 0) {
            value = left;
        } else {
            value = index;
        }
        if (right < size && heapProperty.compare(get(value), get(right)) > 0) {
            value = right;
        }
        if (value != index) {
            swap(value, index);
            heapify(value);
        }
    }

    @Override
    public void clear() {
        data = new Object[4];
        size = 0;
    }

    @Override
    public E peek() {
        return get(0);
    }

    @Override
    public E pop() {
        mark();
        final E top = peek();
        //restructure the heap
        data[0] = data[size - 1];
        size --;
        heapify(0);
        checkCapacity();
        return top;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        //noinspection unchecked
        return (E) data[index];
    }

    @Override
    public int change(int index, E newValue) {
        mark();
        if (newValue == null) {
            throw new NullPointerException();
        }
        if (data[index] != null && heapProperty.compare(get(index), newValue) < 0) {
            throw new IllegalArgumentException();
        }
        data[index] = newValue;
        while (index > 0 && heapProperty.compare(get(index), get(parent(index))) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
        return index;
    }

    @Override
    public void add(E item) {
        mark();
        checkCapacity();
        size ++;
        data[size - 1] = null;
        change(size - 1, item);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = 0;
            private long originalTimestamp = timestamp;
            @Override
            public boolean hasNext() {
                return cursor < size;
            }
            @Override
            public E next() {
                if (originalTimestamp != timestamp) {
                    throw new ConcurrentModificationException();
                }
                return get(cursor ++);
            }
        };
    }

    public static void main(String[] args) {
        final Heap<Integer> heap = new ArrayHeap<>(new MinHeapProperty<Integer>(), Arrays.asList(5, 8, 3, 5, 8, 9, 9));
        heap.add(11);
        heap.add(15);
        heap.add(19);
        while (!heap.isEmpty()) {
            final Integer value = heap.pop();
            System.out.println(value);
        }
    }

}
