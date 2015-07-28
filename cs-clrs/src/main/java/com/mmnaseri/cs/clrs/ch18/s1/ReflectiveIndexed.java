package com.mmnaseri.cs.clrs.ch18.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/28/15)
 */
public class ReflectiveIndexed<K extends Comparable<K>> implements Indexed<K> {

    private final K data;

    public ReflectiveIndexed(K data) {
        this.data = data;
    }

    @Override
    public K getKey() {
        return data;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }

}
