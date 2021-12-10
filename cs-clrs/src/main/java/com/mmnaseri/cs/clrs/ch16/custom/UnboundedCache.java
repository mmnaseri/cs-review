package com.mmnaseri.cs.clrs.ch16.custom;

import com.mmnaseri.cs.clrs.common.Cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/25/15, 1:44 PM)
 */
@SuppressWarnings("unused")
public class UnboundedCache<K, V> implements Cache<K, V> {

  private final Map<K, V> storage = new HashMap<>();

  @Override
  public boolean contains(K key) {
    return storage.containsKey(key);
  }

  @Override
  public V get(K key) {
    return storage.get(key);
  }

  @Override
  public void put(K key, V value) {
    storage.put(key, value);
  }

  @Override
  public boolean evict(K key) {
    if (!contains(key)) {
      return false;
    }
    storage.remove(key);
    return true;
  }

  @Override
  public int size() {
    return storage.size();
  }
}
