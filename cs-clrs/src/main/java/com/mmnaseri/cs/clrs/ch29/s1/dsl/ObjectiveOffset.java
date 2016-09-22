package com.mmnaseri.cs.clrs.ch29.s1.dsl;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (9/6/16, 3:16 PM)
 */
public interface ObjectiveOffset<E extends Number> extends ConstraintDefinition<E> {

    ConstraintDefinition<E> offsetBy(E offset);

}
