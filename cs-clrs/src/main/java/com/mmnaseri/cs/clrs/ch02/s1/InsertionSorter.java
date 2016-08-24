package com.mmnaseri.cs.clrs.ch02.s1;

import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * <p>This class divides the functionality expected of the insertion sort mechanism into two pieces:</p>
 * <ol>
 *     <li>Finding where a single item should be placed compared to a portion of the whole array, and</li>
 *     <li>Doing something about the realized placement.</li>
 * </ol>
 *
 * @see com.mmnaseri.cs.clrs.common.Sorter
 * @see IterativeInsertionSorter
 * @see RecursiveInsertionSorter
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 2:15 AM)
 */
@Quality(Stage.DOCUMENTED)
public abstract class InsertionSorter<E extends Comparable<E>> implements Sorter<E> {

    /**
     * This method finds where a given item should be placed according to the provided comparator by looking at all the items
     * in the specified portion of the input array of items
     * @param items         all the items
     * @param item          the item which we want to place
     * @param length        the length of the array which should be visited
     * @param comparator    the comparator
     * @return the position of the item compared to the array, which can range from {@literal 0} to {@literal length} (thus
     * indicating that the item is comparatively larger than all the other items).
     */
    @Complexity("O(n)")
    protected int findPlacement(E[] items, E item, int length, Comparator<E> comparator) {
        int placement = length;
        for (int j = 0; j < length; j ++) {
            if (comparator.compare(items[j], item) > 0) {
                placement = j;
                break;
            }
        }
        return placement;
    }

}
