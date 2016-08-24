package com.mmnaseri.cs.clrs.ch20.s1;

import com.mmnaseri.cs.clrs.ch18.s1.Indexed;
import com.mmnaseri.cs.clrs.common.DynamicSet;

import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/1/15, 2:50 PM)
 */
public class DirectAddressingDynamicSet<I extends Indexed<Integer>> implements DynamicSet<I> {

    private Object[] storage = new Object[0];
    private int size;
    private I minimum;
    private I maximum;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public I find(Integer key) {
        //noinspection unchecked
        return (I) storage[key];
    }

    @Override
    public void add(I value) {
        final Integer index = value.getKey();
        ensureSize(index);
        storage[index] = value;
        size ++;
        if (minimum == null || value.getKey() < minimum.getKey()) {
            minimum = value;
        }
        if (maximum == null || value.getKey() > maximum.getKey()) {
            maximum = value;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(Integer index) {
        checkIndex(index, false);
        if (index >= storage.length || storage[index] == null) {
            return;
        }
        Object value = storage[index];
        size --;
        shrinkIfNeeded();
        if (value == minimum) {
            minimum = successor(index);
        }
        if (value == maximum) {
            maximum = predecessor(index);
        }
        storage[index] = null;
    }

    @Override
    public I minimum() {
        return minimum;
    }

    @Override
    public I maximum() {
        return maximum;
    }

    @Override
    public I successor(Integer key) {
        checkIndex(key, true);
        if (storage[key] == null) {
            return null;
        }
        for (int i = key + 1; i < storage.length; i++) {
            if (storage[i] != null) {
                //noinspection unchecked
                return (I) storage[i];
            }
        }
        return null;
    }

    @Override
    public I predecessor(Integer key) {
        checkIndex(key, true);
        if (storage[key] == null) {
            return null;
        }
        for (int i = key - 1; i >= 0; i--) {
            if (storage[i] != null) {
                //noinspection unchecked
                return (I) storage[i];
            }
        }
        return null;
    }

    private void checkIndex(Integer index, boolean upperBound) {
        Objects.requireNonNull(index, "Index cannot be null");
        if (index < 0 || upperBound && index >= storage.length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    private void ensureSize(Integer index) {
        checkIndex(index, false);
        if (storage.length <= index) {
            final Object[] temp = new Object[index + 1];
            System.arraycopy(storage, 0, temp, 0, storage.length);
            storage = temp;
        }
    }

    protected void shrinkIfNeeded() {
        int newSize = storage.length;
        while (storage[newSize - 1] == null) {
            newSize --;
        }
        if (newSize < storage.length) {
            final Object[] temp = new Object[newSize];
            System.arraycopy(storage, 0, temp, 0, newSize);
            storage = temp;
        }
    }

}
