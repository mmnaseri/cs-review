package com.mmnaseri.cs.qa.monitor;

import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/27/15, 12:31 AM)
 */
public interface Monitor<D> {

    Set<? extends Feature<D>> getFeatures();

    void validate(D dataStructure);

}
