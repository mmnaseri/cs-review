package com.mmnaseri.cs.clrs.ch05.custom;

import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (9/2/16, 2:50 PM)
 */
@Quality(Stage.TESTED)
public class ShellSorter<E extends Comparable<E>> implements Sorter<E> {

    private final Comparator<E> comparator;
    private final int[] steps;

    public ShellSorter(Comparator<E> comparator, int[] steps) {
        this.comparator = comparator;
        this.steps = steps;
        if (steps.length == 0) {
            throw new IllegalArgumentException("ShellSorter requires at least one step to complete");
        }
        for (int i = 1; i < steps.length; i++) {
            if (steps[i] > steps[i - 1]) {
                throw new IllegalArgumentException("ShellSorter steps must come in decreasing order");
            }
        }
        if (steps[steps.length - 1] != 1) {
            throw new IllegalArgumentException("ShellSorter steps must end in 1");
        }
    }

    @Override
    public void sort(E[] items) {
        //perform insertion sorts with decreasing order
        for (int step : steps) {
            for (int i = step; i < items.length; i++) {
                //remember the first item
                final E temp = items[i];
                int j;
                //keep searching the original array with gaps of `step` till you find the proper place for the item
                for (j = i; j >= step && comparator.compare(items[j - step], temp) > 0; j -= step) {
                    items[j] = items[j - step];
                }
                //restore the proper item that should come as a replacement to item[j]
                items[j] = temp;
            }
        }
    }

}
