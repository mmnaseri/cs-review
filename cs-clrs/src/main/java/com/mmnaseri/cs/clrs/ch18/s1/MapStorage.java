package com.mmnaseri.cs.clrs.ch18.s1;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/28/15)
 */
public class MapStorage<E> implements Storage<E> {

  private final Map<UUID, Map<Integer, E>> memory = new HashMap<>();

  @Override
  public E read(UUID id, int child) {
    if (child >= 0 && memory.containsKey(id) && memory.get(id).containsKey(child)) {
      return memory.get(id).get(child);
    }
    return null;
  }

  @Override
  public void write(UUID id, int index, E value) {
    if (!memory.containsKey(id)) {
      memory.put(id, new HashMap<>());
    }
    memory.get(id).put(index, value);
  }

  @Override
  public void move(UUID source, int sourceIndex, UUID target, int targetIndex) {
    if (read(target, targetIndex) != null) {
      delete(target, targetIndex);
    }
    final E value = read(source, sourceIndex);
    if (value != null) {
      delete(source, sourceIndex);
      write(target, targetIndex, value);
    }
  }

  @Override
  public void delete(UUID id, int index) {
    if (memory.containsKey(id)) {
      final Map<Integer, E> map = memory.get(id);
      map.remove(index);
      if (map.isEmpty()) {
        memory.remove(id);
      }
    }
  }
}
