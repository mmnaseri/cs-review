package com.mmnaseri.cs.clrs.ch18.s1;

import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/28/15)
 */
public interface Storage<E> {

    E read(UUID id, int child);

    void write(UUID id, int index, E value);

    void move(UUID source, int sourceIndex, UUID target, int targetIndex);

    void delete(UUID id, int index);

}
