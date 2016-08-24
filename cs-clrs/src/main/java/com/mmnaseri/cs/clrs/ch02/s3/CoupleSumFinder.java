package com.mmnaseri.cs.clrs.ch02.s3;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Arrays;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 1:50 AM)
 */
@Quality(Stage.DOCUMENTED)
public class CoupleSumFinder {

    /**
     * This method will look up two items whose sum equals the indicated sum in a sorted array
     * @param sum       the target sum
     * @param target    the array of numbers
     * @return the couple
     */
    @Complexity("O(n . lg(n))")
    public Couple findCouple(int sum, int... target) {
        Arrays.sort(target);
        int i = 0;
        int j = target.length - 1;
        while (i >= 0 && j < target.length && i < j) {
            final int result = target[i] + target[j];
            if (result == sum) {
                return new Couple(target[i], target[j]);
            } else if (result < sum) {
                //we need to increase the lower bound
                i ++;
            } else {
                //we need to decrease the upper bound
                j --;
            }
        }
        return null;
    }

}
