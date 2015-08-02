package com.mmnaseri.cs.clrs.ch21.s1;

import com.mmnaseri.cs.clrs.ch21.DisjointSet;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 3:41 AM)
 */
@Quality(Stage.UNTESTED)
public class BasicDisjointSet<I> implements DisjointSet<BasicElement<I>, I> {

    private final Map<UUID, List<I>> storage = new HashMap<>();

    @Override
    public BasicElement<I> create(I representative) {
        UUID index;
        do {
            index = UUID.randomUUID();
        } while (storage.containsKey(index));
        final BasicElement<I> element = new BasicElement<>();
        element.setValue(representative);
        element.setIndex(index);
        storage.put(index, new ArrayList<I>(Collections.singletonList(representative)));
        return element;
    }

    @Override
    public BasicElement<I> find(BasicElement<I> item) {
        if (storage.containsKey(item.getIndex())) {
            final BasicElement<I> element = new BasicElement<>();
            element.setValue(storage.get(item.getIndex()).get(0));
            element.setIndex(item.getIndex());
            return element;
        }
        return null;
    }

    @Override
    public BasicElement<I> union(BasicElement<I> first, BasicElement<I> second) {
        if (storage.containsKey(first.getIndex()) && storage.containsKey(second.getIndex())) {
            storage.get(first.getIndex()).addAll(storage.get(second.getIndex()));
            storage.remove(second.getIndex());
            return find(first);
        }
        return null;
    }

    @Override
    public Set<I> elements(BasicElement<I> item) {
        return storage.containsKey(item.getIndex()) ? new HashSet<>(storage.get(item.getIndex())) : null;
    }

}
