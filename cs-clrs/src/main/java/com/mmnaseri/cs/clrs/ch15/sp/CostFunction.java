package com.mmnaseri.cs.clrs.ch15.sp;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15, 9:46 PM)
 */
public interface CostFunction {

    int getCost(EditOperationType operationType, String source, String target, int sourceIndex, int targetIndex);

}
