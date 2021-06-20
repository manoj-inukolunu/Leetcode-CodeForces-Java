package com.leetcode.random7;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxAvgPass {

  public double maxAverageRatio(int[][] classes, int extraStudents) {
    PriorityQueue<Integer[]> queue = new PriorityQueue<>(new Comparator<Integer[]>() {
      @Override
      public int compare(Integer[] o1, Integer[] o2) {
        double o1Curr = (double) o1[0] / o1[1];
        double o1Next = (double) (o1[0] + 1) / (o1[1] + 1);

        double o2Curr = (double) o2[0] / o2[1];
        double o2Next = (double) (o2[0] + 1) / (o2[1] + 1);

        return -Double.compare(o1Next - o1Curr, o2Next - o2Curr);
      }
    });

    for (int[] c : classes) {
      if (c[0] < c[1]) {
        queue.add(new Integer[]{c[0], c[1]});
      }
    }
    while (!queue.isEmpty()) {
      Integer[] curr = queue.poll();
      if (extraStudents == 0) {
        queue.add(curr);
        break;
      }
      curr[0]++;
      curr[1]++;
      extraStudents--;
      queue.add(curr);
    }
    double ans = 0.0d;
    while (!queue.isEmpty()) {
      Integer[] curr = queue.poll();
      double currPass = (double) curr[0] / curr[1];
      ans += currPass;
    }
    for (int[] c : classes) {
      if (c[0] == c[1]) {
        ans++;
      }
    }
    ans /= classes.length;
    return ans;
  }

  public static void main(String[] args) {
    int[][] classes = new int[][]{
        //{1, 2}, {3, 5}, {2, 2}
        {1, 2}, {1, 3}, {4, 5}, {1, 5}
    };
    MaxAvgPass m = new MaxAvgPass();
    System.out.println(m.maxAverageRatio(classes, 4));
  }

}
