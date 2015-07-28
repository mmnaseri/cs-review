package com.mmnaseri.cs.clrs.ch18.s1;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/28/15)
 */
public class MapStorage<E> implements Storage<E> {
    
    private final Map<UUID, E> nonIndexed = new HashMap<>();
    private final Map<UUID, Map<Integer, E>> indexed = new HashMap<>();

    public Map<UUID, E> getNonIndexed() {
        return nonIndexed;
    }

    public Map<UUID, Map<Integer, E>> getIndexed() {
        return indexed;
    }

    @Override
    public E read(UUID id, int child) {
        if (child >= 0 && indexed.containsKey(id) && indexed.get(id).containsKey(child)) {
            return indexed.get(id).get(child);
        }
        return null;
    }

    @Override
    public E read(UUID id) {
        return nonIndexed.containsKey(id) ? nonIndexed.get(id) : null;
    }

    @Override
    public void write(UUID id, int index, E value) {
        if (!indexed.containsKey(id)) {
            indexed.put(id, new HashMap<Integer, E>());
        }
        indexed.get(id).put(index, value);
    }

    @Override
    public void write(UUID id, E value) {
        nonIndexed.put(id, value);
    }

    @Override
    public void move(UUID source, int sourceIndex, UUID target, int targetIndex) {
        final E value = read(source, sourceIndex);
        if (value != null) {
            delete(source, sourceIndex);
            write(target, targetIndex, value);
        }
    }

    @Override
    public void delete(UUID id) {
        if (nonIndexed.containsKey(id)) {
            nonIndexed.remove(id);
        }
    }

    @Override
    public void delete(UUID id, int index) {
        if (indexed.containsKey(id)) {
            final Map<Integer, E> map = indexed.get(id);
            if (map.containsKey(index)) {
                map.remove(index);
            }
        }
    }

}
