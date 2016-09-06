package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.LinearFunction;
import com.mmnaseri.cs.clrs.common.NumberUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (9/6/16, 3:25 PM)
 */
public class LinearFunctionWriter<E extends Number> {

    private final Class<E> type;
    private final LinearFunctionRepresentationConfiguration configuration;

    public LinearFunctionWriter(Class<E> type) {
        this(type, new LinearFunctionRepresentationConfiguration());
    }

    public LinearFunctionWriter(Class<E> type, LinearFunctionRepresentationConfiguration configuration) {
        this.type = type;
        this.configuration = configuration;
    }

    public void write(LinearFunction<E> function, Writer writer) throws IOException {
        final List<E> coefficients = function.getCoefficients();
        final E offset = function.getOffset();
        int length = 0;
        int i = 0;
        final E zero = NumberUtils.zero(type);
        final E one = NumberUtils.one(type);
        final String plus;
        final String minus;
        final String times;
        final List<String> variables = new ArrayList<>();
        for (int j = 0; j < coefficients.size(); j++) {
            if (LinearFunctionRepresentationConfiguration.Variables.INDEXED.equals(configuration.getVariables())) {
                variables.add("x_" + j);
            } else if (LinearFunctionRepresentationConfiguration.Variables.ASCENDING.equals(configuration.getVariables())) {
                variables.add(String.valueOf((char) (((int) 'a') + j)));
            } else {
                variables.add(String.valueOf((char) (((int) 'z') - j)));
            }
        }
        if (configuration.getSpaceBetweenOperators()) {
            plus = " + ";
            minus = " - ";
            times = LinearFunctionRepresentationConfiguration.Multiplication.NONE.equals(configuration.getMultiplication()) ? "" : " " + configuration.getMultiplication().getRepresentation() + " ";
        } else {
            plus = "+";
            minus = "-";
            times = configuration.getMultiplication().getRepresentation();
        }
        if (configuration.getIncludeDeclaration()) {
            writer.write("f(");
            for (int j = 0; j < variables.size(); j++) {
                String variable = variables.get(j);
                if (j > 0) {
                    if (configuration.getSpaceBetweenOperators()) {
                        writer.write(", ");
                    } else {
                        writer.write(",");
                    }
                }
                writer.write(variable);
            }
            writer.write(")");
            if (configuration.getSpaceBetweenOperators()) {
                writer.write(" = ");
            } else {
                writer.write("=");
            }
        }
        for (E coefficient : coefficients) {
            if (zero.equals(coefficient) && configuration.getIgnoreZeroClauses()) {
                i++;
                continue;
            }
            if (length > 0) {
                if (NumberUtils.compare(zero, coefficient) > 0) {
                    writer.write(minus);
                    coefficient = NumberUtils.negate(coefficient);
                } else {
                    writer.write(plus);
                }
            }
            if (length == 0 && NumberUtils.compare(zero, coefficient) > 0) {
                writer.write("-");
                coefficient = NumberUtils.negate(coefficient);
            }
            if (!one.equals(coefficient)) {
                writer.write(String.valueOf(coefficient));
                writer.write(times);
            }
            writer.write(variables.get(i++));
            length++;
        }
        if (!zero.equals(offset) || !configuration.getIgnoreZeroClauses()) {
            if (NumberUtils.compare(zero, offset) > 0) {
                writer.write(minus);
            } else {
                writer.write(plus);
            }
            writer.write(String.valueOf(NumberUtils.abs(offset)));
        }
    }

    public String writeAsString(LinearFunction<E> function) {
        final StringWriter writer = new StringWriter();
        try {
            write(function, writer);
        } catch (IOException e) {
            return null;
        }
        return writer.toString();
    }

}
