package com.mmnaseri.cs.skiena.ch04.s9;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 10:55 AM)
 */
@Quality(Stage.TESTED)
public class NaiveOccurrenceCounter<E> implements OccurrenceCounter<E> {

    @Override
    public int count(E[] array, E item) {
        int count = 0;
        for (E e : array) {
            if (e.equals(item)) {
                count ++;
            }
        }
        return count;
    }
}
