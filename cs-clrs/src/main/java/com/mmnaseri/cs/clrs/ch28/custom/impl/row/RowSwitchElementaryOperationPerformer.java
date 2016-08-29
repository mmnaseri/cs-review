package com.mmnaseri.cs.clrs.ch28.custom.impl.row;

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
public class RowSwitchElementaryOperationPerformer<E extends Number> implements ElementaryOperationPerformer<E, SwitchElementaryOperation<E>> {

    @Override
    public Class<? extends ElementaryOperation> getOperationType() {
        return SwitchElementaryOperation.class;
    }

    @Override
    public void perform(Matrix<E> matrix, SwitchElementaryOperation<E> operation) {
        Objects.requireNonNull(matrix);
        Objects.requireNonNull(operation);
        if (operation.getSource() < 0 || operation.getSource() >= matrix.getRows()) {
            throw new IllegalArgumentException("Source row " + operation.getSource() + " is out of range [0," + matrix.getRows() + ")");
        }
        if (operation.getTarget() < 0 || operation.getTarget() >= matrix.getRows()) {
            throw new IllegalArgumentException("Target row " + operation.getSource() + " is out of range [0," + matrix.getRows() + ")");
        }
        for (int i = 0; i < matrix.getColumns(); i++) {
            final E temp = matrix.get(operation.getSource(), i);
            matrix.set(operation.getSource(), i, matrix.get(operation.getTarget(), i));
            matrix.set(operation.getTarget(), i, temp);
        }
    }

}
