package com.mmnaseri.cs.clrs.ch11.s3;

import com.mmnaseri.cs.clrs.ch11.HashCalculator;
import com.mmnaseri.cs.clrs.ch11.HashTable;
import com.mmnaseri.cs.clrs.ch11.s2.BaseLargeKeysHashTableTest;
import com.mmnaseri.cs.clrs.ch11.s2.ChainingHashTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/13/15)
 */
public abstract class BaseHashCalculatorTest extends BaseLargeKeysHashTableTest {

    @Override
    protected HashTable<Integer>[] getHashTables() {
        final List<HashTable<Integer>> hashTables = new ArrayList<>();
        for (int capacity : getCapacities()) {
            for (HashCalculator calculator : getHashCalculators()) {
                hashTables.add(new ChainingHashTable<Integer>(capacity, calculator));
            }
        }
        //noinspection unchecked
        return hashTables.toArray(new HashTable[hashTables.size()]);
    }

    protected  abstract HashCalculator[] getHashCalculators();

    protected int[] getCapacities() {
        return new int[]{25};
    }
}
