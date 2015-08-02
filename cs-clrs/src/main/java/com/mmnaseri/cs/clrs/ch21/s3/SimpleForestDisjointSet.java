package com.mmnaseri.cs.clrs.ch21.s3;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 11:54 AM)
 */
public class SimpleForestDisjointSet<I> extends AbstractForestDisjointSet<SimpleTreeElement<I>, I> {

    @Override
    public SimpleTreeElement<I> create(I representative) {
        final SimpleTreeElement<I> element = new SimpleTreeElement<>();
        element.setUuid(getUuid());
        element.setValue(representative);
        return element;
    }

}
