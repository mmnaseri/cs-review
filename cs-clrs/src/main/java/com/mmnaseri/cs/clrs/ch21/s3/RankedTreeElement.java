package com.mmnaseri.cs.clrs.ch21.s3;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 11:56 AM)
 */
public class RankedTreeElement<I> extends TreeElement<I, RankedTreeElement<I>> {

    private int rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}
