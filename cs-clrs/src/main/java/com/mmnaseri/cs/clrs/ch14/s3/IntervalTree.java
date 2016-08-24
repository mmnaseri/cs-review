package com.mmnaseri.cs.clrs.ch14.s3;

import com.mmnaseri.cs.clrs.ch12.s2.TreeNodeFactory;
import com.mmnaseri.cs.clrs.ch13.s1.RedBlackTreeNode;
import com.mmnaseri.cs.clrs.ch13.s3.RedBlackTree;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/20/15, 4:21 AM)
 */
@Quality(value = Stage.UNTESTED, explanation = "Node maximum is calculated in O(lgn) and not maintained during insertion and deletion")
public class IntervalTree extends RedBlackTree<Interval> {

    private final TreeNodeFactory<Interval, RedBlackTreeNode<Interval>> factory;

    public IntervalTree() {
        super(Interval.NATURAL_ORDER);
        factory = new TreeNodeFactory<Interval, RedBlackTreeNode<Interval>>() {
            @Override
            public RedBlackTreeNode<Interval> createNode(Interval value) {
                final IntervalTreeNode node = new IntervalTreeNode();
                node.setValue(value);
                return node;
            }
        };
    }

    @Override
    protected TreeNodeFactory<Interval, RedBlackTreeNode<Interval>> getFactory() {
        return factory;
    }

    @Override
    public IntervalTreeNode insert(Interval value) {
        return (IntervalTreeNode) super.insert(value);
    }

    @Override
    public IntervalTreeNode delete(Interval value) {
        return (IntervalTreeNode) super.delete(value);
    }

    @Override
    public IntervalTreeNode find(Interval value) {
        return (IntervalTreeNode) super.find(value);
    }

    @Override
    public IntervalTreeNode getRoot() {
        return (IntervalTreeNode) super.getRoot();
    }

    public IntervalTreeNode search(Interval criteria) {
        IntervalTreeNode current = getRoot();
        while (current != null && !current.getValue().overlaps(criteria)) {
            if (current.getLeftChild() != null && current.getLeftChild().getMaximum() >= criteria.getStart()) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }
        return current;
    }

}
