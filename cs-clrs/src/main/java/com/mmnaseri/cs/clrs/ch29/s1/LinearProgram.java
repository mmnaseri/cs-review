package com.mmnaseri.cs.clrs.ch29.s1;

import java.util.Set;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 3:08 PM)
 */
public interface LinearProgram<E extends Number> {

    int variables();

    Set<LinearProgramConstraint<E>> getConstraints();

    LinearFunction<E> getObjective();

    boolean isSlack();

}
