package com.mmnaseri.cs.clrs.ch28.custom.impl.column;

import com.mmnaseri.cs.clrs.ch28.custom.ElementaryOperation;
import com.mmnaseri.cs.clrs.ch28.custom.ElementaryOperationPerformer;
import com.mmnaseri.cs.clrs.ch28.custom.impl.operations.SwitchElementaryOperation;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Objects;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/29/16, 11:44 AM)
 */
@Quality(Stage.UNTESTED)
public class ColumnSwitchElementaryOperationPerformer<E extends Number> implements ElementaryOperationPerformer<E, SwitchElementaryOperation<E>> {

    @Override
    public Class<? extends ElementaryOperation> getOperationType() {
        return SwitchElementaryOperation.class;
    }

    @Override
    public void perform(Matrix<E> matrix, SwitchElementaryOperation<E> operation) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(operation);
        if (operation.getSource() < 0 || operation.getSource() >= matrix.getColumns()) {
            throw new IllegalArgumentException("Source column " + operation.getSource() + " is out of range [0," + matrix.getColumns() + ")");
        }
        if (operation.getTarget() < 0 || operation.getTarget() >= matrix.getColumns()) {
            throw new IllegalArgumentException("Target column " + operation.getSource() + " is out of range [0," + matrix.getColumns() + ")");
        }
        for (int i = 0; i < matrix.getRows(); i++) {
            final E temp = matrix.get(i, operation.getSource());
            matrix.set(i, operation.getSource(), matrix.get(i, operation.getTarget()));
            matrix.set(i, operation.getTarget(), temp);
        }
    }

}
