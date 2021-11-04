package com.mmnaseri.cs.clrs.ch16.s1;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/22/15)
 */
public class IndexedActivity extends Activity {

  private final int index;

  public IndexedActivity(Activity activity, int index) {
    this(activity.getStart(), activity.getFinish(), index);
  }

  public IndexedActivity(int start, int finish, int index) {
    super(start, finish);
    this.index = index;
  }

  public int getIndex() {
    return index;
  }

  public static IndexedActivity[] index(Activity... activities) {
    final IndexedActivity[] result = new IndexedActivity[activities.length];
    for (int i = 0; i < activities.length; i++) {
      result[i] = new IndexedActivity(activities[i], i);
    }
    return result;
  }
}
