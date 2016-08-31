package com.mmnaseri.cs.clrs.ch29.s1;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 2:59 PM)
 */
public enum ConstraintType {

    LESS_THAN_OR_EQUAL_TO("<="), GREATER_THAN_OR_EQUAL_TO(">="), EQUAL_TO("=");

    private final String representation;

    ConstraintType(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }

}
