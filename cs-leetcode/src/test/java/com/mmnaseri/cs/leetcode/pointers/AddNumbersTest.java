package com.mmnaseri.cs.leetcode.pointers;

import com.mmnaseri.cs.leetcode.pointers.AddNumbers.ListNode;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddNumbersTest {

  @Test
  public void testSolution() {
    ListNode first = new ListNode(2, new ListNode(4, new ListNode(3)));
    ListNode second = new ListNode(5, new ListNode(6, new ListNode(4)));
    assertThat(
        new AddNumbers().addTwoNumbers(first, second),
        is(new ListNode(7, new ListNode(0, new ListNode(8)))));
  }
}
