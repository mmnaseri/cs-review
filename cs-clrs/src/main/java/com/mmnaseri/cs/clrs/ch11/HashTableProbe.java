package com.mmnaseri.cs.clrs.ch11;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/14/15, 12:51 AM)
 */
public interface HashTableProbe {

    int probe(int index, int capacity, int sequence);

}
