package com.mmnaseri.cs.clrs.ch02.s1;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/13/15)
 */
public class BinaryIterativeInsertionSorter<E extends Comparable<E>> extends IterativeInsertionSorter<E> {

    public BinaryIterativeInsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected int findPlacement(E[] items, E item, int length, Comparator<E> comparator) {
        int from = 0;
        int to = length;
        while (to > from) {
            int cursor = (from + to) / 2;
            if (comparator.compare(item, items[cursor]) < 0) {
                to = cursor;
            } else {
                from = cursor + 1;
            }
        }
        return from;
    }

}
