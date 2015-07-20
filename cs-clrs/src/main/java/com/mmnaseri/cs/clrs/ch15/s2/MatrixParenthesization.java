package com.mmnaseri.cs.clrs.ch15.s2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15, 5:42 AM)
 */
public class MatrixParenthesization {

    private final Map<Integer, Map<Integer, SplitSpecification>> splits = new HashMap<>();

    public MatrixParenthesization() {
    }

    public void note(int i, int j, SplitSpecification specification) {
        if (!splits.containsKey(i)) {
            splits.put(i, new HashMap<Integer, SplitSpecification>());
        }
        splits.get(i).put(j, specification);
    }

    public SplitSpecification get(int i, int j) {
        if (!splits.containsKey(i)) {
            return null;
        }
        return splits.get(i).get(j);
    }

}
