package com.mmnaseri.cs.clrs.ch15.sp;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15, 9:50 PM)
 */
public enum EditOperationType {

    COPY(1, 1),
    REPLACE(1, 1),
    DELETE(1, 0),
    INSERT(0, 1),
    TWIDDLE(2, 2);

    private final int source;
    private final int target;

    EditOperationType(int source, int target) {
        this.source = source;
        this.target = target;
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

}
