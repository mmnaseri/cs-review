package com.mmnaseri.cs.clrs.ch29.s1.impl;

import com.mmnaseri.cs.clrs.ch29.s1.LinearFunction;
import com.mmnaseri.cs.clrs.common.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 3:05 PM)
 */
public class DefaultLinearFunction<E extends Number> implements LinearFunction<E> {

    private final Class<E> type;
    private final int size;
    private final List<E> coefficients;

    public DefaultLinearFunction(Class<E> type, List<E> coefficients) {
        this.type = type;
        this.size = coefficients.size();
        this.coefficients = new ArrayList<>(coefficients);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getCoefficient(int index) {
        return coefficients.get(index);
    }

    @SafeVarargs
    @Override
    public final E evaluate(E... values) {
        Objects.requireNonNull(values);
        if (values.length != size) {
            throw new IllegalArgumentException("List of values is not compatible with this function");
        }
        E value = NumberUtils.zero(type);
        for (int i = 0; i < size; i++) {
            value = NumberUtils.add(value, NumberUtils.multiply(coefficients.get(i), values[i]));
        }
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        int i = 0;
        for (E coefficient : coefficients) {
            if (NumberUtils.zero(type).equals(coefficient)) {
                i ++;
                continue;
            }
            if (builder.length() > 0) {
                builder.append(" + ");
            }
            if (!NumberUtils.one(type).equals(coefficient)) {
                if (NumberUtils.compare(NumberUtils.zero(type), coefficient) > 0) {
                    builder.append("(");
                }
                builder.append(coefficient);
                if (NumberUtils.compare(NumberUtils.zero(type), coefficient) > 0) {
                    builder.append(")");
                }
                builder.append(" * ");
            }
            builder.append("x_");
            builder.append(i ++);
        }
        return builder.toString();
    }

}
