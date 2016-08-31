package com.mmnaseri.cs.clrs.ch29.s1.dsl;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/31/16, 11:08 AM)
 */
public interface ConstraintDefinition<E extends Number> {

    @SuppressWarnings("unchecked")
    ConstraintValue<E> when(E first, E... rest);

}
