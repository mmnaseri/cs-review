package com.mmnaseri.cs.clrs.ch16.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/22/15)
 */
public class Activity implements Comparable<Activity> {

    private final int start;
    private final int finish;


    public Activity(int start, int finish) {
        if (finish < start) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.finish = finish;
    }

    public int getStart() {
        return start;
    }

    public int getFinish() {
        return finish;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(Activity that) {
        return Integer.compare(this.getFinish(), that.getFinish());
    }

    public boolean isCompatible(Activity activity) {
        return getStart() >= activity.getFinish() || getFinish() <= activity.getStart();
    }

}
