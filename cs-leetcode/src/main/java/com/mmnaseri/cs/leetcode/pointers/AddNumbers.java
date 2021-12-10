package com.mmnaseri.cs.leetcode.pointers;

import java.util.Objects;

public class AddNumbers {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return addWithCarry(l1, l2, 0);
  }

  private static ListNode addWithCarry(ListNode a, ListNode b, int carry) {
    if (a == null && b == null) {
      if (carry == 0) {
        return null;
      } else {
        return new ListNode(carry);
      }
    } else if (a == null) {
      carry += b.val;
      return new ListNode(carry % 10, addWithCarry(null, b.next, carry / 10));
    } else if (b == null) {
      return addWithCarry(b, a, carry);
    }
    carry += a.val;
    carry += b.val;
    return new ListNode(carry % 10, addWithCarry(a.next, b.next, carry / 10));
  }

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      ListNode listNode = (ListNode) o;
      return val == listNode.val && Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
      return Objects.hash(val, next);
    }
  }
}
