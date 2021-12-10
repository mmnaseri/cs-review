package com.mmnaseri.cs.clrs.ch21.s2;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 2:56 AM)
 */
@Quality(Stage.TESTED)
public class SimpleDisjointSet<I>
    extends AbstractLinkedDisjointSet<SimpleLinkedElement<I>, I, SimpleLinkedElementContainer<I>> {

  @Override
  protected SimpleLinkedElement<I> newRoot(I representative) {
    final SimpleLinkedElement<I> element = new SimpleLinkedElement<>();
    final SimpleLinkedElementContainer<I> container = new SimpleLinkedElementContainer<>();
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
