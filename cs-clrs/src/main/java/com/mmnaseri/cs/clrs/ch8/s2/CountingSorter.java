package com.mmnaseri.cs.clrs.ch8.s2;

import com.mmnaseri.cs.clrs.common.Sorter;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 2:48 PM)
 */
public class CountingSorter implements Sorter<Integer> {

    private final Comparator<Integer> comparator;

    public CountingSorter(Comparator<Integer> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(Integer[] items) {
        //we need to take these extra steps to ensure we support negative numbers
        int offset = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Integer item : items) {
            if (comparator.compare(item, offset) < 0) {
                offset = item;
            }
            if (comparator.compare(item, max) > 0) {
                max = item;
            }
        }
        final Integer[] counts = new Integer[max - offset + 1];
        //this extra array is required since we cannot modify the original array until we are done,
        //as we require the items in the original array as a reference
        final Integer[] target = new Integer[items.length];
        //initializing the counts
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }
        //counting the numbers, after this each item in count corresponds to how many of that item there was
        //in the original array
        for (Integer item : items) {
            counts[item - offset]++;
        }
        //accumulating counts, after which each element tells you how many items smaller or equal to that
        //element's index were in the original array
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        //we now expand the count and place them in the target array
        for (int i = items.length - 1; i >= 0; i--) {
            final Integer item = items[i];
            target[counts[item - offset] - 1] = item;
            counts[item - offset] --;
        }
        //copy the target array back into the original
        System.arraycopy(target, 0, items, 0, items.length);
    }

}
