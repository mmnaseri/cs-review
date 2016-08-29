package com.mmnaseri.cs.clrs.ch28.custom.impl.operations;

import com.mmnaseri.cs.clrs.ch28.custom.ElementaryOperation;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/29/16, 11:45 AM)
 */
public class SwitchElementaryOperation<E extends Number> implements ElementaryOperation<E> {

    private final int source;
    private final int target;

    public SwitchElementaryOperation(int source, int target) {
        this.source = source;
        this.target = target;
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

}
