package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.ConstraintType;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConstraint;

import java.util.List;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 2:59 PM)
 */
public class DefaultLinearProgramConstraint<E extends Number> extends DefaultLinearFunction<E> implements LinearProgramConstraint<E> {

    private static final String REPRESENTATION = "%s %s %s";
    private final ConstraintType constraintType;
    private final E value;

    public DefaultLinearProgramConstraint(Class<E> type, List<E> coefficients, ConstraintType constraintType, E value) {
        super(type, coefficients);
        this.constraintType = constraintType;
        this.value = value;
    }

    @Override
    public ConstraintType getConstraintType() {
        return constraintType;
    }

    @Override
    public E getValue() {
        return value;
    }

    @Override
    public boolean isSlack() {
        return ConstraintType.EQUAL_TO.equals(constraintType);
    }

    @Override
    public String toString() {
        final String leftHand = super.toString();
        return String.format(REPRESENTATION, leftHand, constraintType, value);
    }

}
