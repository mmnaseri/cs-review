package com.mmnaseri.cs.clrs.ch29.s1.impl;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (9/6/16, 3:31 PM)
 */
public class LinearFunctionRepresentationConfiguration {

    private Boolean spaceBetweenOperators = true;
    private Boolean includeDeclaration = false;
    private Boolean ignoreZeroClauses = true;
    private Variables variables = Variables.INDEXED;
    private Multiplication multiplication = Multiplication.ASTERISK;
    private Boolean useMathematicalSymbols = false;


    public Boolean getIgnoreZeroClauses() {
        return ignoreZeroClauses;
    }

    public LinearFunctionRepresentationConfiguration setIgnoreZeroClauses(Boolean ignoreZeroClauses) {
        this.ignoreZeroClauses = ignoreZeroClauses;
        return this;
    }

    public Multiplication getMultiplication() {
        return multiplication;
    }

    public LinearFunctionRepresentationConfiguration setMultiplication(Multiplication multiplication) {
        this.multiplication = multiplication;
        return this;
    }

    public Boolean getSpaceBetweenOperators() {
        return spaceBetweenOperators;
    }

    public LinearFunctionRepresentationConfiguration setSpaceBetweenOperators(Boolean spaceBetweenOperators) {
        this.spaceBetweenOperators = spaceBetweenOperators;
        return this;
    }

    public Boolean getIncludeDeclaration() {
        return includeDeclaration;
    }

    public LinearFunctionRepresentationConfiguration setIncludeDeclaration(Boolean includeDeclaration) {
        this.includeDeclaration = includeDeclaration;
        return this;
    }

    public Variables getVariables() {
        return variables;
    }

    public LinearFunctionRepresentationConfiguration setVariables(Variables variables) {
        this.variables = variables;
        return this;
    }

    public LinearFunctionRepresentationConfiguration copy() {
        return new LinearFunctionRepresentationConfiguration()
                .setIgnoreZeroClauses(ignoreZeroClauses)
                .setIncludeDeclaration(includeDeclaration)
                .setMultiplication(multiplication)
                .setSpaceBetweenOperators(spaceBetweenOperators)
                .setVariables(variables);
    }

    public Boolean getUseMathematicalSymbols() {
        return useMathematicalSymbols;
    }

    public LinearFunctionRepresentationConfiguration setUseMathematicalSymbols(Boolean useMathematicalSymbols) {
        this.useMathematicalSymbols = useMathematicalSymbols;
        return this;
    }

    public enum Variables {

        INDEXED, ASCENDING, DESCENDING

    }

    public enum Multiplication {

        ASTERISK("*"), DOT("."), TIMES("\u2A09"), NONE("");

        private final String representation;

        Multiplication(String representation) {
            this.representation = representation;
        }

        public String getRepresentation() {
            return representation;
        }

    }

}
