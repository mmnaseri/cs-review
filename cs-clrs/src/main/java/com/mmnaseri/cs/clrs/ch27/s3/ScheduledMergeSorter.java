package com.mmnaseri.cs.clrs.ch27.s3;

import com.mmnaseri.cs.clrs.ch02.s3.MergeSorter;
import com.mmnaseri.cs.clrs.ch27.s0.Action;
import com.mmnaseri.cs.clrs.ch27.s0.Scheduler;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.SerialSchedulerFactory;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (3/13/16, 11:27 PM)
 */
@Quality(value = Stage.BUGGY, explanation = "does not work. committed so that I can work on it from a different machine")
public class ScheduledMergeSorter<E extends Comparable<E>> extends MergeSorter<E> {

    private final Comparator<E> comparator;
    private final SchedulerFactory schedulerFactory;

    public ScheduledMergeSorter(Comparator<E> comparator, SchedulerFactory schedulerFactory) {
        super(comparator);
        this.comparator = comparator;
        this.schedulerFactory = schedulerFactory;
    }

    /**
     * Merges the portions of `original` stated by `[leftStart, leftEnd]` and `[rightStart, rightEnd]` into the
     * `result` array starting at index `offset`.
     */
    private void merge(final E[] original, int leftStart, int leftEnd, int rightStart, int rightEnd, final E[] result, final int offset) {
        int leftLength = leftEnd - leftStart;
        int rightLength = rightEnd - rightStart;
        if (leftLength < rightLength) {
            //exchange the lengths
            leftLength = rightLength;
            //exchange starting points
            int temp = leftStart;
            leftStart = rightStart;
            rightStart = temp;
            //exchange ending points
            temp = leftEnd;
            leftEnd = rightEnd;
            rightEnd = temp;
        }
        if (leftLength == 0) {
            return;
        }
        final int leftMidPoint = (leftStart + leftEnd) / 2;
        final int rightMidPoint = find(original[leftMidPoint], original, rightStart, rightEnd);
        final int resultMidPoint = offset + (leftMidPoint - leftStart) + (rightMidPoint - rightStart);
        result[resultMidPoint] =  original[leftMidPoint];
        final Scheduler scheduler = schedulerFactory.getScheduler();
        final int finalLeftStart = leftStart;
        final int finalRightStart = rightStart;
//        scheduler.spawn(new Action() {
//            @Override
//            public void perform() {
//                merge(original, finalLeftStart, leftMidPoint - 1, finalRightStart, rightMidPoint - 1, result, offset);
//            }
//        });
        merge(original, finalLeftStart, leftMidPoint - 1, finalRightStart, rightMidPoint - 1, result, offset);
        merge(original, leftMidPoint + 1, leftEnd, rightMidPoint, rightEnd, result, resultMidPoint + 1);
        scheduler.syncAndEnd();
    }

    /**
     * Performs binary search to find the given needle in the sorted array
     * @param needle    the item to look for. The needle <em>must</em> exist within the defined scope of the array
     * @param array     the array to look inside
     * @param start     the starting point (inclusive)
     * @param end       the ending point (exclusive)
     * @return the index of the item in the array
     */
    private int find(E needle, E[] array, int start, int end) {
        int low = start;
        int high = Math.max(end + 1, start);
        while (low < high) {
            int mid = (low + high) / 2;
            if (comparator.compare(needle, array[mid]) <= 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        ScheduledMergeSorter<Integer> sorter = new ScheduledMergeSorter<>(comparator, new SerialSchedulerFactory());
        Integer[] result = new Integer[6];
        sorter.merge(new Integer[]{1, 3, 5, 2, 4, 6}, 0, 3, 3, 6, result, 0);
        System.out.println("result = " + Arrays.toString(result));
    }

}
