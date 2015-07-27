package com.mmnaseri.cs.clrs.ch18.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public interface DataStore<E extends Indexed<K>, K extends Comparable<K>> {

    E read(K key);

    void write(K key, E data);

}
