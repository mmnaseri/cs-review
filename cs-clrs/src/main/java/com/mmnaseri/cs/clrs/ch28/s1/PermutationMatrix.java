package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.NumberUtils;
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
public class PermutationMatrix<E extends Number> extends AbstractImmutableMatrix<E> {

    private final List<Integer> permutation;
    private final E zero;
    private final E one;

    public PermutationMatrix(Class<E> type, List<Integer> permutation) {
        zero = NumberUtils.zero(type);
        one = NumberUtils.one(type);
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
    protected E read(int row, int col) {
        return col == permutation.get(row) ? one : zero;
    }

}
