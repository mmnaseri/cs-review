package com.mmnaseri.cs.clrs.ch21.s2;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 3:35 AM)
 */
public class WeightedLinkedElementContainer<I> extends LinkedElementContainer<WeightedLinkedElement<I>, I> {

    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
