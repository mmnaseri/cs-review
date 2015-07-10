package com.mmnaseri.cs.algorithm.clrs.ch4.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 1:54 AM)
 */
public class RecursiveMaximumSubArrayFinder implements MaximumSubArrayFinder {

    private SubArray findAcrossMiddle(int from, int to, int mid, int... target) {
        if (from == to) {
            return new SubArray(from, from, target[from]);
        }
        int sum = 0;
        int leftSum = 0;
        int left = mid;
        int right = mid;
        int rightSum = 0;
        for (int i = mid; i >= from; i --) {
            sum += target[i];
            if (sum > leftSum) {
                leftSum = sum;
                left = i;
            }
        }
        sum = 0;
        for (int i = mid + 1; i < to; i ++) {
            sum += target[i];
            if (sum > rightSum) {
                rightSum = sum;
                right = i;
            }
        }
        return new SubArray(left, right, leftSum + rightSum);
    }

    @Override
    public SubArray find(int... target) {
        if (target.length == 0) {
            return null;
        }
        return find(0, target.length, target);
    }

    public SubArray find(int from, int to, int... target) {
        if (from >= to) {
            if (to == target.length) {
                return new SubArray(to, to, Integer.MIN_VALUE);
            }
            return new SubArray(from, from, target[from]);
        }
        int mid = from + (to - from) / 2;
        final SubArray middle = findAcrossMiddle(from, to, mid, target);
        final SubArray leftSubArray = find(from, mid, target);
        final SubArray rightSubArray = find(mid + 1, to, target);
        if (leftSubArray.isEmpty() && rightSubArray.isEmpty() && middle.isEmpty()) {
            return new SubArray(0, 0, target[0]);
        }
        if (leftSubArray.getSum() >= rightSubArray.getSum() && leftSubArray.getSum() >= middle.getSum()) {
            return leftSubArray;
        } else if (rightSubArray.getSum() >= middle.getSum()) {
            return rightSubArray;
        } else {
            return middle;
        }
    }
}
