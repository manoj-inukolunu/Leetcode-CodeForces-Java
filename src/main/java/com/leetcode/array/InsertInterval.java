package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 6/2/20.
 */
public class InsertInterval {

  public class Interval {

    public int start;
    public int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }


  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<Interval> intervalsList = new ArrayList();
    for (int i = 0; i < intervals.length; i++) {
      intervalsList.add(new Interval(intervals[i][0], intervals[i][1]));
    }

    Interval toInsert = new Interval(newInterval[0], newInterval[1]);

    int insertPos = getPositionToInsert(intervalsList, toInsert);

    merge(intervalsList);

    return convert(intervalsList);

  }

  private int[][] convert(List<Interval> intervalsList) {
    return new int[0][];
  }

  private void merge(List<Interval> intervalsList) {

  }

  private int getPositionToInsert(List<Interval> list, Interval toInsert) {
    int low = 0, high = list.size();
    while (low <= high) {
      int mid = low + (high - low) / 2;

    }
    return 0;
  }


}
