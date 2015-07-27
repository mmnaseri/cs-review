package com.mmnaseri.cs.clrs.ch13.sp;

import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import com.mmnaseri.cs.clrs.ch13.s2.RotatingBinarySearchTree;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/26/15, 8:05 PM)
 */
@Quality(Stage.UNTESTED)
public class Treap<E> extends RotatingBinarySearchTree<E, TreapNode<E>> {

    public Treap(Comparator<E> comparator) {
        super(comparator, new TreapNodeFactory<E>());
    }

    protected PriorityManager getPriorityManager() {
        return ((TreapNodeFactory<E>) getFactory()).getPriorityManager();
    }

    protected void rotateUp(TreapNode<E> node) {
        while (!node.isRoot() && node.getParent().getPriority() < node.getPriority()) {
            if (node == node.getParent().getLeftChild()) {
                rotateRight(node);
            } else {
                rotateLeft(node);
            }
        }
        if (node.isRoot()) {
            setRoot(node);
        }
    }

    protected void trickleDown(TreapNode<E> node) {
        while (!node.isLeaf()) {
            final boolean root = node.isRoot();
            if (node.getLeftChild() == null) {
                rotateLeft(node);
            } else if (node.getRightChild() == null) {
                rotateRight(node);
            } else if (node.getLeftChild().getPriority() < node.getRightChild().getPriority()) {
                rotateRight(node);
            } else {
                rotateLeft(node);
            }
            if (root) {
                setRoot(node.getParent());
            }
        }
    }

    @Override
    public TreapNode<E> insert(E value) {
        final TreapNode<E> node = super.insert(value);
        rotateUp(node);
        return node;
    }

    @Override
    public TreapNode<E> delete(E value) {
        final TreapNode<E> node = find(value);
        if (node == null) {
            return null;
        }
        trickleDown(node);
        delete(node);
        return node;
    }

    protected static class PriorityManager {

        private final Set<Integer> priorities = new HashSet<>();
        private final Random random = new Random();

        private int get() {
            int priority;
            do {
                priority = Math.abs(random.nextInt());
            } while (priorities.contains(priority));
            return priority;
        }

        private void discard(int priority) {
            priorities.remove(priority);
        }

    }

    private static class TreapNodeFactory<E> implements TreeNodeFactory<E, TreapNode<E>> {

        private final PriorityManager priorityManager = new PriorityManager();

        @Override
        public TreapNode<E> createNode(E value) {
            final TreapNode<E> node = new TreapNode<>();
            node.setValue(value);
            node.setPriority(priorityManager.get());
            return node;
        }

        public PriorityManager getPriorityManager() {
            return priorityManager;
        }

    }

}
