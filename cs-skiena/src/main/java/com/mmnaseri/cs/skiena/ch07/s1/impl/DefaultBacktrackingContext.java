package com.mmnaseri.cs.skiena.ch07.s1.impl;

import com.mmnaseri.cs.skiena.ch07.s1.BacktrackingContext;

import java.util.Collections;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:23 PM)
 */
public class DefaultBacktrackingContext<E, D> implements BacktrackingContext<E, D> {

    private boolean stopped = false;
    private final D data;
    private final List<E> current;

    public DefaultBacktrackingContext(D data, List<E> current) {
        this.data = data;
        this.current = Collections.unmodifiableList(current);
    }

    @Override
    public void stop() {
        stopped = true;
    }

    @Override
    public boolean isStopped() {
        return stopped;
    }

    @Override
    public List<E> current() {
        return current;
    }

    @Override
    public int size() {
        return current.size();
    }

    @Override
    public D data() {
        return data;
    }

}
