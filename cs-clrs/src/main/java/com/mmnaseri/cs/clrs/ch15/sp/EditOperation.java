package com.mmnaseri.cs.clrs.ch15.sp;

import java.util.List;
import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (7/21/15, 9:51 PM)
 */
public class EditOperation {

  private final EditOperationType type;
  private final String target;
  private final int cost;

  public EditOperation(EditOperationType type, String target, int cost) {
    this.type = type;
    this.target = target;
    this.cost = cost;
  }

  public String getTarget() {
    return target;
  }

  public EditOperationType getType() {
    return type;
  }

  public int getCost() {
    return cost;
  }

  public static int getTotalCost(List<EditOperation> operations) {
    if (operations == null) {
      return 0;
    }
    int cost = 0;
    for (EditOperation editOperation : operations) {
      cost += editOperation.getCost();
    }
    return cost;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EditOperation)) return false;
    EditOperation that = (EditOperation) o;
    return cost == that.cost && type == that.type && Objects.equals(target, that.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, target, cost);
  }

  @Override
  public String toString() {
    return type.name().toLowerCase() + (target != null ? " " + target : "");
  }
}
