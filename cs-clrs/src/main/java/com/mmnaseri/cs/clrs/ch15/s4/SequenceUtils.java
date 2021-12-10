package com.mmnaseri.cs.clrs.ch15.s4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15)
 */
public abstract class SequenceUtils {

  public static <E> List<List<E>> subSequences(List<E> sequence) {
    final ArrayList<List<E>> state = new ArrayList<>();
    state.add(new ArrayList<>());
    return subSequences(sequence, state);
  }

  private static <E> List<List<E>> subSequences(List<E> sequence, List<List<E>> state) {
    if (sequence.isEmpty()) {
      return state;
    }
    final E first = sequence.get(0);
    final List<E> rest;
    if (sequence.size() > 1) {
      rest = sequence.subList(1, sequence.size());
    } else {
      rest = Collections.emptyList();
    }
    final ArrayList<List<E>> next = new ArrayList<>();
    for (List<E> list : state) {
      final ArrayList<E> with = new ArrayList<>(list);
      final ArrayList<E> without = new ArrayList<>(list);
      with.add(first);
      next.add(with);
      next.add(without);
    }
    return subSequences(rest, next);
  }
}
