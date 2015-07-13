package com.mmnaseri.cs.ds.clrs.ch11.s3;

import com.mmnaseri.cs.ds.clrs.ch11.HashCalculator;
import com.mmnaseri.cs.ds.clrs.ch11.HashTable;
import com.mmnaseri.cs.ds.clrs.ch11.s2.ChainingHashTable;
import com.mmnaseri.cs.ds.clrs.ch11.s2.ChainingHashTableTest;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/13/15)
 */
public abstract class BaseHashCalculatorTest extends ChainingHashTableTest {
    @Override
    protected HashTable<Integer> getHashTable() {
        return new ChainingHashTable<>(getCapacity(), getHashCalculator());
    }

    protected  abstract HashCalculator getHashCalculator();

    protected int getCapacity() {
        return 25;
    }
}
