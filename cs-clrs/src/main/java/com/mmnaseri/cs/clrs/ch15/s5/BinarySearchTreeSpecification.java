package com.mmnaseri.cs.clrs.ch15.s5;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/21/15, 9:26 PM)
 */
public class BinarySearchTreeSpecification {

    private final Map<Integer, Map<Integer, SplitSpecification>> splits = new HashMap<>();

    public SplitSpecification get(int i, int j) {
        if (!splits.containsKey(i)) {
            return null;
        }
        return splits.get(i).get(j);
    }

    public void set(int i, int j, SplitSpecification specification) {
        if (!splits.containsKey(i)) {
            splits.put(i, new HashMap<Integer, SplitSpecification>());
        }
        splits.get(i).put(j, specification);
    }

}
