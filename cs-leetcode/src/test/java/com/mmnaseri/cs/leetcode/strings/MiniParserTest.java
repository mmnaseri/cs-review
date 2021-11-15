package com.mmnaseri.cs.leetcode.strings;

import com.mmnaseri.cs.leetcode.strings.MiniParser.NestedInteger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MiniParserTest {

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {"324", NestedInteger.of(324)},
      new Object[] {
        "[123,[456,[789]]]",
        NestedInteger.of(
            NestedInteger.of(123),
            NestedInteger.of(NestedInteger.of(456), NestedInteger.of(NestedInteger.of(789))))
      },
      new Object[] {
        "[123,456,[788,799,833],[[]],10,[]]",
        NestedInteger.of(
            NestedInteger.of(123),
            NestedInteger.of(456),
            NestedInteger.of(NestedInteger.of(788), NestedInteger.of(799), NestedInteger.of(833)),
            NestedInteger.of(new NestedInteger()),
            NestedInteger.of(10),
            new NestedInteger())
      }
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSolution(String text, NestedInteger expected) {
    assertThat(new MiniParser().deserialize(text), is(expected));
  }
}
