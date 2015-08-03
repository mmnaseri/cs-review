package com.mmnaseri.cs.clrs.ch21.s1;

import com.mmnaseri.cs.clrs.ch21.DisjointSet;
import com.mmnaseri.cs.clrs.ch21.Element;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 11:37 AM)
 */
public abstract class BaseDisjointSetTest {
    protected abstract DisjointSet<Element<Integer>, Integer> createSet();

    @Test
    public void testCreatingASet() throws Exception {
        final DisjointSet<Element<Integer>, Integer> set = createSet();
        final Element<Integer> first = set.create(1);
        final Element<Integer> second = set.create(2);
        assertThat(first, is(notNullValue()));
        assertThat(second, is(notNullValue()));
        assertThat(first.getValue(), is(notNullValue()));
        assertThat(first.getValue(), is(1));
        assertThat(second.getValue(), is(notNullValue()));
        assertThat(second.getValue(), is(2));
        final Element<Integer> firstRepresentative = set.find(first);
        assertThat(firstRepresentative, is(notNullValue()));
        assertThat(firstRepresentative.getValue(), is(first.getValue()));
        final Element<Integer> secondRepresentative = set.find(second);
        assertThat(secondRepresentative, is(notNullValue()));
        assertThat(secondRepresentative.getValue(), is(second.getValue()));
        final Set<Element<Integer>> elements = set.sets();
        assertThat(elements.size(), is(2));
        for (Element<Integer> element : elements) {
            assertThat(element.getValue(), isIn(Arrays.asList(1, 2)));
        }
    }

    @Test
    public void testUnion() throws Exception {
        final DisjointSet<Element<Integer>, Integer> set = createSet();
        final Element<Integer> first = set.create(1);
        final Element<Integer> second = set.create(2);
        final Element<Integer> third = set.create(3);
        assertThat(set.elements(first), contains(1));
        assertThat(set.elements(second), contains(2));
        assertThat(set.elements(third), contains(3));
        assertThat(set.sets().size(), is(3));
        set.union(first, second);
        assertThat(set.elements(first), containsInAnyOrder(1, 2));
        assertThat(set.elements(second), containsInAnyOrder(1, 2));
        assertThat(set.elements(third), contains(3));
        assertThat(set.sets().size(), is(2));
        set.union(second, third);
        assertThat(set.elements(first), containsInAnyOrder(1, 2, 3));
        assertThat(set.elements(second), containsInAnyOrder(1, 2, 3));
        assertThat(set.elements(third), containsInAnyOrder(1, 2, 3));
        assertThat(set.sets().size(), is(1));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIncompatibleSetsFind() throws Exception {
        final DisjointSet<Element<Integer>, Integer> one = createSet();
        final DisjointSet<Element<Integer>, Integer> two = createSet();
        two.find(one.create(1));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIncompatibleSetsUnion() throws Exception {
        final DisjointSet<Element<Integer>, Integer> one = createSet();
        final DisjointSet<Element<Integer>, Integer> two = createSet();
        two.union(one.create(1), one.create(2));
    }

    @Test
    public void testLargeSet() throws Exception {
        final DisjointSet<Element<Integer>, Integer> set = createSet();
        final Element<Integer> base = set.create(-1);
        final List<Element<Integer>> inserted = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            final Element<Integer> element = set.create(i);
            set.union(base, element);
            inserted.add(element);
            for (int j = 0; j < inserted.size(); j++) {
                final Element<Integer> baseRepresentative = set.find(base);
                final Element<Integer> itemRepresentative = set.find(inserted.get(i));
                final Element<Integer> elementRepresentative = set.find(element);
                assertThat(baseRepresentative, is(notNullValue()));
                assertThat(itemRepresentative, is(notNullValue()));
                assertThat(elementRepresentative, is(notNullValue()));
                assertThat(itemRepresentative.getValue(), is(baseRepresentative.getValue()));
                assertThat(elementRepresentative.getValue(), is(baseRepresentative.getValue()));
            }
        }
    }

}
