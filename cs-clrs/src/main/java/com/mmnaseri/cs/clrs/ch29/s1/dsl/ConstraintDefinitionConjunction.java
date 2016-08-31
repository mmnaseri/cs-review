package com.mmnaseri.cs.clrs.ch29.s1.dsl;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/31/16, 11:46 AM)
 */
public interface ConstraintDefinitionConjunction<E extends Number> extends Finalizer<E> {

    @SuppressWarnings("unchecked")
    ConstraintValue<E> and(E first, E... rest);

}
