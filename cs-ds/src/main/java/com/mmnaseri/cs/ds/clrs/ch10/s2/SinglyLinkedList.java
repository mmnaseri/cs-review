package com.mmnaseri.cs.ds.clrs.ch10.s2;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 11:08 PM)
 */
public class SinglyLinkedList<E> implements LinkedList<E> {

    protected static class LinkedListItem<E> {

        private LinkedListItem<E> next;

        private final E value;
        protected LinkedListItem(E value) {
            this.value = value;
        }

        public LinkedListItem<E> getNext() {
            return next;
        }

        public void setNext(LinkedListItem<E> next) {
            this.next = next;
        }

        public E getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value) + (next == null ? "" : "," + next);
        }

    }

    private LinkedListItem<E> first = null;
    private int size = 0;

    protected LinkedListItem<E> getFirst() {
        return first;
    }

    protected void setFirst(LinkedListItem<E> first) {
        this.first = first;
    }

    protected LinkedListItem<E> getByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        LinkedListItem<E> item = first;
        if (item == null) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        while (index > 0) {
            if (item == null) {
                throw new ArrayIndexOutOfBoundsException(index);
            }
            item = item.getNext();
            index --;
        }
        return item;
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
    public E get(int index) {
        return getByIndex(index).getValue();
    }

    @Override
    public int find(E needle) {
        LinkedListItem<E> item = this.first;
        int index = 0;
        while (item != null) {
            if (needle == null && item.getValue() == null || needle != null && needle.equals(item.getValue())) {
                return index;
            }
            item = item.getNext();
            index ++;
        }
        return -1;
    }

    @Override
    public void insert(int index, E item) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        final LinkedListItem<E> previous = index > 0 ? getByIndex(index - 1) : null;
        final LinkedListItem<E> next = index < size ? getByIndex(index) : null;
        final LinkedListItem<E> listItem = new LinkedListItem<>(item);
        if (previous != null) {
            previous.setNext(listItem);
        } else {
            first = listItem;
        }
        if (next != null) {
            listItem.setNext(next);
        }
        increaseSize();
    }

    protected void increaseSize() {
        size ++;
    }

    @Override
    public void add(E item) {
        insert(size, item);
    }

    private void deleteListItem(LinkedListItem<E> previous, LinkedListItem<E> item) {
        if (previous == null) {
            first = item.getNext();
        } else {
            previous.setNext(item.getNext());
        }
        decreaseSize();
    }

    protected void decreaseSize() {
        size --;
    }

    @Override
    public void delete(E item) {
        final int index = find(item);
        if (index == -1) {
            return;
        }
        delete(index);
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        final LinkedListItem<E> previous = index == 0 ? null : getByIndex(index - 1);
        final LinkedListItem<E> item = getByIndex(index);
        deleteListItem(previous, item);
    }

    @Override
    public String toString() {
        return "[" + (first == null ? "" : first.toString()) + "]";
    }

}
