package com.mmnaseri.cs.skiena.ch04.s9.ss1;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:29 AM)
 */
@Quality(Stage.TESTED)
public class BinarySearchOccurrenceCounter<E extends Comparable<E>> implements OccurrenceCounter<E> {

    @Complexity("O(log(n))")
    @Override
    public int count(E[] array, E item) {
        //first, let's find the first occurrence -- O(log(n))
        final int left = left(array, item, 0, array.length - 1);
        if (left == -1) { //if the item wasn't in the array, the count is `0`
            return 0;
        }
        //now, let's find the last one (and we only need to look at the subarray starting at the first occurrence) -- O(log(n))
        final int right = right(array, item, left, array.length - 1);
        return right - left + 1;
    }

    private int left(E[] array, E item, int low, int high) {
        if (high >= low) {
            final int middle = (low + high) / 2;
            if ((middle == 0 || array[middle - 1].compareTo(item) < 0) && array[middle].equals(item)) { //we have reached the left boundary
                return middle;
            } else if (array[middle].compareTo(item) < 0) {
                return left(array, item, middle + 1, high); //the boundary is somewhere in the right half
            } else {
                return left(array, item, low, middle - 1); //the boundary is somewhere in the left half
            }
        }
        return -1;
    }

    private int right(E[] array, E item, int low, int high) {
        if (high >= low) {
            final int middle = (low + high) / 2;
            if ((middle == array.length - 1 || array[middle + 1].compareTo(item) > 0) && array[middle].equals(item)) { //we have reached the right boundary
                return middle;
            } else if (array[middle].compareTo(item) > 0) { //the boundary is somewhere in the left half
                return right(array, item, low, middle - 1);
            } else {
                return right(array, item, middle + 1, high); //the boundary is somewhere in the right half
            }
        }
        return -1;
    }

}
