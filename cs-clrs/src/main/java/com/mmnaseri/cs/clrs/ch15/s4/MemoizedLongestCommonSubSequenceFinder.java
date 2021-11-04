package com.mmnaseri.cs.clrs.ch15.s4;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15)
 */
@Quality(Stage.TESTED)
public class MemoizedLongestCommonSubSequenceFinder<E>
    implements LongestCommonSubSequenceFinder<E> {

  @Override
  public List<E> find(List<E> first, List<E> second) {
    if (first == null || second == null) {
      return null;
    }
    return find(new HashMap<>(), first, second, first.size() - 1, second.size() - 1);
  }

  private List<E> find(
      Map<Integer, Map<Integer, List<E>>> memory,
      List<E> first,
      List<E> second,
      int firstCursor,
      int secondCursor) {
    if (firstCursor < 0 || secondCursor < 0) {
      return Collections.emptyList();
    }
    if (memory.containsKey(firstCursor) && memory.get(firstCursor).containsKey(secondCursor)) {
      return memory.get(firstCursor).get(secondCursor);
    }
    final List<E> result;
    final E firstItem = first.get(firstCursor);
    final E secondItem = second.get(secondCursor);
    if (firstItem == null && secondItem == null
        || firstItem != null && firstItem.equals(secondItem)) {
      result = new ArrayList<>(find(memory, first, second, firstCursor - 1, secondCursor - 1));
      result.add(firstItem);
    } else {
      final List<E> one = find(memory, first, second, firstCursor, secondCursor - 1);
      final List<E> two = find(memory, first, second, firstCursor - 1, secondCursor);
      if (one.size() > two.size()) {
        result = one;
      } else {
        result = two;
      }
    }
    if (!memory.containsKey(firstCursor)) {
      memory.put(firstCursor, new HashMap<>());
    }
    memory.get(firstCursor).put(secondCursor, result);
    return result;
  }
}
