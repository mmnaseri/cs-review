package com.mmnaseri.cs.clrs.ch08.s4;

import com.mmnaseri.cs.clrs.ch02.s1.InsertionSorter;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 4:40 PM)
 */
@Quality(Stage.TESTED)
public class BucketSorter implements Sorter<Double> {

    private final int buckets;
    private final InsertionSorter<Double> sorter = new InsertionSorter<>(new Comparator<Double>() {
        @Override
        public int compare(Double first, Double second) {
            return first.compareTo(second);
        }
    });

    public BucketSorter(int buckets) {
        this.buckets = buckets;
    }

    @Override
    public void sort(Double[] items) {
        double min = Integer.MAX_VALUE;
        double max = Integer.MIN_VALUE;
        for (Double item : items) {
            if (min > item) {
                min = item;
            }
            if (max < item) {
                max = item;
            }
        }
        double bucketSize = (max - min) / (this.buckets - 1.0);
        final List<List<Double>> buckets = new ArrayList<>(this.buckets);
        for (int i = 0; i < this.buckets; i ++) {
            buckets.add(new LinkedList<Double>());
        }
        for (Double item : items) {
            buckets.get((int) ((item - min) / bucketSize)).add(item);
        }
        int copied = 0;
        for (final List<Double> bucket : buckets) {
            final Double[] values = bucket.toArray(new Double[bucket.size()]);
            sorter.sort(values);
            System.arraycopy(values, 0, items, copied, values.length);
            copied += values.length;
        }
    }

}
