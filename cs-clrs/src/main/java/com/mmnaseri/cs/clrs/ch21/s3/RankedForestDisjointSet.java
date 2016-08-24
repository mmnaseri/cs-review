package com.mmnaseri.cs.clrs.ch21.s3;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 11:57 AM)
 */
public class RankedForestDisjointSet<I> extends AbstractForestDisjointSet<RankedTreeElement<I>, I> {

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

    @Override
    protected RankedTreeElement<I> newRoot(I representative) {
        final RankedTreeElement<I> element = new RankedTreeElement<>();
        element.setValue(representative);
        element.setRank(0);
        return element;
    }

}
