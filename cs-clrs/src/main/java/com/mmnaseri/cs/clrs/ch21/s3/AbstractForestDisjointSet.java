package com.mmnaseri.cs.clrs.ch21.s3;

import com.mmnaseri.cs.clrs.ch21.DisjointSet;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 11:47 AM)
 */
public abstract class AbstractForestDisjointSet<E extends TreeElement<I, E>, I> implements DisjointSet<E, I> {

    private final UUID uuid;

    public AbstractForestDisjointSet() {
        uuid = UUID.randomUUID();
    }

    protected UUID getUuid() {
        return uuid;
    }

    @Override
    public E find(E item) {
        control(item);
        while (!item.isRoot()) {
            item = item.getParent();
            control(item);
        }
        return item;
    }

    @Override
    public E union(E first, E second) {
        control(first);
        control(second);
        final E root = find(first);
        second.setParent(root);
        return root;
    }

    @Override
    public Set<I> elements(E item) {
        final E root = find(item);
        return collect(root);
    }

    private Set<I> collect(E root) {
        final Set<I> values = new HashSet<>();
        values.add(root.getValue());
        for (E child : root.getChildren()) {
            values.addAll(collect(child));
        }
        return values;
    }

    protected void control(E element) {
        Objects.requireNonNull(element.getUuid());
        if (!element.getUuid().equals(getUuid())) {
            throw new IllegalArgumentException("Item does not belong to this context");
        }
    }

}
