package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.impl.AbstractImmutableMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/23/16, 8:59 PM)
 */
@Quality(Stage.TESTED)
public class PermutationMatrix extends AbstractImmutableMatrix<Double> {

    private static final double ON = 1.0;
    private static final int OFF = 0;
    private final List<Integer> permutation;

    public PermutationMatrix(List<Integer> permutation) {
        if (permutation == null) {
            throw new IllegalArgumentException("Permutation cannot be null");
        }
        if (permutation.isEmpty()) {
            throw new IllegalArgumentException("Cannot create a permutation matrix with an empty permutation");
        }
        if (new HashSet<>(permutation).size() != permutation.size()) {
            throw new IllegalArgumentException("Permutation cannot contain duplicate values");
        }
        for (Integer index : permutation) {
            if (index < 0 || index >= permutation.size()) {
                throw new IllegalArgumentException("Permutation indices must be within range");
            }
        }
        this.permutation = new ArrayList<>(permutation);
    }

    @Override
    public int getRows() {
        return permutation.size();
    }

    @Override
    public int getColumns() {
        return permutation.size();
    }

    @Override
    protected Double read(int row, int col) {
        return col == permutation.get(row) ? ON : OFF;
    }

}
