package com.leetcode.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 5/23/20.
 */
public class IntervalListIntersection {

  class Interval {

    int start;
    int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;

    }
  }

  public int[][] intervalIntersection(int[][] A, int[][] B) {
    if (A.length == 0 || B.length == 0) {
      return new int[][]{};
    }
    List<Interval> intervalA = new ArrayList<>();
    List<Interval> intervalB = new ArrayList<>();
    buildIntervals(A, B, intervalA, intervalB);
    List<Interval> ans = new ArrayList<>();
    int a = 0, b = 0;
    while (a < intervalA.size() && b < intervalB.size()) {
      Interval aInt = intervalA.get(a);
      Interval bInt = intervalB.get(b);
      Interval intersectedInterval = intersect(aInt, bInt);
      if (intersectedInterval != null) {
        ans.add(intersectedInterval);
      }
      if (aInt.end < bInt.end) {
        a++;
      } else {
        b++;
      }
    }

    int[][] res = new int[ans.size()][2];

    for (int i = 0; i < ans.size(); i++) {
      res[i][0] = ans.get(i).start;
      res[i][1] = ans.get(i).end;
    }
    return res;
  }

  public Interval intersect(Interval a, Interval b) {
    if (b.start > a.end || a.start > b.end) {
      return null;
    }
    return new Interval(Math.max(a.start, b.start), Math.min(a.end, b.end));
  }

  private void buildIntervals(int[][] a, int[][] b, List<Interval> intervalA, List<Interval> intervalB) {
    for (int i = 0; i < a.length; i++) {
      intervalA.add(new Interval(a[i][0], a[i][1]));
    }
    for (int i = 0; i < b.length; i++) {
      intervalB.add(new Interval(b[i][0], b[i][1]));
    }
  }


  public static void main(String args[]) {
    int[][] A = new int[][]{
        {0, 5}, {12, 14}, {15, 18}
    };

    int[][] B = new int[][]{
        {11, 15}, {18, 19}
    };

    IntervalListIntersection i = new IntervalListIntersection();
		/*int[][] arr = i.intervalIntersection(A, B);

		for (int[] row : arr) {
			System.out.println(Arrays.toString(row));
		}*/

//		i.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
  }

}
