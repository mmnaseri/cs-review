package com.mmnaseri.cs.clrs.ch27.s3;

import com.mmnaseri.cs.clrs.ch02.s3.MergeSorter;
import com.mmnaseri.cs.clrs.ch27.s0.Action;
import com.mmnaseri.cs.clrs.ch27.s0.Scheduler;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (3/13/16, 11:27 PM)
 */
@Quality(Stage.TESTED)
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
     * `result` array starting at index `resultStart`.
     */
    private void merge(final E[] original, int leftStart, int leftEnd, int rightStart, int rightEnd, final E[] result, final int resultStart) {
        int leftLength = leftEnd - leftStart;
        int rightLength = rightEnd - rightStart;
        if (leftLength < rightLength) {
            int temp;
            //exchanging lengths
            temp = rightLength;
            leftLength = temp;
            //exchanging starting points
            temp = rightStart;
            rightStart = leftStart;
            leftStart = temp;
            //exchanging ending points
            temp = rightEnd;
            rightEnd = leftEnd;
            leftEnd = temp;
        }
        if (leftLength == 0) {
            return;
        }
        //at this point, *left* is the larger portion, and we are going to use that as the reference
        final int leftMiddle = (leftStart + leftEnd - 1) / 2;
        //we find the pivot point for the *right* part
        final int rightMiddle;
        try {
            rightMiddle = find(original[leftMiddle], original, rightStart, rightEnd);
        } catch (Exception e) {
            System.err.println(original[leftMiddle] + ", " + Arrays.asList(original).subList(rightStart, rightEnd));
            throw e;
        }
        //we find out *where* we need to stick the one element we are going to place at this iteration
        final int resultMiddle = resultStart + (leftMiddle - leftStart) + (rightMiddle - rightStart);
        //we place one element in its place. This execution is the *only* execution that will modify this index of the result array
        result[resultMiddle] = original[leftMiddle];
        final int finalLeftStart = leftStart;
        final int finalRightStart = rightStart;
        final Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.spawn(new Action() {
            @Override
            public void perform() {
                merge(original, finalLeftStart, leftMiddle, finalRightStart, rightMiddle, result, resultStart);
            }
        });
        merge(original, leftMiddle + 1, leftEnd, rightMiddle, rightEnd, result, resultMiddle + 1);
        scheduler.syncAndEnd();
    }

    /**
     * Performs binary search to find the given needle in the sorted array
     *
     * @param needle the item to look for. The needle <em>must</em> exist within the defined scope of the array
     * @param array  the array to look inside
     * @param start  the starting point (inclusive)
     * @param end    the ending point (exclusive)
     * @return the index of the item in the array
     */
    private int find(E needle, E[] array, int start, int end) {
        int low = start;
        int high = Math.max(end, start);
        while (low < high) {
            int mid = (low + high) / 2;
            final E pivot = array[mid];
            if (pivot == null || comparator.compare(needle, pivot) <= 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    @SuppressWarnings("unchecked")
    private void sort(final E[] array, final int start, int end, E[] result, int offset) {
        if (end == start) {
            return;
        }
        if (end - start == 1) {
            result[offset] = array[start];
            return;
        }
        final E[] temporary = (E[]) Array.newInstance(array.getClass().getComponentType(), end - start);
        final int middle = (end + start - 1) / 2;
        final int pivot = middle - start;
        final Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.spawn(new Action() {
            @Override
            public void perform() {
                sort(array, start, middle + 1, temporary, 0);
            }
        });
        sort(array, middle + 1, end, temporary, pivot + 1);
        scheduler.syncAndEnd();
        merge(temporary, 0, pivot + 1, pivot + 1, end - start, result, offset);
    }

    @Override
    protected void sort(E[] items, int from, int to) {
        //noinspection unchecked
        final E[] temporary = (E[]) Array.newInstance(items.getClass().getComponentType(), to - from);
        sort(items, from, to, temporary, 0);
        System.arraycopy(temporary, 0, items, 0, items.length);
    }

}
