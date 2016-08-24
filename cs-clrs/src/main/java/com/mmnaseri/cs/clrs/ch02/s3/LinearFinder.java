package com.mmnaseri.cs.clrs.ch02.s3;

import com.mmnaseri.cs.clrs.common.Finder;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/11/15, 1:23 PM)
 */
@Quality(Stage.TESTED)
public class LinearFinder<E> implements Finder<E> {

    /**
     * This  method will find a given needle in a haystack array by looking at each and every item possible
     * @param needle    the item to look for
     * @param items     the array of items to examine
     * @return the position of the item in the array or {@literal -1} if it is not found
     */
    @SafeVarargs
    @Override
    @Complexity("O(n)")
    public final int find(E needle, E... items) {
        for (int i = 0; i < items.length; i++) {
            E item = items[i];
            if (needle.equals(item)) {
                return i;
            }
        }
        return -1;
    }

}
