package com.mmnaseri.cs.clrs.ch29.s1;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/31/16, 10:38 AM)
 */
public interface LinearProgramConverter<E extends Number> {

    LinearProgram<E> convert(LinearProgram<E> original);

}
