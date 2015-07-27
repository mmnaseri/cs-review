package com.mmnaseri.cs.clrs.common;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Arrays;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (6/6/15, 4:27 PM)
 */
public abstract class ArrayUtils {

    @Quality(Stage.UNTESTED)
    public static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Quality(Stage.UNTESTED)
    public static <E> E[] concat(E[] left, E[] right) {
        final E[] result = Arrays.copyOf(left, left.length + right.length);
        System.arraycopy(right, 0, result, left.length, right.length);
        return result;
    }

}
