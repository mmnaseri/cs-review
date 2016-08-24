package com.mmnaseri.cs.clrs.ch22.s4;

import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/3/15)
 */
public class DFSTopologicalSorterTest extends BaseTopologicalSorterTest {

    @Override
    protected TopologicalSorter<EdgeDetails, VertexDetails> getTopologicalSorter() {
        return new DFSTopologicalSorter<>();
    }

}
