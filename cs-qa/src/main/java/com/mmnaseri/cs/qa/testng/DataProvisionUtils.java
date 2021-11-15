package com.mmnaseri.cs.qa.testng;

public final class DataProvisionUtils {

  private DataProvisionUtils() throws IllegalAccessException {
    throw new IllegalAccessException("This type shouldn't be instantiated.");
  }

  public static Object[][] merge(Object[][] originalCases, Object[][] additionalCases) {
    Object[][] allCases = new Object[originalCases.length + additionalCases.length][];
    System.arraycopy(originalCases, 0, allCases, 0, originalCases.length);
    System.arraycopy(additionalCases, 0, allCases, originalCases.length, additionalCases.length);
    return allCases;
  }
}
