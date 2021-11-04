package com.mmnaseri.cs.clrs.ch21.s2;

import com.mmnaseri.cs.clrs.ch21.Element;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 3:17 AM)
 */
public class LinkedElement<
        E extends LinkedElement<E, I, C>, I, C extends LinkedElementContainer<E, I>>
    implements Element<I> {

  private C container;
  private I value;
  private E previous;
  private E next;

  public C getContainer() {
    return container;
  }

  public void setContainer(C container) {
    this.container = container;
  }

  @Override
  public I getValue() {
    return value;
  }

  public void setValue(I value) {
    this.value = value;
  }

  public E getPrevious() {
    return previous;
  }

  public void setPrevious(E previous) {
    this.previous = previous;
  }

  public E getNext() {
    return next;
  }

  public void setNext(E next) {
    this.next = next;
  }
}
