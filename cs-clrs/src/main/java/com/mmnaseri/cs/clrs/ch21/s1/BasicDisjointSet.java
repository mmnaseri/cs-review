package com.mmnaseri.cs.clrs.ch21.s1;

import com.mmnaseri.cs.clrs.ch21.DisjointSet;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 3:41 AM)
 */
@Quality(Stage.TESTED)
public class BasicDisjointSet<I> implements DisjointSet<BasicElement<I>, I> {

  private final Map<UUID, List<I>> storage = new HashMap<>();

  @Override
  public BasicElement<I> create(I representative) {
    UUID index;
    do {
      index = UUID.randomUUID();
    } while (storage.containsKey(index));
    final BasicElement<I> element = new BasicElement<>();
    element.setValue(representative);
    element.setIndex(index);
    storage.put(index, new ArrayList<>(Collections.singletonList(representative)));
    return element;
  }

  @Override
  public BasicElement<I> find(BasicElement<I> item) {
    if (storage.containsKey(item.getIndex())) {
      final BasicElement<I> element = new BasicElement<>();
      element.setValue(storage.get(item.getIndex()).get(0));
      element.setIndex(item.getIndex());
      return element;
    }
    throw new IllegalArgumentException("This item does not belong to the current context");
  }

  @Override
  public BasicElement<I> union(BasicElement<I> first, BasicElement<I> second) {
    if (storage.containsKey(first.getIndex()) && storage.containsKey(second.getIndex())) {
      storage.get(first.getIndex()).addAll(storage.get(second.getIndex()));
      storage.remove(second.getIndex());
      second.setIndex(first.getIndex());
      return find(first);
    }
    throw new IllegalArgumentException("This item does not belong to the current context");
  }

  @Override
  public Set<I> elements(BasicElement<I> item) {
    return storage.containsKey(item.getIndex())
        ? new HashSet<>(storage.get(item.getIndex()))
        : null;
  }

  @Override
  public Set<BasicElement<I>> sets() {
    final Set<BasicElement<I>> set = new HashSet<>();
    for (Map.Entry<UUID, List<I>> entry : storage.entrySet()) {
      final BasicElement<I> element = new BasicElement<>();
      element.setIndex(entry.getKey());
      element.setValue(entry.getValue().get(0));
      set.add(element);
    }
    return set;
  }
}
