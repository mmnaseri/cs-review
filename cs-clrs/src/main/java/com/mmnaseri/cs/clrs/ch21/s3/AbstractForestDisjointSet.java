package com.mmnaseri.cs.clrs.ch21.s3;

import com.mmnaseri.cs.clrs.ch21.DisjointSet;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 11:47 AM)
 */
public abstract class AbstractForestDisjointSet<E extends TreeElement<I, E>, I>
    implements DisjointSet<E, I> {

  private final UUID uuid;
  private final Set<E> roots = new HashSet<>();

  public AbstractForestDisjointSet() {
    uuid = UUID.randomUUID();
  }

  @Override
  public final E create(I representative) {
    final E root = newRoot(representative);
    addRoot(root);
    root.setUuid(uuid);
    return root;
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
    final E firstRoot = find(first);
    final E secondRoot = find(second);
    final E replacement = link(firstRoot, secondRoot);
    if (replacement == firstRoot) {
      removeRoot(secondRoot);
    } else {
      removeRoot(firstRoot);
    }
    return replacement;
  }

  protected E link(E firstRoot, E secondRoot) {
    secondRoot.setParent(firstRoot);
    return firstRoot;
  }

  @Override
  public Set<I> elements(E item) {
    final E root = find(item);
    return collect(root);
  }

  @Override
  public Set<E> sets() {
    return new HashSet<>(roots);
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
    if (!element.getUuid().equals(uuid)) {
      throw new IllegalArgumentException("Item does not belong to this context");
    }
  }

  protected void addRoot(E root) {
    roots.add(root);
  }

  protected void removeRoot(E root) {
    roots.remove(root);
  }

  protected abstract E newRoot(I representative);
}
