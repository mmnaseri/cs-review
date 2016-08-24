package com.mmnaseri.cs.clrs.ch19.s1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/1/15, 11:14 AM)
 */
public class FibonacciHeapNode<E extends Comparable<E>> {

    private E value;
    private boolean marked;
    private FibonacciHeapNode<E> previous;
    private FibonacciHeapNode<E> next;
    private FibonacciHeapNode<E> child;
    private FibonacciHeapNode<E> parent;
    private int degree;

    public FibonacciHeapNode(E value) {
        this.value = value;
        this.marked = false;
        this.degree = 0;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public FibonacciHeapNode<E> getPrevious() {
        return previous;
    }

    public void setPrevious(FibonacciHeapNode<E> previous) {
        this.previous = previous;
    }

    public FibonacciHeapNode<E> getNext() {
        return next;
    }

    public void setNext(FibonacciHeapNode<E> next) {
        this.next = next;
    }

    public FibonacciHeapNode<E> getChild() {
        return child;
    }

    public void setChild(FibonacciHeapNode<E> child) {
        this.child = child;
    }

    public FibonacciHeapNode<E> getParent() {
        return parent;
    }

    public void setParent(FibonacciHeapNode<E> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

}
