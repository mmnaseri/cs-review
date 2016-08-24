package com.mmnaseri.cs.clrs.common;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 10:57 PM)
 */
public abstract class NumberUtils {

    @SuppressWarnings("unchecked")
    @Quality(Stage.UNTESTED)
    public static <E extends Number> E add(E first, E second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        if (first instanceof Float && second instanceof Float) {
            final Float firstValue = (Float) first;
            final Float secondValue = (Float) second;
            return (E) (Float) (firstValue + secondValue);
        } else if (first instanceof Long && second instanceof Long) {
            final Long firstValue = (Long) first;
            final Long secondValue = (Long) second;
            return (E) (Long) (firstValue + secondValue);
        } else if (first instanceof Double && second instanceof Double) {
            final Double firstValue = (Double) first;
            final Double secondValue = (Double) second;
            return (E) (Double) (firstValue + secondValue);
        } else if (first instanceof Short && second instanceof Short) {
            final Short firstValue = (Short) first;
            final Short secondValue = (Short) second;
            return (E) (Short) ((Integer) (firstValue + secondValue)).shortValue();
        } else if (first instanceof Byte && second instanceof Byte) {
            final Byte firstValue = (Byte) first;
            final Byte secondValue = (Byte) second;
            return (E) (Byte) ((Integer) (firstValue + secondValue)).byteValue();
        } else if (first instanceof Integer && second instanceof Integer) {
            final Integer firstValue = (Integer) first;
            final Integer secondValue = (Integer) second;
            return (E) (Integer) (firstValue + secondValue);
        } else if (first instanceof AtomicLong && second instanceof AtomicLong) {
            final AtomicLong firstValue = (AtomicLong) first;
            final AtomicLong secondValue = (AtomicLong) second;
            return (E) new AtomicLong(firstValue.get() + secondValue.get());
        } else if (first instanceof AtomicInteger && second instanceof AtomicInteger) {
            final AtomicInteger firstValue = (AtomicInteger) first;
            final AtomicInteger secondValue = (AtomicInteger) second;
            return (E) new AtomicInteger(firstValue.get() + secondValue.get());
        } else if (first instanceof BigDecimal && second instanceof BigDecimal) {
            final BigDecimal firstValue = (BigDecimal) first;
            final BigDecimal secondValue = (BigDecimal) second;
            return (E) firstValue.add(secondValue);
        } else if (first instanceof BigInteger && second instanceof BigInteger) {
            final BigInteger firstValue = (BigInteger) first;
            final BigInteger secondValue = (BigInteger) second;
            return (E) firstValue.add(secondValue);
        } else {
            throw new UnsupportedOperationException("Unsupported number type");
        }
    }
    
    @SuppressWarnings("unchecked")
    @Quality(Stage.UNTESTED)
    public static <E extends Number> E subtract(E first, E second) {
        if (first instanceof Float && second instanceof Float) {
            final Float firstValue = (Float) first;
            final Float secondValue = (Float) second;
            return (E) (Float) (firstValue - secondValue);
        } else if (first instanceof Long && second instanceof Long) {
            final Long firstValue = (Long) first;
            final Long secondValue = (Long) second;
            return (E) (Long) (firstValue - secondValue);
        } else if (first instanceof Double && second instanceof Double) {
            final Double firstValue = (Double) first;
            final Double secondValue = (Double) second;
            return (E) (Double) (firstValue - secondValue);
        } else if (first instanceof Short && second instanceof Short) {
            final Short firstValue = (Short) first;
            final Short secondValue = (Short) second;
            return (E) (Short) ((Integer) (firstValue - secondValue)).shortValue();
        } else if (first instanceof Byte && second instanceof Byte) {
            final Byte firstValue = (Byte) first;
            final Byte secondValue = (Byte) second;
            return (E) (Byte) ((Integer) (firstValue - secondValue)).byteValue();
        } else if (first instanceof Integer && second instanceof Integer) {
            final Integer firstValue = (Integer) first;
            final Integer secondValue = (Integer) second;
            return (E) (Integer) (firstValue - secondValue);
        } else if (first instanceof AtomicLong && second instanceof AtomicLong) {
            final AtomicLong firstValue = (AtomicLong) first;
            final AtomicLong secondValue = (AtomicLong) second;
            return (E) new AtomicLong(firstValue.get() - secondValue.get());
        } else if (first instanceof AtomicInteger && second instanceof AtomicInteger) {
            final AtomicInteger firstValue = (AtomicInteger) first;
            final AtomicInteger secondValue = (AtomicInteger) second;
            return (E) new AtomicInteger(firstValue.get() - secondValue.get());
        } else if (first instanceof BigDecimal && second instanceof BigDecimal) {
            final BigDecimal firstValue = (BigDecimal) first;
            final BigDecimal secondValue = (BigDecimal) second;
            return (E) firstValue.subtract(secondValue);
        } else if (first instanceof BigInteger && second instanceof BigInteger) {
            final BigInteger firstValue = (BigInteger) first;
            final BigInteger secondValue = (BigInteger) second;
            return (E) firstValue.subtract(secondValue);
        } else {
            throw new UnsupportedOperationException("Unsupported number type");
        }
    }
    
    @SuppressWarnings("unchecked")
    @Quality(Stage.UNTESTED)
    public static <E extends Number> E multiply(E first, E second) {
        if (first instanceof Float && second instanceof Float) {
            final Float firstValue = (Float) first;
            final Float secondValue = (Float) second;
            return (E) (Float) (firstValue * secondValue);
        } else if (first instanceof Long && second instanceof Long) {
            final Long firstValue = (Long) first;
            final Long secondValue = (Long) second;
            return (E) (Long) (firstValue * secondValue);
        } else if (first instanceof Double && second instanceof Double) {
            final Double firstValue = (Double) first;
            final Double secondValue = (Double) second;
            return (E) (Double) (firstValue * secondValue);
        } else if (first instanceof Short && second instanceof Short) {
            final Short firstValue = (Short) first;
            final Short secondValue = (Short) second;
            return (E) (Short) ((Integer) (firstValue * secondValue)).shortValue();
        } else if (first instanceof Byte && second instanceof Byte) {
            final Byte firstValue = (Byte) first;
            final Byte secondValue = (Byte) second;
            return (E) (Byte) ((Integer) (firstValue * secondValue)).byteValue();
        } else if (first instanceof Integer && second instanceof Integer) {
            final Integer firstValue = (Integer) first;
            final Integer secondValue = (Integer) second;
            return (E) (Integer) (firstValue * secondValue);
        } else if (first instanceof AtomicLong && second instanceof AtomicLong) {
            final AtomicLong firstValue = (AtomicLong) first;
            final AtomicLong secondValue = (AtomicLong) second;
            return (E) new AtomicLong(firstValue.get() * secondValue.get());
        } else if (first instanceof AtomicInteger && second instanceof AtomicInteger) {
            final AtomicInteger firstValue = (AtomicInteger) first;
            final AtomicInteger secondValue = (AtomicInteger) second;
            return (E) new AtomicInteger(firstValue.get() * secondValue.get());
        } else if (first instanceof BigDecimal && second instanceof BigDecimal) {
            final BigDecimal firstValue = (BigDecimal) first;
            final BigDecimal secondValue = (BigDecimal) second;
            return (E) firstValue.multiply(secondValue);
        } else if (first instanceof BigInteger && second instanceof BigInteger) {
            final BigInteger firstValue = (BigInteger) first;
            final BigInteger secondValue = (BigInteger) second;
            return (E) firstValue.multiply(secondValue);
        } else {
            throw new UnsupportedOperationException("Unsupported number type");
        }
    }

    @SuppressWarnings("unchecked")
    @Quality(Stage.UNTESTED)
    public static <E extends Number> E divide(E first, E second) {
        if (first instanceof Float && second instanceof Float) {
            final Float firstValue = (Float) first;
            final Float secondValue = (Float) second;
            return (E) (Float) (firstValue / secondValue);
        } else if (first instanceof Long && second instanceof Long) {
            final Long firstValue = (Long) first;
            final Long secondValue = (Long) second;
            return (E) (Long) (firstValue / secondValue);
        } else if (first instanceof Double && second instanceof Double) {
            final Double firstValue = (Double) first;
            final Double secondValue = (Double) second;
            return (E) (Double) (firstValue / secondValue);
        } else if (first instanceof Short && second instanceof Short) {
            final Short firstValue = (Short) first;
            final Short secondValue = (Short) second;
            return (E) (Short) ((Integer) (firstValue / secondValue)).shortValue();
        } else if (first instanceof Byte && second instanceof Byte) {
            final Byte firstValue = (Byte) first;
            final Byte secondValue = (Byte) second;
            return (E) (Byte) ((Integer) (firstValue / secondValue)).byteValue();
        } else if (first instanceof Integer && second instanceof Integer) {
            final Integer firstValue = (Integer) first;
            final Integer secondValue = (Integer) second;
            return (E) (Integer) (firstValue / secondValue);
        } else if (first instanceof AtomicLong && second instanceof AtomicLong) {
            final AtomicLong firstValue = (AtomicLong) first;
            final AtomicLong secondValue = (AtomicLong) second;
            return (E) new AtomicLong(firstValue.get() / secondValue.get());
        } else if (first instanceof AtomicInteger && second instanceof AtomicInteger) {
            final AtomicInteger firstValue = (AtomicInteger) first;
            final AtomicInteger secondValue = (AtomicInteger) second;
            return (E) new AtomicInteger(firstValue.get() / secondValue.get());
        } else if (first instanceof BigDecimal && second instanceof BigDecimal) {
            final BigDecimal firstValue = (BigDecimal) first;
            final BigDecimal secondValue = (BigDecimal) second;
            return (E) firstValue.divide(secondValue, BigDecimal.ROUND_HALF_EVEN);
        } else if (first instanceof BigInteger && second instanceof BigInteger) {
            final BigInteger firstValue = (BigInteger) first;
            final BigInteger secondValue = (BigInteger) second;
            return (E) firstValue.divide(secondValue);
        } else {
            throw new UnsupportedOperationException("Unsupported number type");
        }
    }

}
