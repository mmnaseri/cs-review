package com.mmnaseri.cs.clrs.ch28.custom;

import com.mmnaseri.cs.clrs.common.Matrix;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/29/16, 12:10 PM)
 */
public interface InvertibleMatrixGenerator<E extends Number> {

    Matrix<E> generate(int size);

}
