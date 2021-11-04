package com.mmnaseri.cs.clrs.ch20.s3;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/1/15, 6:54 PM)
 */
@Quality(Stage.TESTED)
public class VanEmdeBoasTree {

  private final int size;
  private final int bound;
  private Integer minimum;
  private Integer maximum;
  private final VanEmdeBoasTree summary;
  private final VanEmdeBoasTree[] clusters;

  public VanEmdeBoasTree(int size) {
    if (size < 2) {
      throw new IllegalArgumentException();
    }
    this.size = size;
    bound = (int) Math.pow(2, Math.floor(Math.log(size) / Math.log(2) / 2));
    int upper = size / bound;
    this.minimum = null;
    this.maximum = null;
    if (size > 2) {
      this.clusters = new VanEmdeBoasTree[upper];
      this.summary = new VanEmdeBoasTree(upper);
      for (int i = 0; i < clusters.length; i++) {
        clusters[i] = new VanEmdeBoasTree(bound);
      }
    } else {
      this.clusters = null;
      this.summary = null;
    }
  }

  public Integer minimum() {
    return minimum;
  }

  public Integer maximum() {
    return maximum;
  }

  public boolean contains(int value) {
    if (Objects.equals(minimum, value) || Objects.equals(maximum, value)) {
      return true;
    } else if (size == 2) {
      return false;
    }
    final int high = segment(value);
    return !(high >= clusters.length || high < 0) && clusters[high].contains(offset(value));
  }

  public Integer successor(int value) {
    if (size == 2) {
      if (value == 0 && Objects.equals(maximum, 1)) {
        return 1;
      } else {
        return null;
      }
    } else if (minimum != null && value < minimum) {
      return minimum;
    } else {
      final Integer maximumLow = clusters[segment(value)].maximum();
      if (maximumLow != null && offset(value) < maximumLow) {
        final Integer offset = clusters[segment(value)].successor(offset(value));
        return index(segment(value), offset);
      } else {
        final Integer successorCluster = summary.successor(segment(value));
        if (successorCluster == null) {
          return null;
        }
        final Integer offset = clusters[successorCluster].minimum();
        return index(successorCluster, offset);
      }
    }
  }

  public Integer predecessor(int value) {
    if (size == 2) {
      if (value == 1 && Objects.equals(minimum, 0)) {
        return 0;
      } else {
        return null;
      }
    }
    if (maximum != null && value > maximum) {
      return maximum;
    } else {
      final Integer minimumLow = clusters[segment(value)].minimum();
      if (minimumLow != null && offset(value) > minimumLow) {
        final Integer offset = clusters[segment(value)].predecessor(offset(value));
        return index(segment(value), offset);
      } else {
        final Integer predecessorCluster = summary.predecessor(segment(value));
        if (predecessorCluster == null) {
          if (minimum != null && value > minimum) {
            return minimum;
          } else {
            return null;
          }
        } else {
          final Integer offset = clusters[predecessorCluster].maximum();
          return index(predecessorCluster, offset);
        }
      }
    }
  }

  public void add(int value) {
    if (minimum == null) {
      minimum = value;
      maximum = value;
      return;
    }
    if (value < minimum) {
      int temp = value;
      value = minimum;
      minimum = temp;
    }
    if (size > 2) {
      final int high = segment(value);
      if (high < 0 || high >= clusters.length) {
        return;
      }
      final VanEmdeBoasTree cluster = clusters[high];
      if (cluster.minimum() == null) {
        summary.add(high);
      }
      cluster.add(offset(value));
    }
    if (value > maximum) {
      maximum = value;
    }
  }

  public void delete(int value) {
    if (Objects.equals(minimum, maximum)) {
      minimum = null;
      maximum = null;
    } else if (size == 2) {
      if (value == 0) {
        minimum = 1;
      } else {
        minimum = 0;
      }
      maximum = minimum;
    } else {
      if (value == minimum) {
        final Integer firstCluster = summary.minimum();
        value = index(firstCluster, clusters[firstCluster].minimum());
        minimum = value;
      }
      final int high = segment(value);
      if (high < 0 || high >= clusters.length) {
        return;
      }
      final VanEmdeBoasTree cluster = clusters[high];
      cluster.delete(offset(value));
      if (cluster.minimum() == null) {
        summary.delete(high);
        if (value == maximum) {
          final Integer summaryMaximum = summary.maximum();
          if (summaryMaximum == null) {
            maximum = minimum;
          } else {
            maximum = index(summaryMaximum, clusters[summaryMaximum].maximum());
          }
        }
      } else if (value == maximum) {
        maximum = index(high, cluster.maximum());
      }
    }
  }

  private int segment(int x) {
    return x / bound;
  }

  private int offset(int x) {
    return x % bound;
  }

  private int index(int segment, int offset) {
    return segment * bound + offset;
  }
}
