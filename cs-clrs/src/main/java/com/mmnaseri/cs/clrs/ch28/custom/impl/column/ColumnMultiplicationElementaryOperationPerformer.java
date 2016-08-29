package com.mmnaseri.cs.clrs.ch28.custom.impl.column;

import com.mmnaseri.cs.clrs.ch28.custom.ElementaryOperation;
import com.mmnaseri.cs.clrs.ch28.custom.ElementaryOperationPerformer;
import com.mmnaseri.cs.clrs.ch28.custom.impl.operations.MultiplicationElementaryOperation;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Objects;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/29/16, 11:53 AM)
 */
@Quality(Stage.UNTESTED)
public class ColumnMultiplicationElementaryOperationPerformer<E extends Number> implements ElementaryOperationPerformer<E, MultiplicationElementaryOperation<E>> {

    @Override
    public Class<? extends ElementaryOperation> getOperationType() {
        return MultiplicationElementaryOperation.class;
    }

    @Override
    public void perform(Matrix<E> matrix, MultiplicationElementaryOperation<E> operation) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(operation);
        if (operation.getTarget() < 0 || operation.getTarget() >= matrix.getColumns()) {
            throw new IllegalArgumentException("Target column is out of its bounds");
        }
        if (NumberUtils.zero(operation.getConstant().getClass()).equals(operation.getConstant())) {
            throw new IllegalArgumentException("Constant cannot be zero");
        }
        for (int i = 0; i < matrix.getRows(); i++) {
            matrix.set(i, operation.getTarget(), NumberUtils.multiply(operation.getConstant(), matrix.get(i, operation.getTarget())));
        }
    }

}
