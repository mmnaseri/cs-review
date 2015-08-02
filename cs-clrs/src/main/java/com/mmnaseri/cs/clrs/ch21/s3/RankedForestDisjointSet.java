package com.mmnaseri.cs.clrs.ch21.s3;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 11:57 AM)
 */
public class RankedForestDisjointSet<I> extends AbstractForestDisjointSet<RankedTreeElement<I>, I> {

    @Override
    public RankedTreeElement<I> create(I representative) {
        final RankedTreeElement<I> element = new RankedTreeElement<>();
        element.setUuid(getUuid());
        element.setValue(representative);
        element.setRank(0);
        return element;
    }

    @Override
    protected RankedTreeElement<I> link(RankedTreeElement<I> firstRoot, RankedTreeElement<I> secondRoot) {
        if (firstRoot.getRank() > secondRoot.getRank()) {
            secondRoot.setParent(firstRoot);
            return firstRoot;
        } else {
            firstRoot.setParent(secondRoot);
            if (firstRoot.getRank() == secondRoot.getRank()) {
                secondRoot.setRank(secondRoot.getRank() + 1);
            }
            return secondRoot;
        }
    }

}
