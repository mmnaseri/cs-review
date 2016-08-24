package com.mmnaseri.cs.clrs.ch21.s3;

import com.mmnaseri.cs.clrs.ch21.Element;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 11:45 AM)
 */
public class TreeElement<I, E extends TreeElement<I, E>> implements Element<I> {

    private I value;
    private E parent;
    private Set<E> children = new HashSet<>();
    private UUID uuid;

    @Override
    public I getValue() {
        return value;
    }

    public void setValue(I value) {
        this.value = value;
    }

    public E getParent() {
        return parent;
    }

    public void setParent(E parent) {
        this.parent = parent;
        if (parent != null) {
            //noinspection unchecked
            parent.getChildren().add((E) this);
        }
    }

    public boolean isRoot() {
        return getParent() == null;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Set<E> getChildren() {
        return children;
    }

    public void setChildren(Set<E> children) {
        this.children = children;
    }

}
