package com.mmnaseri.cs.clrs.ch11.s2;

import com.mmnaseri.cs.clrs.ch11.HashCalculator;
import com.mmnaseri.cs.clrs.ch11.HashTable;
import com.mmnaseri.cs.clrs.ch11.s3.DivisionHashCalculator;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/13/15)
 */
@Quality(Stage.TESTED)
public class ChainingHashTable<E> implements HashTable<E> {

    private static class HashTableItem<E> {

        private final int index;
        private final E value;
        private HashTableItem<E> previous;
        private HashTableItem<E> next;

        public HashTableItem(int index, E value) {
            this.index = index;
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public int getIndex() {
            return index;
        }

        public HashTableItem<E> getPrevious() {
            return previous;
        }

        public void setPrevious(HashTableItem<E> previous) {
            this.previous = previous;
        }

        public HashTableItem<E> getNext() {
            return next;
        }

        public void setNext(HashTableItem<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(value) + (next == null ? "" : "," + next);
        }

    }

    private final HashTableItem<E>[] slots;
    private final int capacity;
    private final HashCalculator hashCalculator;
    private int size;

    public ChainingHashTable(int capacity) {
        this(capacity, new DivisionHashCalculator());
    }

    public ChainingHashTable(int capacity, HashCalculator hashCalculator) {
        this.capacity = capacity;
        this.hashCalculator = hashCalculator;
        //noinspection unchecked
        slots = new HashTableItem[capacity];
        size = 0;
    }

    @Override
    public E get(int index) {
        final int hash = hashCalculator.calculate(index, capacity);
        final HashTableItem<E> item = find(index, hash);
        return item == null ? null : item.getValue();
    }

    @Override
    public void put(int index, E item) {
        final int hash = hashCalculator.calculate(index, capacity);
        final HashTableItem<E> tableItem = new HashTableItem<>(index, item);
        if (slots[hash] == null) {
            slots[hash] = tableItem;
            size ++;
        } else {
            final HashTableItem<E> found = find(index, hash);
            if (found != null) {
                if (found.getPrevious() != null) {
                    found.getPrevious().setNext(tableItem);
                } else {
                    slots[hash] = tableItem;
                }
                if (found.getNext() != null) {
                    found.getNext().setPrevious(tableItem);
                }
                found.setPrevious(null);
                found.setNext(null);
                return;
            }
            tableItem.setNext(slots[hash]);
            slots[hash].setPrevious(tableItem);
            slots[hash] = tableItem;
            size ++;
        }
    }

    @Override
    public void delete(int index) {
        final int hash = hashCalculator.calculate(index, capacity);
        final HashTableItem<E> item = find(index, hash);
        if (item != null) {
            if (item.getNext() == null && item.getPrevious() == null) {
                slots[hash] = null;
                size --;
                return;
            }
            if (item.getNext() != null) {
                item.getNext().setPrevious(item.getPrevious());
            }
            if (item.getPrevious() != null) {
                item.getPrevious().setNext(item.getNext());
            }
            item.setPrevious(null);
            item.setNext(null);
            size --;
        }
    }

    private HashTableItem<E> find(int index, int hash) {
        if (slots[hash] == null) {
            return null;
        }
        HashTableItem<E> cursor = slots[hash];
        while (cursor != null) {
            if (cursor.getIndex() == index) {
                return cursor;
            }
            cursor = cursor.getNext();
        }
        return null;
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

}
