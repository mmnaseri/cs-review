package com.mmnaseri.cs.clrs.ch29.s1.dsl;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (9/6/16, 3:18 PM)
 */
public interface ConstraintOffset<E extends Number> extends ConstraintValue<E> {

    ConstraintValue<E> offsetBy(E offset);

}
