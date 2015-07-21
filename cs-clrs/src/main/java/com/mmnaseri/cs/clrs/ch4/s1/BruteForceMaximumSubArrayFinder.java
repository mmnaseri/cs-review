package com.mmnaseri.cs.clrs.ch4.s1;

import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 1:53 AM)
 */
@Quality(Stage.TESTED)
public class BruteForceMaximumSubArrayFinder implements MaximumSubArrayFinder {

    @Override
    public SubArray find(int... target) {
        int[][] sums = new int[target.length][target.length];
        for (int i = 0; i < target.length; i++) {
            sums[i][i] = target[i];
            for (int j = i + 1; j < target.length; j++) {
                sums[i][j] = sums[i][j - 1] + target[j];
            }
        }
        SubArray max = null;
        for (int i = 0; i < sums.length; i++) {
            for (int j = i; j < sums[i].length; j++) {
                final int sum = sums[i][j];
                if (max == null || max.getSum() < sum) {
                    max = new SubArray(i, j, sum);
                }
            }
        }
        return max;
    }

}
