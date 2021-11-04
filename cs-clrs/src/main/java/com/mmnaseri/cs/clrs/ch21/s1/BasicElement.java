package com.mmnaseri.cs.clrs.ch21.s1;

import com.mmnaseri.cs.clrs.ch21.Element;

import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 3:40 AM)
 */
public class BasicElement<I> implements Element<I> {

  private I value;
  private UUID index;

  @Override
  public I getValue() {
    return value;
  }

  public void setValue(I value) {
    this.value = value;
  }

  public UUID getIndex() {
    return index;
  }

  public void setIndex(UUID index) {
    this.index = index;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BasicElement<?> that = (BasicElement<?>) o;
    return !(value != null ? !value.equals(that.value) : that.value != null)
        && index.equals(that.index);
  }

  @Override
  public int hashCode() {
    int result = value != null ? value.hashCode() : 0;
    result = 31 * result + index.hashCode();
    return result;
  }
}
