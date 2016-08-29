package com.mmnaseri.cs.clrs.ch28.custom.impl.operations;

import com.mmnaseri.cs.clrs.ch28.custom.ElementaryOperation;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/29/16, 12:01 PM)
 */
public class AdditionElementaryOperation<E extends Number> implements ElementaryOperation<E> {

    private final int source;
    private final int target;
    private final E constant;

    public AdditionElementaryOperation(int source, int target, E constant) {
        this.source = source;
        this.target = target;
        this.constant = constant;
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    public E getConstant() {
        return constant;
    }

}
