package com.mmnaseri.cs.clrs.ch19.s2;

import com.mmnaseri.cs.clrs.ch19.s1.FibonacciHeapNode;
import com.mmnaseri.cs.clrs.common.Heap;
import com.mmnaseri.cs.clrs.common.HeapProperty;
import com.mmnaseri.cs.clrs.common.MergeableHeap;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/1/15, 12:50 AM)
 */
@Quality(value = Stage.UNTESTED, explanation = "Checked out manually")
public abstract class FibonacciHeap<E extends Comparable<E>> implements MergeableHeap<E> {

    private final HeapProperty<E> heapProperty;
    private FibonacciHeapNode<E> root = null;
    private int size = 0;

    public FibonacciHeap(HeapProperty<E> heapProperty) {
        this.heapProperty = heapProperty;
    }

    @Override
    public void merge(Heap<E> heap) {
        if (!(heap instanceof FibonacciHeap<?>)) {
            //if it isn't a fibonacci heap, just iterate over the items and merge them anyway
            while (!heap.isEmpty()) {
                final E element = heap.pop();
                add(element);
            }
        } else {
            //if it is a fibonacci heap merge by concatenating the two root chains and then
            //picking the better top node
            final FibonacciHeap<E> that = (FibonacciHeap<E>) heap;
            if (root == null) {
                //if the current root is null, we prefer the new root, regardless of its value
                root = that.root;
            } else if (that.root != null) {
                //if neither root is null, we link them together
                link(root, that.root);
                //we then pick the more suitable root element as the current root
                if (heapProperty.compare(that.root.getValue(), root.getValue()) < 0) {
                    root = that.root;
                }
            }
            //update the current tree's size by adding the value of the other tree
            size += that.size();
            //we finally free pointers to the current structure from the other tree
            //and reset its size
            that.root = null;
            that.size = 0;
        }
    }

    @Override
    public void clear() {
        //walk downward from the root, freeing every pointer
        free(root);
        size = 0;
        root = null;
    }

    private void free(FibonacciHeapNode<E> node) {
        if (node == null) {
            return;
        }
        node.setParent(null);
        node.getPrevious().setNext(null);
        node.setPrevious(null);
        node.setValue(null);
        free(node.getChild());
        free(node.getNext());
        node.setChild(null);
        node.setNext(null);
    }

    @Override
    public E peek() {
        //just return the value of the top element if there is one
        return root == null ? null : root.getValue();
    }

    @Override
    public E pop() {
        //attempting to pop an empty heap is an exception
        if (size() == 0) {
            throw new ArrayIndexOutOfBoundsException(-1);
        }
        //keep a pointer to the top of the heap
        final FibonacciHeapNode<E> top = this.root;
        //if the top is null this is an exception
        if (top == null) {
            throw new IllegalStateException();
        }
        //reset parent pointer for all children of the top element
        FibonacciHeapNode<E> child = top.getChild();
        while (child != null) {
            child.setParent(null);
            child = child.getNext();
            if (child == top.getChild()) {
                break;
            }
        }
        //link the first child into the root so that they all become root nodes
        link(top, top.getChild());
        //pluck the current root element
        pluckRoot();
        //consolidate the tree
        if (root != null) {
            consolidate();
        }
        //decrease the size
        size --;
        //return the extracted root value
        return top.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E item) {
        final FibonacciHeapNode<E> node = new FibonacciHeapNode<>(item);
        node.setNext(node);
        node.setPrevious(node);
        if (root == null) {
            root = node;
        } else {
            link(root, node);
            if (heapProperty.compare(root.getValue(), item) > 0) {
                root = node;
            }
        }
        size ++;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void link(FibonacciHeapNode<E> first, FibonacciHeapNode<E> second) {
        //if we are trying to link to a null list it shouldn't have any effects
        if (second == null) {
            return;
        }
        //insert the second chain into the current position of the first chain
        first.getNext().setPrevious(second.getPrevious());
        second.getPrevious().setNext(first.getNext());
        first.setNext(second);
        second.setPrevious(first);
    }

    private void pluckRoot() {
        //pluck the root out by unlinking it from the tree and also unlinking it from the rest of the chain
        final FibonacciHeapNode<E> top = this.root;
        if (root.getNext() == root) {
            root = null;
        } else {
            root = root.getNext();
        }
        unlink(top);
    }

    private void unlink(FibonacciHeapNode<E> node) {
        //if the node is its own neighbor we don't do anything
        if (node.getNext() != node) {
            //otherwise, first connect the next and previous elements to each other
            node.getNext().setPrevious(node.getPrevious());
            node.getPrevious().setNext(node.getNext());
            //and then release pointers to them
            node.setNext(null);
            node.setPrevious(null);
        }
    }

    private void consolidate() {
        //noinspection unchecked
        final FibonacciHeapNode<E>[] nodes = new FibonacciHeapNode[(int) Math.ceil(Math.log(size) / Math.log(2))];
        //we need to iterate over the root nodes
        final List<FibonacciHeapNode<E>> rootNodes = new ArrayList<>();
        FibonacciHeapNode<E> node = this.root;
        do {
            rootNodes.add(node);
            node = node.getNext();
        } while (node != this.root);
        for (FibonacciHeapNode<E> current : rootNodes) {
            int degree = current.getDegree();
            while (nodes[degree] != null) {
                FibonacciHeapNode<E> target = nodes[degree];
                //swap target and current if necessary so that current is the one with the smaller key than target
                if (heapProperty.compare(current.getValue(), target.getValue()) > 0) {
                    nodes[degree] = current;
                    current = target;
                    target = nodes[degree];
                    nodes[degree] = target;
                }
                //remove target from the root list
                unlink(target);
                //have target form its own circular list
                target.setNext(target);
                target.setPrevious(target);
                //make target a child of current
                if (current.getChild() == null) {
                    current.setChild(target);
                } else {
                    link(current.getChild(), target);
                }
                target.setParent(current);
                //increment current.degree
                current.setDegree(current.getDegree() + 1);
                target.setMarked(false);
                nodes[degree] = null;
                degree ++;
            }
            nodes[degree] = current;
            node = node.getNext();
        }
        //reset the root node
        root = null;
        for (FibonacciHeapNode<E> current : nodes) {
            if (current == null) {
                continue;
            }
            current.setPrevious(current);
            current.setNext(current);
            if (root == null) {
                root = current;
            } else {
                link(root, current);
                if (heapProperty.compare(root.getValue(), current.getValue()) > 0) {
                    root = current;
                }
            }
        }
    }

}
