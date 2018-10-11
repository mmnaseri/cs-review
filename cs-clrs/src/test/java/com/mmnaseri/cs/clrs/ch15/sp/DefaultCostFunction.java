package com.mmnaseri.cs.clrs.ch15.sp;

/**
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (05/10/2018)
 */
public class DefaultCostFunction implements CostFunction {


    @Override
    public int getCost(EditOperationType operationType, String source, String target, int sourceIndex, int targetIndex) {
        if (operationType == EditOperationType.COPY) {
            return 0;
        } else if (operationType == EditOperationType.INSERT) {
            return 40;
        } else if (operationType == EditOperationType.DELETE) {
            return 40;
        } else if (operationType == EditOperationType.TWIDDLE) {
            return 10;
        } else if (operationType == EditOperationType.REPLACE) {
            return 30;
        }
        throw new IllegalArgumentException(operationType + " operationtype is not valid");
    }

}
