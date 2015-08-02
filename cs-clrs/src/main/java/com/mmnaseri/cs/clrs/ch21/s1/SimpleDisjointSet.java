package com.mmnaseri.cs.clrs.ch21.s1;

import com.mmnaseri.cs.clrs.ch21.DisjointSet;
import com.mmnaseri.cs.clrs.ch21.Element;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 2:56 AM)
 */
@Quality(Stage.UNTESTED)
public class SimpleDisjointSet<I> implements DisjointSet<SimpleElement<I>, I> {

    private final UUID id = UUID.randomUUID();

    @Override
    public Element<I> create(I representative) {
        final SimpleElement<I> element = new SimpleElement<>();
        element.setValue(representative);
        final ElementContainer<I> container = new ElementContainer<>(id);
        element.setContainer(container);
        container.setHead(element);
        container.setTail(element);
        return element;
    }

    @Override
    public Element<I> find(SimpleElement<I> item) {
        control(item);
        return item.getContainer().getHead();
    }

    @Override
    public Element<I> union(SimpleElement<I> first, SimpleElement<I> second) {
        control(first);
        control(second);
        final ElementContainer<I> firstContainer = first.getContainer();
        final ElementContainer<I> secondContainer = second.getContainer();
        secondContainer.getHead().setPrevious(firstContainer.getTail());
        firstContainer.getTail().setNext(secondContainer.getHead());
        firstContainer.setTail(secondContainer.getTail());
        SimpleElement<I> element = secondContainer.getHead();
        while (element != null) {
            element.setContainer(firstContainer);
            element = element.getNext();
        }
        secondContainer.unlink();
        return firstContainer.getHead();
    }

    @Override
    public Set<I> elements(SimpleElement<I> item) {
        control(item);
        SimpleElement<I> element = item.getContainer().getHead();
        final Set<I> result = new HashSet<>();
        while (element != null) {
            result.add(element.getValue());
            element = element.getNext();
        }
        return result;
    }

    private void control(SimpleElement<I> item) {
        final ElementContainer<I> container = item.getContainer();
        Objects.requireNonNull(container, "Item does not point to any sets");
        if (!container.getSet().equals(id)) {
            throw new IllegalStateException("This item does not belong to the current pool");
        }
    }

}
