package com.mmnaseri.cs.clrs.ch02.sp;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 3:39 AM)
 */
@Quality(Stage.DOCUMENTED)
public class MergeInversionCounter<E extends Comparable<E>> implements InversionCounter<E> {

    private final Comparator<E> comparator;

    public MergeInversionCounter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    @Complexity("O(n)")
    private int merge(E[] array, int from, int mid, int to) {
        //beak the original array into two halves around the designated pivot point
        final E[] left = (E[]) Array.newInstance(array.getClass().getComponentType(), mid - from);
        final E[] right = (E[]) Array.newInstance(array.getClass().getComponentType(), to - mid);
        System.arraycopy(array, from, left, 0, left.length);
        System.arraycopy(array, mid, right, 0, right.length);
        //start from the beginnings of the two arrays
        int leftIndex = 0;
        int rightIndex = 0;
        //at the start, we do not have any inversions
        int inversions = 0;
        //to avoid incrementing the inversions counter every time we encounter one, we count all inversions for each group
        //of items in the left array that are bigger than the current item in the right array
        boolean counted = false;
        for (int cursor = from; cursor < to; cursor ++) {
            if (rightIndex >= right.length) {
                //if the right cursor has reached the end of its respective array, copy the left array to the remainder of the spots and exit the loop
                System.arraycopy(left, leftIndex, array, cursor, left.length - leftIndex);
                break;
            } else if (leftIndex >= left.length) {
                //if the left cursor has reached the end of its respective array, copy the right array to the remainder of the spots and exit the loop
                System.arraycopy(right, rightIndex, array, cursor, right.length - rightIndex);
                break;
            }
            //whenever something in the left array is bigger than the current item in the right array, it is an inversion
            if (!counted && comparator.compare(left[leftIndex], right[rightIndex]) > 0) {
                inversions += left.length - leftIndex;
                counted = true;
            }
            if (comparator.compare(left[leftIndex], right[rightIndex]) <= 0) {
                //if the items in the left array are smaller, we insert them and move the cursor accordingly
                array[cursor] = left[leftIndex];
                leftIndex++;
            } else {
                //if the items in the right array are smaller, though, it is an inversion (and we mark it as unaccounted for so that the next iteration can pick it up)
                //the reason why we don't count it right away is that the moment when the insertion has to move from the right array to the left array shouldn't be
                //counted as an inversion, rather the beginning of an inverted portion
                counted = false;
                array[cursor] = right[rightIndex];
                rightIndex++;
            }
        }
        //return the inversions found in the current portion of the array
        return inversions;
    }

    @Complexity(value = "O(n.lg(n))", explanation = "Similar to merge sort")
    private int count(E[] items, int from, int to) {
        //if the array has only a single item, it can't contain any inversions
        if (to - from < 2) {
            return 0;
        }
        int count = 0;
        //calculate the midpoint
        int mid = from + (to - from) / 2;
        //count the inversions in the left portion
        count += count(items, from, mid);
        //and add to those the ones we find in the right portion
        count += count(items, mid, to);
        //and finally add the ones found at the current incarnation of the array (this is where the actual work for the current array is done)
        count += merge(items, from, mid, to);
        //return the inversions found so far
        return count;
    }

    @Complexity("O(n.lg(n))")
    @Override
    @SafeVarargs
    public final int count(E... items) {
        //we create a copy so that the merge operation does not affect the actual array that was passed down to us
        final E[] copy = Arrays.copyOf(items, items.length);
        return count(copy, 0, items.length);
    }

}
