package com.mmnaseri.cs.clrs.ch23.s1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/14/15)
 */
public class MutableWeightedEdgeDetails implements WeightedEdgeDetails {

  private int weight;

  @Override
  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }
}
