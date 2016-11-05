package com.mmnaseri.cs.clrs.ch32.s1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/4/16, 6:35 PM)
 */
public abstract class BaseStringMatcherTest {

    protected abstract StringMatcher getStringMatcher();

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = ".*haystack.*")
    public void testFindingInNull() throws Exception {
        getStringMatcher().indexOf("123", null);
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = ".*needle.*")
    public void testFindingNullInString() throws Exception {
        getStringMatcher().indexOf(null, "");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*needle.*")
    public void testFindingEmptyString() throws Exception {
        getStringMatcher().indexOf("", "123");
    }

    @DataProvider
    public Object[][] cases() {
        return new Object[][]{
                new Object[]{"1", "", new Integer[0]},
                new Object[]{"1", "12", new Integer[]{0}},
                new Object[]{"1", "23", new Integer[0]},
                new Object[]{"123", "456", new Integer[0]},
                new Object[]{"123", "456123456123", new Integer[]{3, 9}}
        };
    }

    @Test(dataProvider = "cases")
    public void testFindingString(String needle, String haystack, Integer[] offset) throws Exception {
        final Integer[] indices = getStringMatcher().indexOf(needle, haystack);
        assertThat(indices, is(offset));
        for (Integer index : indices) {
            assertThat(index, is(greaterThanOrEqualTo(0)));
            assertThat(index, is(lessThan(haystack.length())));
            assertThat(haystack.substring(index), startsWith(needle));
        }
    }

}
