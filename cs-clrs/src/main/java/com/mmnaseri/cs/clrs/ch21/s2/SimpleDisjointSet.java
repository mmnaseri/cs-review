package com.mmnaseri.cs.clrs.ch21.s2;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 2:56 AM)
 */
@Quality(Stage.TESTED)
public class SimpleDisjointSet<I> extends AbstractLinkedDisjointSet<SimpleLinkedElement<I>, I, SimpleLinkedElementContainer<I>> {

    @Override
    public SimpleLinkedElement<I> create(I representative) {
        final SimpleLinkedElement<I> element = new SimpleLinkedElement<>();
        final SimpleLinkedElementContainer<I> container = new SimpleLinkedElementContainer<>();
        container.setUuid(getUuid());
        container.setHead(element);
        container.setTail(element);
        element.setContainer(container);
        element.setValue(representative);
        return element;
    }

    @Override
    public SimpleLinkedElement<I> union(SimpleLinkedElement<I> first, SimpleLinkedElement<I> second) {
        return absorb(first, second);
    }

}
