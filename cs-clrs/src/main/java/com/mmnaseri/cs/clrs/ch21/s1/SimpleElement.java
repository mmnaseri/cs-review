package com.mmnaseri.cs.clrs.ch21.s1;

import com.mmnaseri.cs.clrs.ch21.Element;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 2:54 AM)
 */
class SimpleElement<E> implements Element<E> {

    private E value;
    private ElementContainer<E> container;
    private SimpleElement<E> previous;
    private SimpleElement<E> next;

    ElementContainer<E> getContainer() {
        return container;
    }

    void setContainer(ElementContainer<E> container) {
        this.container = container;
    }

    @Override
    public E getValue() {
        return value;
    }

    void setValue(E value) {
        this.value = value;
    }

    SimpleElement<E> getPrevious() {
        return previous;
    }

    void setPrevious(SimpleElement<E> previous) {
        this.previous = previous;
    }

    SimpleElement<E> getNext() {
        return next;
    }

    void setNext(SimpleElement<E> next) {
        this.next = next;
    }

}
