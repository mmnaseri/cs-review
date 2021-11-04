package com.mmnaseri.cs.clrs.ch21.s2;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 3:36 AM)
 */
@Quality(Stage.TESTED)
public class WeightedDisjointSet<I>
    extends AbstractLinkedDisjointSet<
        WeightedLinkedElement<I>, I, WeightedLinkedElementContainer<I>> {

  @Override
  protected WeightedLinkedElement<I> newRoot(I representative) {
    final WeightedLinkedElementContainer<I> container = new WeightedLinkedElementContainer<>();
    final WeightedLinkedElement<I> element = new WeightedLinkedElement<>();
    element.setValue(representative);
    element.setContainer(container);
    container.setHead(element);
    container.setTail(element);
    container.setWeight(1);
    return element;
  }

  @Override
  public WeightedLinkedElement<I> union(
      WeightedLinkedElement<I> first, WeightedLinkedElement<I> second) {
    final WeightedLinkedElement<I> element;
    if (first.getContainer().getWeight() < second.getContainer().getWeight()) {
      element = absorb(first, second);
    } else {
      element = absorb(second, first);
    }
    element
        .getContainer()
        .setWeight(first.getContainer().getWeight() + second.getContainer().getWeight());
    return element;
  }
}
