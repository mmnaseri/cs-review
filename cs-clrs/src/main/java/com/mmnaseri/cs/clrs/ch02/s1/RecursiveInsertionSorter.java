package com.mmnaseri.cs.clrs.ch02.s1;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/27/15)
 */
@Quality(Stage.DOCUMENTED)
public class RecursiveInsertionSorter<E extends Comparable<E>> extends InsertionSorter<E> {

    private final Comparator<E> comparator;

    public RecursiveInsertionSorter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * This method will start the recursion for {@link #sort(Comparable[], int)}
     *
     * @param items    the array of items to be sorted
     */
    @Override
    public void sort(E[] items) {
        sort(items, 1);
    }

    /**
     * This method will place the {@literal sorted + 1}th item in the correct place and proceed to recursively
     * repeat this step by increasing {@literal sorted} until all items are sorted
     *
     * @param items     the items to be sorted
     * @param sorted    the number of sorted items
     */
    @Complexity(value = "O(n . f(n))", explanation = "where 'f(n)' is the complexity of the placement step")
    private void sort(E[] items, int sorted) {
        if (sorted >= items.length) {
            return;
        }
        final E item = items[sorted];
        int placement = findPlacement(items, item, sorted, comparator);
        System.arraycopy(items, placement, items, placement + 1, sorted - placement);
        items[placement] = item;
        sort(items, sorted + 1);
    }

}
