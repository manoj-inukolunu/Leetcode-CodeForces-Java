package com.leetcode.random;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class RemoveCoveredIntervals {

  public int removeCoveredIntervals(int[][] intervals) {
    if (intervals.length == 0) {
      return 0;
    }
    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        if (o1[1] == o2[1]) {
          return -Integer.compare(o1[0], o2[0]);
        }
        return Integer.compare(o1[1], o2[1]);
      }
    });
    Stack<int[]> stack = new Stack<>();
    stack.push(intervals[0]);
    int count = 0;
    for (int i = 1; i < intervals.length; i++) {
      while (!stack.isEmpty() && covers(intervals[i], stack.peek())) {
        count++;
        stack.pop();
      }
      stack.push(intervals[i]);
    }

    return count;

  }

  private boolean covers(int[] interval, int[] peek) {
    return interval[0] <= peek[0] && interval[1] >= peek[1];
  }

}
