package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinimumNumberOfArrows {

  class Pair {

    int start;
    int end;

    public Pair(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public boolean overlaps(Pair next) {
      return Math.max(this.start, next.start) <= Math.min(this.end, next.end);
    }

    public Pair getOverLapped(Pair next) {
      return new Pair(Math.max(this.start, next.start), start + Math.min(this.end, next.end) - Math.max(this.start, next.start));
    }
  }

  public int findMinArrowShots(int[][] points) {
    List<Pair> list = new ArrayList<>();

    for (int i = 0; i < points.length; i++) {
      list.add(new Pair(points[i][0], points[i][1]));
    }
    Collections.sort(list, Comparator.comparingInt(o -> o.start));

    Pair overlap = null;
    int count = 0;
    for (int i = 1; i < list.size(); i++) {
      Pair curr = list.get(i);
      Pair prev = list.get(i - 1);
      if (overlap == null && curr.overlaps(prev)) {
        overlap = curr.getOverLapped(prev);
        if (i == list.size() - 1) {
          count++;
        }
      } else if (overlap != null && curr.overlaps(overlap)) {
        overlap = curr.getOverLapped(overlap);
        if (i == list.size() - 1) {
          count++;
        }
      } else if (overlap != null && !curr.overlaps(overlap)) {
        if (i == list.size() - 1) {
          count += 2;
        } else {
          count++;
        }
        overlap = null;
      } else if (overlap == null && !curr.overlaps(prev)) {
        count++;
      }
    }
    if (count == 0 && overlap == null) {
      return list.size();
    }
    if (count == 0 && overlap != null) {
      count++;
    }
    return count;
  }


  public static void main(String args[]) {
    MinimumNumberOfArrows m = new MinimumNumberOfArrows();

    int[][] arr = new int[][]{
        {35005211, 56600579}, {94702567, 121658996}, {36465782, 97487312}, {78722862, 112387985}, {45174067, 113877202}, {1513929, 3493731},
        {15634022, 51357080},
        {69133069, 95031236},
        {59961393, 148979849}, {28175011, 84653053}
    };

    System.out.println(m.findMinArrowShots(arr));
  }
}
