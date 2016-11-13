package com.mmnaseri.cs.skiena.ch04.s9.ss2;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 1:10 PM)
 */
@Quality(Stage.TESTED)
public class NaivePrefixLengthFinder<E> implements PrefixLengthFinder<E> {

    @Complexity(value = "O(k)", explanation = "`k` is the length of the prefix")
    @Override
    public int findPrefixLength(E[] array) {
        if (array.length == 0) {
            return 0;
        }
        int count = 1;
        final E first = array[0];
        while (count < array.length - 1 && array[count].equals(first)) {
            count ++;
        }
        return count;
    }

}
