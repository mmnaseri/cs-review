package com.mmnaseri.cs.clrs.ch28.custom;

import com.mmnaseri.cs.clrs.common.Matrix;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 11:52 AM)
 */
public interface DeterminantCalculator<E extends Number> {

    E determinant(Matrix<E> matrix);

}
