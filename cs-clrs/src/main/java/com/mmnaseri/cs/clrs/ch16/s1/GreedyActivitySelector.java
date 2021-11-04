package com.mmnaseri.cs.clrs.ch16.s1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/22/15, 10:48 PM)
 */
public class GreedyActivitySelector implements ActivitySelector {

  @Override
  public Set<Integer> select(Activity... activities) {
    final IndexedActivity[] indexedActivities = IndexedActivity.index(activities);
    Arrays.sort(indexedActivities);
    final Set<Integer> indices = new HashSet<>();
    indices.add(indexedActivities[0].getIndex());
    int current = 0;
    for (int i = 1; i < indexedActivities.length; i++) {
      if (indexedActivities[i].getStart() >= indexedActivities[current].getFinish()) {
        indices.add(indexedActivities[i].getIndex());
        current = i;
      }
    }
    return indices;
  }
}
