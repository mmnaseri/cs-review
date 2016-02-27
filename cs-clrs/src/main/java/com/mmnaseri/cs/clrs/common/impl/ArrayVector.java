package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.Vector;

import java.util.Iterator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
public class ArrayVector<E> implements Vector<E> {

    private final Object[] source;
    private final int size;

    public ArrayVector(int size) {
        this.size = size;
        source = new Object[size];
    }

    @Override
    public E get(int index) {
        //noinspection unchecked
        return (E) source[index];
    }

    @Override
    public void set(int index, E value) {
        source[index] = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(source);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < source.length; i++) {
            final Object value = source[i];
            if (i > 0) {
                builder.append(",");
            }
            builder.append(value);
        }
        builder.append("]");
        return builder.toString();
    }

    private static class ArrayIterator<E> implements Iterator<E> {

        private final Object[] array;
        private int cursor = 0;

        private ArrayIterator(Object[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return cursor < array.length;
        }

        @Override
        public E next() {
            //noinspection unchecked
            return (E) array[cursor];
        }

    }

}
