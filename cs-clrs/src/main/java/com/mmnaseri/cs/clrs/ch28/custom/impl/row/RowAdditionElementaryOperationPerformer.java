package com.mmnaseri.cs.clrs.ch28.custom.impl.row;

import com.mmnaseri.cs.clrs.ch28.custom.ElementaryOperation;
import com.mmnaseri.cs.clrs.ch28.custom.ElementaryOperationPerformer;
import com.mmnaseri.cs.clrs.ch28.custom.impl.operations.AdditionElementaryOperation;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Objects;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/29/16, 12:02 PM)
 */
@Quality(Stage.UNTESTED)
public class RowAdditionElementaryOperationPerformer<E extends Number> implements ElementaryOperationPerformer<E, AdditionElementaryOperation<E>> {

    @Override
    public Class<? extends ElementaryOperation> getOperationType() {
        return AdditionElementaryOperation.class;
    }

    @Override
    public void perform(Matrix<E> matrix, AdditionElementaryOperation<E> operation) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(operation);
        Objects.requireNonNull(operation.getConstant());
        if (operation.getTarget() < 0 || operation.getTarget() >= matrix.getRows()) {
            throw new IllegalArgumentException("Target row is out of its bounds");
        }
        if (operation.getSource() < 0 || operation.getSource() >= matrix.getRows()) {
            throw new IllegalArgumentException("Source row is out of its bounds");
        }
        if (operation.getSource() == operation.getTarget()) {
            throw new IllegalArgumentException("Source and target cannot be the same");
        }
        for (int i = 0; i < matrix.getColumns(); i++) {
            matrix.set(operation.getTarget(), i, NumberUtils.add(matrix.get(operation.getTarget(), i), NumberUtils.multiply(matrix.get(operation.getSource(), i), operation.getConstant())));
        }
    }

}
