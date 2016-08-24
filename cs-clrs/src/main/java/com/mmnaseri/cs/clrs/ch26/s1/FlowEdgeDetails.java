package com.mmnaseri.cs.clrs.ch26.s1;

import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/26/15)
 */
public interface FlowEdgeDetails extends EdgeDetails {

    int getCapacity();

    int getFlow();

}
