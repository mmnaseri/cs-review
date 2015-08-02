package com.mmnaseri.cs.clrs.ch20.s1;

import com.mmnaseri.cs.clrs.ch18.s1.ReflectiveIndexed;
import com.mmnaseri.cs.clrs.common.DynamicSet;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/1/15, 3:10 PM)
 */
public class FixedSizeDynamicSet implements DynamicSet<ReflectiveIndexed<Integer>> {

    private int size;
    private final boolean[] storage;
    private int min = -1;
    private int max = -1;

    public FixedSizeDynamicSet(int capacity) {
        storage = new boolean[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public ReflectiveIndexed<Integer> find(Integer key) {
        return storage[key] ? new ReflectiveIndexed<>(key) : null;
    }

    @Override
    public void add(ReflectiveIndexed<Integer> value) {
        if (!storage[value.getKey()]) {
            storage[value.getKey()] = true;
            size ++;
        }
        if (min == -1 || value.getKey() < min) {
            min = value.getKey();
        }
        if (max == -1 || value.getKey() > max) {
            max = value.getKey();
        }
    }

    @Override
    public void delete(Integer index) {
        if (storage[index]) {
            if (min == index) {
                final ReflectiveIndexed<Integer> successor = successor(index);
                min = successor != null ? successor.getKey() : -1;
            }
            if (max == index) {
                final ReflectiveIndexed<Integer> predecessor = predecessor(index);
                max = predecessor != null ? predecessor.getKey() : -1;
            }
            storage[index] = false;
            size --;
        }
    }

    @Override
    public ReflectiveIndexed<Integer> minimum() {
        return min == -1 ? null : new ReflectiveIndexed<>(min);
    }

    @Override
    public ReflectiveIndexed<Integer> maximum() {
        return max == -1 ? null : new ReflectiveIndexed<>(max);
    }

    @Override
    public ReflectiveIndexed<Integer> successor(Integer key) {
        if (!storage[key]) {
            return null;
        }
        for (int i = key + 1; i < storage.length; i++) {
            if (storage[i]) {
                return new ReflectiveIndexed<>(i);
            }
        }
        return null;
    }

    @Override
    public ReflectiveIndexed<Integer> predecessor(Integer key) {
        if (!storage[key]) {
            return null;
        }
        for (int i = key - 1; i >= 0; i--) {
            if (storage[i]) {
                return new ReflectiveIndexed<>(i);
            }
        }
        return null;
    }

}
