package com.mmnaseri.cs.clrs.ch11.s5;

import com.mmnaseri.cs.clrs.ch11.HashTable;
import com.mmnaseri.cs.clrs.ch11.HashTableProbe;
import com.mmnaseri.cs.clrs.ch11.s3.DivisionHashCalculator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/14/15, 12:51 AM)
 */
public class OpenAddressedHashTable<E> implements HashTable<E> {

    private final HashTableProbe probe;
    private final int capacity;
    private final HashTableItem<E>[] slots;
    private final HashTableItem<E> deleted;
    private int size;

    public OpenAddressedHashTable(int capacity) {
        this(capacity, new LinearHashTableProbe(new DivisionHashCalculator()));
    }

    public OpenAddressedHashTable(int capacity, HashTableProbe probe) {
        this.probe = probe;
        this.capacity = capacity;
        //noinspection unchecked
        slots = new HashTableItem[capacity];
        size = 0;
        deleted = new HashTableItem<>(0, null);
    }

    @Override
    public E get(int index) {
        final int hash = getHash(index);
        if (hash != -1) {
            return slots[hash].getValue();
        } else {
            return null;
        }
    }

    @Override
    public void put(int index, E item) {
        for (int i = 0; i < capacity; i ++) {
            final int hash = this.probe.probe(index, capacity, i);
            boolean add = false;
            if (slots[hash] == null || slots[hash] == deleted) {
                size ++;
                add = true;
            } else if (slots[hash].getIndex() == index) {
                add = true;
            }
            if (add) {
                slots[hash] = new HashTableItem<>(index, item);
                return;
            }
        }
        throw new IllegalStateException("Hash table is full");
    }

    @Override
    public void delete(int index) {
        final int hash = getHash(index);
        if (hash != -1) {
            slots[hash] = deleted;
            size --;
        }
    }

    private int getHash(int index) {
        for (int i = 0; i < capacity; i ++) {
            final int hash = this.probe.probe(index, capacity, i);
            if (slots[hash] != null && slots[hash].getIndex() == index) {
                return hash;
            }
        }
        return -1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    private static class HashTableItem<E> {

        private final int index;
        private final E value;

        private HashTableItem(int index, E value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public E getValue() {
            return value;
        }

    }

}
