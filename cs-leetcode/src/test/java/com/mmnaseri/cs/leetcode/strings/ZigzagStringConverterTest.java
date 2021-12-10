package com.mmnaseri.cs.leetcode.strings;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ZigzagStringConverterTest {

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {"AB", 1, "AB"},
      new Object[] {"ABCDEF", 2, "ACEBDF"},
      new Object[] {"A", 1, "A"},
      new Object[] {"A", 2, "A"},
      new Object[] {"A", 3, "A"},
      new Object[] {"PAYPALISHIRING", 3, "PAHNAPLSIIGYIR"},
      new Object[] {"PAYPALISHIRING", 4, "PINALSIGYAHRPI"},
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSolution(String original, int rows, String converted) {
    assertThat(new ZigzagStringConverter().convert(original, rows), is(converted));
  }
}
