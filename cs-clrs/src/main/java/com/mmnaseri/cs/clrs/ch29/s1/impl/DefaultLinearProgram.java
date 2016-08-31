package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.LinearFunction;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConstraint;

import java.util.Set;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 3:12 PM)
 */
public class DefaultLinearProgram<E extends Number> implements LinearProgram<E> {

    private final Set<LinearProgramConstraint<E>> constraints;
    private final LinearFunction<E> objective;
    private final boolean slack;
    private final int variables;

    public DefaultLinearProgram(Set<LinearProgramConstraint<E>> constraints, LinearFunction<E> objective) {
        this.constraints = constraints;
        this.objective = objective;
        boolean slack = false;
        int variables = objective.size();
        if (variables == 0) {
            throw new IllegalArgumentException("Objective function cannot have zero variables");
        }
        if (constraints.isEmpty()) {
            throw new IllegalStateException("Unsolvable program");
        }
        for (LinearProgramConstraint<E> constraint : constraints) {
            slack = slack || constraint.isSlack();
        }
        for (LinearProgramConstraint<E> constraint : constraints) {
            if (variables != constraint.size()) {
                throw new IllegalArgumentException("Constraints must all have the same number of variables");
            }
        }
        this.slack = slack;
        this.variables = variables;
    }

    @Override
    public int variables() {
        return variables;
    }

    @Override
    public Set<LinearProgramConstraint<E>> getConstraints() {
        return constraints;
    }

    @Override
    public LinearFunction<E> getObjective() {
        return objective;
    }

    @Override
    public boolean isSlack() {
        return slack;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("z = ");
        builder.append(objective);
        builder.append("\n");
        for (LinearProgramConstraint<E> constraint : constraints) {
            builder.append("\n").append(constraint);
        }
        return builder.append("\n").toString();
    }

}

/**
 * withObjective(1, 2, 3)
 *  .when(2, 3, 4).isGreaterThan()
 *  .and(...).is()
 *  .and(...).isLessThan()
 *  .maximize();
 */
