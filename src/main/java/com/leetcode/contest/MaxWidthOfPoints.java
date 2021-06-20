package com.leetcode.contest;

import java.util.Arrays;
import java.util.Comparator;

public class MaxWidthOfPoints {

  public int maxWidthOfVerticalArea(int[][] points) {

    Arrays.sort(points, Comparator.comparingInt(o -> o[0]));

    int max = Integer.MIN_VALUE;

    for (int i = 1; i < points.length; i++) {
      max = Math.max(max, Math.abs(points[i][0] - points[i - 1][0]));
    }

    return max;
  }


}
