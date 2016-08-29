package com.mmnaseri.cs.clrs.ch28.custom;

import com.mmnaseri.cs.clrs.common.Matrix;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/29/16, 11:42 AM)
 */
public interface ElementaryOperationPerformer<E extends Number, O extends ElementaryOperation<E>> {

    Class<? extends ElementaryOperation> getOperationType();

    void perform(Matrix<E> matrix, O operation);

}
