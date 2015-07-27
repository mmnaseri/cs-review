package com.mmnaseri.cs.clrs.ch13.s3;

import com.mmnaseri.cs.clrs.ch12.s1.PreOrderTreeWalk;
import com.mmnaseri.cs.clrs.ch12.s1.TreeWalk;
import com.mmnaseri.cs.clrs.ch12.s1.TreeWalkCallback;
import com.mmnaseri.cs.clrs.ch13.s1.NodeColor;
import com.mmnaseri.cs.clrs.ch13.s1.RedBlackTreeNode;
import com.mmnaseri.cs.qa.monitor.FeatureController;
import com.mmnaseri.cs.qa.monitor.impl.BoundMonitor;
import com.mmnaseri.cs.qa.monitor.impl.Failure;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static com.mmnaseri.cs.qa.monitor.impl.MonitorBuilder.controlDataStructure;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15, 12:53 AM)
 */
public class MonitoredRedBlackTree<E> extends RedBlackTree<E> {

    private final BoundMonitor<MonitoredRedBlackTree<E>> monitor;

    public MonitoredRedBlackTree(Comparator<E> comparator) {
        super(comparator);
        monitor = controlDataStructure(this)
                .givenFeature("color").using(new RedBlackTreeColorController<E>())
                .andFeature("black root").using(new RedBlackTreeRootColorController<E>())
                .andFeature("red node has black children").using(new RedBlackTreeLevelColorController<E>())
                .andFeature("black height").using(new RedBlackTreeBlackHeightController<E>())
                .viaMonitor();
    }

    @Override
    public RedBlackTreeNode<E> insert(E value) {
        monitor.validate();
        final RedBlackTreeNode<E> inserted = super.insert(value);
        monitor.validate();
        return inserted;
    }

    @Override
    public RedBlackTreeNode<E> delete(E value) {
        monitor.validate();
        final RedBlackTreeNode<E> deleted = super.delete(value);
        monitor.validate();
        return deleted;
    }

    private static class RedBlackTreeColorController<E> implements FeatureController<MonitoredRedBlackTree<E>> {

        @Override
        public Set<Failure<MonitoredRedBlackTree<E>>> control(final MonitoredRedBlackTree<E> dataStructure) {
            if (dataStructure.getRoot() == null) {
                return null;
            }
            final HashSet<Failure<MonitoredRedBlackTree<E>>> failures = new HashSet<>();
            final TreeWalk<E, RedBlackTreeNode<E>> walk = new PreOrderTreeWalk<>();
            walk.perform(dataStructure.getRoot(), new TreeWalkCallback<E, RedBlackTreeNode<E>>() {
                @Override
                public void apply(RedBlackTreeNode<E> node) {
                    if (!NodeColor.BLACK.equals(node.getColor()) && !NodeColor.RED.equals(node.getColor())) {
                        failures.add(new Failure<>(node, dataStructure, "Expected node color to be either RED or BLACK but it is " + node.getColor()));
                    }
                }
            });
            return failures;
        }

    }

    private static class RedBlackTreeRootColorController<E> implements FeatureController<MonitoredRedBlackTree<E>> {

        @Override
        public Set<Failure<MonitoredRedBlackTree<E>>> control(MonitoredRedBlackTree<E> dataStructure) {
            if (dataStructure.getRoot() != null && !NodeColor.BLACK.equals(dataStructure.getRoot().getColor())) {
                return Collections.singleton(new Failure<>(dataStructure.getRoot(), dataStructure, "Expected the root to be black but it was " + dataStructure.getRoot().getColor()));
            }
            return null;
        }

    }

    private static class RedBlackTreeLevelColorController<E> implements FeatureController<MonitoredRedBlackTree<E>> {

        @Override
        public Set<Failure<MonitoredRedBlackTree<E>>> control(final MonitoredRedBlackTree<E> dataStructure) {
            if (dataStructure.getRoot() == null) {
                return null;
            }
            final HashSet<Failure<MonitoredRedBlackTree<E>>> failures = new HashSet<>();
            final TreeWalk<E, RedBlackTreeNode<E>> walk = new PreOrderTreeWalk<>();
            walk.perform(dataStructure.getRoot(), new TreeWalkCallback<E, RedBlackTreeNode<E>>() {
                @Override
                public void apply(RedBlackTreeNode<E> node) {
                    if (NodeColor.RED.equals(node.getColor())) {
                        if (node.getLeftChild() != null && !NodeColor.BLACK.equals(node.getLeftChild().getColor())) {
                            failures.add(new Failure<>(node, dataStructure, "Expected the child of RED node to be black but its left child was " + node.getLeftChild().getColor()));
                        }
                        if (node.getRightChild() != null && !NodeColor.BLACK.equals(node.getRightChild().getColor())) {
                            failures.add(new Failure<>(node, dataStructure, "Expected the child of RED node to be black but its right child was " + node.getRightChild().getColor()));
                        }
                    }
                }
            });
            return failures;
        }

    }

    private static class RedBlackTreeBlackHeightController<E> implements FeatureController<MonitoredRedBlackTree<E>> {

        @Override
        public Set<Failure<MonitoredRedBlackTree<E>>> control(MonitoredRedBlackTree<E> dataStructure) {
            if (dataStructure.getRoot() == null) {
                return null;
            }
            final TreeWalk<E, RedBlackTreeNode<E>> walk = new PreOrderTreeWalk<>();
            final Set<RedBlackTreeNode<E>> leaves = new HashSet<>();
            walk.perform(dataStructure.getRoot(), new TreeWalkCallback<E, RedBlackTreeNode<E>>() {
                @Override
                public void apply(RedBlackTreeNode<E> node) {
                    if (node.isLeaf()) {
                        leaves.add(node);
                    }
                }
            });
            final HashSet<Failure<MonitoredRedBlackTree<E>>> failures = new HashSet<>();
            int expected = -1;
            for (RedBlackTreeNode<E> leaf : leaves) {
                int blacks = 0; //whether it is a red leaf with black `null` children or an actual `black` leaf we count it as one
                RedBlackTreeNode<E> node = leaf;
                while (node != null) {
                    if (NodeColor.BLACK.equals(node.getColor())) {
                        blacks ++;
                    }
                    node = node.getParent();
                }
                if (expected < 0) {
                    expected = blacks;
                } else if (blacks != expected) {
                    failures.add(new Failure<>(leaf, dataStructure, "Expected black height of leaf to be " + expected + " but it was " + blacks));
                }
            }
            return failures;
        }

    }

}
