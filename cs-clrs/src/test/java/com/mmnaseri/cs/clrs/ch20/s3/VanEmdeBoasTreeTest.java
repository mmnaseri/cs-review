package com.mmnaseri.cs.clrs.ch20.s3;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/1/15, 7:57 PM)
 */
public class VanEmdeBoasTreeTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCreatingTreeOfSizeOne() {
    new VanEmdeBoasTree(1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCreatingTreeOfSizeZero() {
    new VanEmdeBoasTree(0);
  }

  @DataProvider
  public Object[][] stressTestDataProvider() {
    final List<Object[]> cases = new ArrayList<>();
    final List<Integer> input = new ArrayList<>();
    final int benchmark = 15;
    for (int i = 0; i < Math.pow(2, benchmark + 1); i++) {
      input.add(i * 2);
    }
    int size = 2;
    for (int i = 0; i < benchmark; i++) {
      cases.add(new Object[] {size, input});
      size *= 2;
    }
    return cases.toArray(new Object[cases.size()][]);
  }

  @Test(dataProvider = "stressTestDataProvider")
  public void stressTest(int treeSize, List<Integer> input) {
    final VanEmdeBoasTree tree = new VanEmdeBoasTree(treeSize);
    final List<Integer> inserted = new ArrayList<>();
    for (int i = 0; i < treeSize; i++) {
      assertThat(tree.contains(input.get(i)), is(false));
      tree.add(input.get(i));
      if (input.get(i) < treeSize) {
        assertThat(tree.contains(input.get(i)), is(true));
        inserted.add(input.get(i));
      }
    }
    for (int i = inserted.size() - 1; i >= 0; i--) {
      final Integer item = inserted.get(i);
      final Integer predecessor;
      if (i == 0) {
        predecessor = null;
      } else {
        predecessor = inserted.get(i - 1);
      }
      final Integer successor;
      if (i == inserted.size() - 1) {
        successor = null;
      } else {
        successor = inserted.get(i + 1);
      }
      assertThat(tree.predecessor(item), is(predecessor));
      assertThat(tree.successor(item), is(successor));
    }
    for (int i = 0; i < treeSize; i++) {
      if (input.get(i) < treeSize) {
        assertThat(tree.contains(input.get(i)), is(true));
      }
      tree.delete(input.get(i));
      assertThat(tree.contains(input.get(i)), is(false));
    }
  }
}
