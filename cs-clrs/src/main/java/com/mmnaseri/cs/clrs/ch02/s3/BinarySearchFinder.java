package com.mmnaseri.cs.clrs.ch02.s3;

import com.mmnaseri.cs.clrs.common.Finder;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Arrays;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 3:16 AM)
 */
@Quality(Stage.DOCUMENTED)
public class BinarySearchFinder<E extends Comparable<E>> implements Finder<E> {

    /**
     * This method implements the binary search method for finding a needle item in a haystack array
     *
     * @param needle the needle to look for
     * @param items  the haystack of items
     * @return the position of the occurrence of the needle in the haystack
     */
    @SafeVarargs
    @Override
    @Complexity(value = "O(n . lg(n))", explanation = "even though the search is only O(lg(n)), it makes the strong assumption that the array is sorted already," +
            "and to enforce this assumption, we have to first sort the array, and O(n . lg(n) + lg(n)) = O(n.lg(n))")
    public final int find(E needle, E... items) {
        Arrays.sort(items);
        return find(items, needle, 0, items.length);
    }

    private int find(E[] items, E needle, int from, int to) {
        //if the range is invalid, return `-1`
        if (to < from || from < 0 || to > items.length || items.length == 0) {
            return -1;
        }
        //calculate the middle
        int mid = from + (to - from) / 2;
        //compare the needle to the middle item
        final int comparison = needle.compareTo(items[mid]);
        if (comparison == 0) {
            //if we have located the item, return the middle as the target position
            return mid;
        } else if (to - from > 1) {
            if (comparison < 0) {
                //if the item is smaller than the middle, try searching in the left half of the array
                return find(items, needle, from, mid-1);
            } else {
                //otherwise, try searching the right half of the array
                return find(items, needle, mid+1, to);
            }
        } else {
            //if the size of the search array is small enough that only contains a single item or less and we still haven't
            //found the needle, we should give up
            return -1;
        }
    }

}
