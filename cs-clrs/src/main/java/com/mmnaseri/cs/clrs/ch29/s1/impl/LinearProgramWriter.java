package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.ConstraintType;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgram;
import com.mmnaseri.cs.clrs.ch29.s1.LinearProgramConstraint;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (9/6/16, 3:45 PM)
 */
public class LinearProgramWriter<E extends Number> {

    private final LinearFunctionWriter<E> objectiveWriter;
    private final LinearFunctionWriter<E> functionWriter;
    private final LinearFunctionRepresentationConfiguration configuration;

    public LinearProgramWriter(Class<E> type) {
        this(type, new LinearFunctionRepresentationConfiguration());
    }

    public LinearProgramWriter(Class<E> type, LinearFunctionRepresentationConfiguration configuration) {
        this.configuration = configuration;
        this.functionWriter = new LinearFunctionWriter<>(type, configuration);
        this.objectiveWriter = new LinearFunctionWriter<>(type, configuration.copy().setIncludeDeclaration(false));
    }

    public void write(LinearProgram<E> program, Writer writer) throws IOException {
        final boolean pretty = configuration.getUseMathematicalSymbols();
        writer.write("z = ");
        objectiveWriter.write(program.getObjective(), writer);
        writer.write("\n\n");
        for (LinearProgramConstraint<E> constraint : program.getConstraints()) {
            functionWriter.write(constraint, writer);
            if (configuration.getSpaceBetweenOperators()) {
                writer.write(" ");
            }
            final ConstraintType constraintType = constraint.getConstraintType();
            writer.write(pretty ? constraintType.getFormatted() : constraintType.getRepresentation());
            if (configuration.getSpaceBetweenOperators()) {
                writer.write(" ");
            }
            writer.write(String.valueOf(constraint.getValue()));
            writer.write("\n");
        }
    }

    public String writeAsString(LinearProgram<E> program) {
        final StringWriter writer = new StringWriter();
        try {
            write(program, writer);
        } catch (IOException e) {
            return null;
        }
        return writer.toString();
    }

}
