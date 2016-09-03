package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.LinearFunction;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConstraint;

import java.util.List;
import java.util.Set;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 3:12 PM)
 */
public class DefaultLinearProgram<E extends Number> implements LinearProgram<E> {

    private final List<LinearProgramConstraint<E>> constraints;
    private final LinearFunction<E> objective;
    private final boolean slack;
    private final int variables;
    private final int slackness;

    public DefaultLinearProgram(List<LinearProgramConstraint<E>> constraints, LinearFunction<E> objective, int slackness) {
        this.constraints = constraints;
        this.objective = objective;
        this.slackness = slackness;
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
    public List<LinearProgramConstraint<E>> getConstraints() {
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
    public int getSlackness() {
        return slackness;
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
