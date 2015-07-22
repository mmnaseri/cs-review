package com.mmnaseri.cs.clrs.ch16.s1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/22/15)
 */
public abstract class BaseActivitySelectorTest {

    protected abstract ActivitySelector getActivitySelector();

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                new Object[]{"incompatible, second bigger", new int[]{1, 3}, new int[]{4, 7}, new int[]{1}},
                new Object[]{"incompatible, first bigger", new int[]{1, 3}, new int[]{9, 7}, new int[]{0}},
                new Object[]{"multiple", new int[]{1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12}, new int[]{4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16}, new int[]{0, 3, 8, 10}},
        };
    }

    @Test(dataProvider = "dataProvider", invocationCount = 10)
    public void testFunctionality(@SuppressWarnings("UnusedParameters") String name, int[] start, int[] finish, int[] expected) throws Exception {
        if (start == null || finish == null || start.length != finish.length) {
            throw new IllegalStateException();
        }
        final Activity[] activities = new Activity[start.length];
        for (int i = 0; i < activities.length; i++) {
            activities[i] = new Activity(start[i], finish[i]);
        }
        final ActivitySelector selector = getActivitySelector();
        final Set<Integer> indices = selector.select(activities);
        if (expected == null) {
            assertThat(indices, is(nullValue()));
        } else {
            assertThat(indices, hasSize(expected.length));
            for (int index : expected) {
                assertThat(indices.contains(index), is(true));
                indices.remove(index);
            }
            assertThat(indices, is(empty()));
        }
    }

}
