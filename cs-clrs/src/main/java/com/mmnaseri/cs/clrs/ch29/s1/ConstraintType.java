package com.mmnaseri.cs.clrs.ch29.s1;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 2:59 PM)
 */
public enum ConstraintType {

    LESS_THAN_OR_EQUAL_TO("<=", "\u2264"), GREATER_THAN_OR_EQUAL_TO(">=", "\u2265"), EQUAL_TO("=", "=");

    private final String representation;
    private final String formatted;

    ConstraintType(String representation, String formatted) {
        this.representation = representation;
        this.formatted = formatted;
    }

    public String getRepresentation() {
        return representation;
    }

    public String getFormatted() {
        return formatted;
    }

}
