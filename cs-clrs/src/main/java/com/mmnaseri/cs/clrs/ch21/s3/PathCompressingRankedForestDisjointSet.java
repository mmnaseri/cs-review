package com.mmnaseri.cs.clrs.ch21.s3;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 12:03 PM)
 */
@Quality(Stage.TESTED)
public class PathCompressingRankedForestDisjointSet<I> extends RankedForestDisjointSet<I> {

  @Override
  public RankedTreeElement<I> find(RankedTreeElement<I> item) {
    control(item);
    if (!item.isRoot()) {
      item.setParent(find(item.getParent()));
      return item.getParent();
    } else {
      return item;
    }
  }
}
