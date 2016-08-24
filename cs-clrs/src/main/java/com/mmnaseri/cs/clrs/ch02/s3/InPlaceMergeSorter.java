package com.mmnaseri.cs.clrs.ch02.s3;

import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * todo the implementation is non-trivial and should be studied before being attempted
 * http://stackoverflow.com/questions/2571049/how-to-sort-in-place-using-the-merge-sort-algorithm
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 3:11 AM)
 */
@Quality(Stage.INCOMPLETE)
public class InPlaceMergeSorter<E extends Comparable<E>> implements Sorter<E> {

    @Override
    public void sort(E[] items) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
