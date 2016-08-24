package com.mmnaseri.cs.clrs.ch14.s3;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/20/15, 4:19 AM)
 */
public class Interval implements Comparable<Interval> {

    public static final Comparator<Interval> NATURAL_ORDER = new Comparator<Interval>() {
        @Override
        public int compare(Interval o1, Interval o2) {
            return o1.compareTo(o2);
        }
    };

    private int start;
    private int end;

    public Interval() {
    }

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public int compareTo(@SuppressWarnings("NullableProblems") Interval that) {
        return Integer.compare(this.getStart(), that.getStart());
    }

    public boolean overlaps(Interval that) {
        return (this.getStart() >= that.getStart() && this.getStart() <= that.getEnd()) ||
                (this.getEnd() >= that.getStart() && this.getEnd() <= that.getEnd());
    }

    @Override
    public String toString() {
        return "[" + getStart() + "," + getEnd() + "]";
    }
}
