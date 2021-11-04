package com.mmnaseri.cs.clrs.ch22.s1;

import com.mmnaseri.cs.clrs.common.ParameterizedTypeReference;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/3/15)
 */
public class GraphElement<D extends GraphDetails> {

  private final Map<String, Object> properties = new HashMap<>();
  private D details;

  public void setProperty(String name, Object value) {
    properties.put(name, value);
  }

  public Object getProperty(String name) {
    return properties.get(name);
  }

  public <E> E getProperty(String name, Class<E> type) {
    return properties.containsKey(name) ? type.cast(properties.get(name)) : null;
  }

  @SuppressWarnings("unused")
  public <E> E getProperty(String name, ParameterizedTypeReference<E> typeReference) {
    //noinspection unchecked
    return properties.containsKey(name) ? (E) properties.get(name) : null;
  }

  public boolean hasProperty(String name) {
    return properties.containsKey(name);
  }

  public Set<String> getPropertyNames() {
    return properties.keySet();
  }

  public D getDetails() {
    return details;
  }

  public void setDetails(D details) {
    this.details = details;
  }
}
