package com.mmnaseri.cs.clrs.ch29.s1;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 2:58 PM)
 */
public interface LinearProgramConstraint<E extends Number> extends LinearFunction<E> {

    ConstraintType getConstraintType();

    E getValue();

    boolean isSlack();

}
