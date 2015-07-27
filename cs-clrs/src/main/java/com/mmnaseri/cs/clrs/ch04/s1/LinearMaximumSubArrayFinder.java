package com.mmnaseri.cs.clrs.ch04.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * This is an implementation of <a href="http://www.algorithmist.com/index.php/Kadane's_Algorithm">Kadane's algorithm</a>.
 *
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 2:34 AM)
 */
@Quality(Stage.TESTED)
public class LinearMaximumSubArrayFinder implements MaximumSubArrayFinder {

    @Override
    public SubArray find(int... target) {
        if (target.length == 0) {
            return null;
        }
        int sum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        int currentSum = 0;
        int currentStart = 0;
        for (int currentEnd = 0; currentEnd < target.length; currentEnd ++) {
            currentSum += target[currentEnd];
            if (currentSum > sum) {
                sum = currentSum;
                start = currentStart;
                end = currentEnd;
            }
            if (currentSum < 0) {
                currentSum = 0;
                currentStart = currentEnd + 1;
            }
        }
        return new SubArray(start, end, sum);
    }

}
