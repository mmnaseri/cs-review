package com.mmnaseri.cs.algorithm.clrs.ch2.s3;

import java.util.Arrays;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 1:50 AM)
 */
public class CoupleSumFinder {

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
