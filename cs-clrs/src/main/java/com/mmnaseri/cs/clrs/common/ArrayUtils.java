package com.mmnaseri.cs.clrs.common;

import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

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

}
