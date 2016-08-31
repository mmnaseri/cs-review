package com.mmnaseri.cs.clrs.ch29.s1.dsl;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/31/16, 11:45 AM)
 */
public interface ConstraintValue<E extends Number> {

    ConstraintDefinitionConjunction<E> is(E value);

    ConstraintDefinitionConjunction<E> isGreaterThan(E value);

    ConstraintDefinitionConjunction<E> isLessThan(E value);

}
