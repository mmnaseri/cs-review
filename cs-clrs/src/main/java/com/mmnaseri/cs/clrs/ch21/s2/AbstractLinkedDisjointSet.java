package com.mmnaseri.cs.clrs.ch21.s2;

import com.mmnaseri.cs.clrs.ch21.DisjointSet;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 3:17 AM)
 */
public abstract class AbstractLinkedDisjointSet<E extends LinkedElement<E, I, C>, I, C extends LinkedElementContainer<E, I>> implements DisjointSet<E, I> {

    private final UUID uuid;
    private final Set<E> roots = new HashSet<>();

    public AbstractLinkedDisjointSet() {
        uuid = UUID.randomUUID();
    }

    protected UUID getUuid() {
        return uuid;
    }

    @Override
    public final E create(I representative) {
        final E root = newRoot(representative);
        root.getContainer().setUuid(uuid);
        roots.add(root);
        return root;
    }

    @Override
    public E find(E item) {
        control(item);
        return item.getContainer().getHead();
    }

    @Override
    public Set<I> elements(E item) {
        E element = item.getContainer().getHead();
        final Set<I> result = new HashSet<>();
        while (element != null) {
            result.add(element.getValue());
            element = element.getNext();
        }
        return result;
    }

    protected E absorb(E parent, E child) {
        control(parent);
        control(child);
        roots.remove(child);
        E element = child.getContainer().getHead();
        parent.getContainer().getTail().setNext(child.getContainer().getHead());
        child.getContainer().getHead().setPrevious(parent.getContainer().getTail());
        parent.getContainer().setTail(child.getContainer().getTail());
        child.getContainer().unlink();
        while (element != null) {
            element.setContainer(parent.getContainer());
            element = element.getNext();
        }
        return parent.getContainer().getHead();
    }

    protected void control(E item) {
        Objects.requireNonNull(item, "Input item cannot be null");
        Objects.requireNonNull(item.getContainer(), "Input item does not belong to any disjoint sets");
        Objects.requireNonNull(item.getContainer().getUuid(), "Disjoint set for the item is not signed");
        if (!item.getContainer().getUuid().equals(uuid)) {
            throw new IllegalArgumentException("Object does not belong to the current context");
        }
    }

    @Override
    public Set<E> sets() {
        return new HashSet<>(roots);
    }

    protected abstract E newRoot(I representative);

}
