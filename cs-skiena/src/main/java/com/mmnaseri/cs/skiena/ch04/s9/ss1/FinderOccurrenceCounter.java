package com.mmnaseri.cs.skiena.ch04.s9.ss1;

import com.mmnaseri.cs.clrs.common.Finder;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:22 AM)
 */
@Quality(Stage.TESTED)
public class FinderOccurrenceCounter<E extends Comparable<E>> implements OccurrenceCounter<E> {

    private final Finder<E> finder;

    public FinderOccurrenceCounter(Finder<E> finder) {
        this.finder = finder;
    }

    @Complexity(value = "O(k+f(n))",explanation = "f(n) is the complexity of the finder used, and `k` is the number of the occurrences of `item`")
    @Override
    public int count(E[] array, E item) {
        final int location = finder.find(item, array);
        if (location == -1) {
            return 0;
        }
        int from = location;
        while (from > 0 && array[from - 1].equals(item)) {
            from --;
        }
        int to = location;
        while (to < array.length - 1 && array[to + 1].equals(item)) {
            to ++;
        }
        return to - from + 1;
    }
}
