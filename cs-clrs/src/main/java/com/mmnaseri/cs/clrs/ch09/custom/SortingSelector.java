package com.mmnaseri.cs.clrs.ch09.custom;

import com.mmnaseri.cs.clrs.ch09.Selector;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/11/15, 6:12 PM)
 */
@Quality(Stage.TESTED)
public class SortingSelector<E extends Comparable<E>> implements Selector<E> {

    private final Sorter<E> sorter;

    public SortingSelector(Sorter<E> sorter) {
        this.sorter = sorter;
    }

    @SafeVarargs
    @Override
    public final E select(int order, E... items) {
        if (items.length == 0) {
            return null;
        }
        sorter.sort(items);
        return items[Math.max(0, Math.min(order, items.length - 1))];
    }

}
