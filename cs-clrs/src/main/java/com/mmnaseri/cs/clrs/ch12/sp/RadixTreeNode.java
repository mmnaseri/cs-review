package com.mmnaseri.cs.clrs.ch12.sp;

import com.mmnaseri.cs.clrs.ch10.s4.impl.BinaryTreeNode;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/18/15, 10:08 PM)
 */
public class RadixTreeNode extends BinaryTreeNode<Bit> {

    private boolean lastBit;

    public RadixTreeNode() {
        lastBit = false;
    }

    public void setLastBit(boolean lastBit) {
        this.lastBit = lastBit;
    }

    public boolean isLastBit() {
        return lastBit;
    }

}
