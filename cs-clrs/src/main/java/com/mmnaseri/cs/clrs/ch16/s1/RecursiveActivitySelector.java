package com.mmnaseri.cs.clrs.ch16.s1;

import com.mmnaseri.cs.clrs.common.ArrayUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/22/15, 10:26 PM)
 */
public class RecursiveActivitySelector implements ActivitySelector {

    private Set<Integer> select(int current, IndexedActivity... activities) {
        int cursor = current + 1;
        while (cursor < activities.length && activities[cursor].getStart() < activities[current].getFinish()) {
            cursor ++;
        }
        if (cursor < activities.length) {
            final Set<Integer> rest = select(cursor, activities);
            rest.add(activities[cursor].getIndex() - 1);
            return rest;
        } else {
            return new HashSet<>();
        }
    }

    @Override
    public Set<Integer> select(Activity... activities) {
        final IndexedActivity[] indexedActivities = IndexedActivity.index(ArrayUtils.concat(new Activity[]{new Activity(0, 0)}, activities));
        Arrays.sort(indexedActivities);
        return select(0, indexedActivities);
    }

}
