package com.mmnaseri.cs.clrs.ch02.sp;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/11/15, 1:41 AM)
 */
public interface InversionCounter<E extends Comparable<E>> {

    /**
     * This method will count the number of items which according to some defined ordering are out of place.
     * @param items    the items
     * @return number of "inversions"
     */
    @SuppressWarnings("unchecked")
    int count(E... items);

}
