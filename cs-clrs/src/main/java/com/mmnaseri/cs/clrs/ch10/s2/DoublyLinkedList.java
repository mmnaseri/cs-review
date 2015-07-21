package com.mmnaseri.cs.clrs.ch10.s2;

import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 11:41 PM)
 */
@Quality(Stage.TESTED)
public class DoublyLinkedList<E> extends SinglyLinkedList<E> {

    private static class DoublyLinkedListItem<E> extends LinkedListItem<E> {

        private LinkedListItem<E> previous;

        private DoublyLinkedListItem(E value) {
            super(value);
        }

        @Override
        public DoublyLinkedListItem<E> getNext() {
            return (DoublyLinkedListItem<E>) super.getNext();
        }

        public LinkedListItem<E> getPrevious() {
            return previous;
        }

        public void setPrevious(LinkedListItem<E> previous) {
            this.previous = previous;
        }

    }

    @Override
    protected DoublyLinkedListItem<E> getFirst() {
        return (DoublyLinkedListItem<E>) super.getFirst();
    }

    @Override
    protected DoublyLinkedListItem<E> getByIndex(int index) {
        return (DoublyLinkedListItem<E>) super.getByIndex(index);
    }

    @Override
    public void insert(int index, E item) {
        if (index < 0 || index > getSize()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        final DoublyLinkedListItem<E> current;
        if (index == 0) {
            current = getFirst();
        } else if (index != getSize()) {
            current = getByIndex(index);
        } else {
            current = null;
        }
        final DoublyLinkedListItem<E> listItem = new DoublyLinkedListItem<>(item);
        if (current == null) {
            if (index == 0) {
                setFirst(listItem);
            } else {
                final DoublyLinkedListItem<E> previous = getByIndex(index - 1);
                previous.setNext(listItem);
                listItem.setPrevious(previous);
            }
        } else {
            final LinkedListItem<E> previous = current.getPrevious();
            if (previous == null) {
                setFirst(listItem);
            } else {
                previous.setNext(listItem);
                listItem.setPrevious(previous);
            }
            listItem.setNext(current);
            current.setPrevious(listItem);
        }
        increaseSize();
    }

    @Override
    public void delete(int index) {
        final DoublyLinkedListItem<E> item = getByIndex(index);
        if (item.getPrevious() != null) {
            item.getPrevious().setNext(item.getNext());
        } else {
            setFirst(item.getNext());
        }
        if (item.getNext() != null) {
            item.getNext().setPrevious(item.getPrevious());
        }
        decreaseSize();
    }

}
