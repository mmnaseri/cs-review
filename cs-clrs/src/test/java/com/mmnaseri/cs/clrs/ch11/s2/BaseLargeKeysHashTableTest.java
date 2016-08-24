package com.mmnaseri.cs.clrs.ch11.s2;

import com.mmnaseri.cs.clrs.ch11.BaseHashTableTest;

import java.util.Map;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/14/15, 1:10 AM)
 */
public abstract class BaseLargeKeysHashTableTest extends BaseHashTableTest {
    @Override
    protected Map<String, TestCase> getTestCases() {
        final Map<String, TestCase> testCases = super.getTestCases();
        testCases.put("largeKeys,smallValues", new TestCase(new Integer[]{1237812366, 4567895, 81231231, 976553, 21233, 76753245, 112313}, new Integer[]{8, 5, 7, 3, 2, 1, 3}, new Integer[]{1, 2, 3, 4, 5, 6, 7}));
        testCases.put("largeKeys,largeValues", new TestCase(new Integer[]{7657, 12343, 7982, 9345, 12312310}, new Integer[]{45346231, 19283765, 938475268, 3456789, 1982376718}, new Integer[]{1, 2, 3, 4, 5}));
        testCases.put("largeKeys,duplicateValues", new TestCase(new Integer[]{7, 42, 833, 3444, 25555}, new Integer[]{1, 1, 1, 2, 2}, new Integer[]{1, 2, 3, 4, 5}));
        testCases.put("largeKeys,negativeValues", new TestCase(new Integer[]{6217635, 5123, 39486754, 712312}, new Integer[]{-5, -1, -8, -1}, new Integer[]{1, 2, 3, 4}));
        testCases.put("largeKeys,zeroValue", new TestCase(new Integer[]{1128735, 812, 44675, 754876}, new Integer[]{0, 2, 0, 3}, new Integer[]{1, 2, 3, 4}));
        testCases.put("largeKeys,nullValue", new TestCase(new Integer[]{1123871263, 21287361, 67312335, 986574}, new Integer[]{0, null, 1, null}, new Integer[]{1, 2, 3, 4}));
        testCases.put("largeKeys,infinityValue", new TestCase(new Integer[]{1498756, 61265, 48765, 7123}, new Integer[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 2}, new Integer[]{1, 2, 3, 4}));
        testCases.put("largeKeys.duplicates,smallValues", new TestCase(new Integer[]{1458736, 1458736, 1458736, 348765}, new Integer[]{8, 7, 6, 5}, new Integer[]{1, 1, 1, 2}));
        testCases.put("largeKeys.duplicates,largeValues", new TestCase(new Integer[]{438756, 67098, 438756, 67098}, new Integer[]{545678123, 912873, 912109, 7537}, new Integer[]{1, 2, 2, 2}));
        return testCases;
    }
}
