package com.mmnaseri.cs.clrs.ch29.s1.dsl;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (9/2/16, 6:50 PM)
 */
public interface Slackness<E extends Number> extends ConstraintDefinition<E> {

    ConstraintDefinition<E> andSlackness(int slackness);

}
