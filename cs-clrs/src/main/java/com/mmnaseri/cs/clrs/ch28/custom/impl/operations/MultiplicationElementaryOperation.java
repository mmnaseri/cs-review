package com.mmnaseri.cs.clrs.ch28.custom.impl.operations;

import com.mmnaseri.cs.clrs.ch28.custom.ElementaryOperation;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/29/16, 11:50 AM)
 */
public class MultiplicationElementaryOperation<E extends Number> implements ElementaryOperation<E> {

    private final E constant;
    private final int target;

    public MultiplicationElementaryOperation(E constant, int target) {
        this.constant = constant;
        this.target = target;
    }

    public E getConstant() {
        return constant;
    }

    public int getTarget() {
        return target;
    }

}
