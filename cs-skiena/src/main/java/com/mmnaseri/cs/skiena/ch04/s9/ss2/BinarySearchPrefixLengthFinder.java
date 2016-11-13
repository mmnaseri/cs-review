package com.mmnaseri.cs.skiena.ch04.s9.ss2;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 2:27 PM)
 */
@Quality(Stage.TESTED)
public class BinarySearchPrefixLengthFinder<E> implements PrefixLengthFinder<E> {

    @Complexity(value = "O(log(k))", explanation = "`k` is the length of the prefix")
    @Override
    public int findPrefixLength(E[] array) {
        if (array.length == 0) {
            return 0;
        }
        int from = 0;
        int to = 1;
        final E first = array[0];
        //O(log(k)) -- overreaches the length of prefix by one, by doubling.
        //so, at this point `2^(k+1) >= from - to >= 2^k`
        while (to < array.length) {
            if (!array[to - 1].equals(first)) {
                break;
            }
            from = to;
            to *= 2;
        }
        //we might have jumped over the end of the array
        to = Math.min(to, array.length);
        //we then perform a binary search of order O(log(to-from)) ~= O(log(k/2))
        while (from != to - 1) {
            int middle = (from + to) / 2;
            if (array[middle].equals(first)) {
                from = middle;
            } else {
                to = middle;
            }
        }
        //we might have found the last character of the prefix or the next character after the prefix
        return array[to - 1].equals(first) ? to : to - 1;
    }

}
