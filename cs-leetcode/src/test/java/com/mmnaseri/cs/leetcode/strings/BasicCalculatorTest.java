package com.mmnaseri.cs.leetcode.strings;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BasicCalculatorTest {

  private BasicCalculator calculator;

  @BeforeMethod
  public void setUp() {
    calculator = new BasicCalculator();
  }

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
//      new Object[] {"3+2*2", 7},
//      new Object[] {" 3/2 ", 1},
//      new Object[] {" 3+5 / 2 ", 5},
//      new Object[] {"1-1+1", 1},
//      new Object[] {"3/3 * 4  ", 4},
      new Object[] {"1*2-3/4+5*6-7*8+9/10", -24},
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSample1(String expression, int expectedOutput) {
    assertThat(calculator.eval(expression), is(expectedOutput));
  }
}
