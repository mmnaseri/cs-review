package com.mmnaseri.cs.clrs.ch22.s1;

import com.mmnaseri.cs.clrs.common.ParameterizedTypeReference;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/3/15)
 */
public class GraphElement {

    private Map<String, Object> properties = new HashMap<>();

    public void setProperty(String name, Object value) {
        properties.put(name, value);
    }

    public Object getProperty(String name) {
        return properties.get(name);
    }

    public <E> E getProperty(String name, Class<E> type) {
        return properties.containsKey(name) ? type.cast(properties.get(name)) : null;
    }

    public <E> E getProperty(String name, ParameterizedTypeReference<E> typeReference) {
        //noinspection unchecked
        return properties.containsKey(name) ? (E) properties.get(name) : null;
    }

}
