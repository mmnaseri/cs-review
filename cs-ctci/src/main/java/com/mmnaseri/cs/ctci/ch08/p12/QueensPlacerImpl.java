package com.mmnaseri.cs.ctci.ch08.p12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/26/16, 10:59 PM)
 */
public class QueensPlacerImpl implements QueensPlacer {

    @Override
    public List<Integer> place(int size) {
        return place(Collections.<Integer>emptyList(), size);
    }

    private List<Integer> place(List<Integer> positions, int size) {
        if (positions.size() == size) {
            return positions;
        }
        for (int i = 0; i < size; i++) {
            if (!threatens(positions, positions.size(), i)) {
                final List<Integer> soFar = new ArrayList<>(positions);
                soFar.add(i);
                final List<Integer> result = place(soFar, size);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private boolean threatens(List<Integer> positions, int row, int col) {
        for (int i = 0; i < positions.size(); i++) {
            final int j = positions.get(i);
            if (j == col || Math.abs(row - i) == Math.abs(col - j)) {
                return true;
            }
        }
        return false;
    }

}
