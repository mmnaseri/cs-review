package com.mmnaseri.cs.clrs.ch18.s1;

import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public interface DataStore<E> {

    E read(UUID id);

    void write(UUID id, E data);

}
