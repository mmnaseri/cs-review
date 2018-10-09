package com.mmnaseri.cs.clrs.ch15.sp;

/**
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (05/10/2018)
 */
public class ExpensiveReplacementCostFunction implements CostFunction {


    @Override
    public int getCost(EditOperationType operationType, String source, String target, int sourceIndex, int targetIndex) {
        if (operationType == EditOperationType.COPY) {
            return 0;
        } else if (operationType == EditOperationType.INSERT) {
            return 20;
        } else if (operationType == EditOperationType.DELETE) {
            return 19;
        } else if (operationType == EditOperationType.TWIDDLE) {
            return 10;
        } else if (operationType == EditOperationType.REPLACE) {
            return 40;
        }
        throw new IllegalArgumentException(operationType + " operationtype is not valid");
    }

}
