package com.leetcode.contest;

import java.util.Arrays;
import java.util.Comparator;

public class MinEnergy {

  public int minimumEffort(int[][] tasks) {

    Arrays.sort(tasks, Comparator.comparingInt(o -> o[1] - o[0]));
    int end = 0;
    for (int i = 0; i < tasks.length; i++) {
      end += tasks[i][1];
    }

    int start = 0;
    int ans = 0;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (canFinish(tasks, mid)) {
        ans = mid;
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return ans;
  }

  private boolean canFinish(int[][] tasks, int energy) {

/*    for (int[] row : tasks) {
      System.out.println(Arrays.toString(row));
    }*/
    for (int i = tasks.length - 1; i >= 0; i--) {
      if (tasks[i][1] > energy) {
        return false;
      } else {
        energy -= tasks[i][0];
      }
    }
    return true;
  }

  public static void main(String args[]) {
    MinEnergy m = new MinEnergy();
    int[][] tasks = new int[][]{
        {1, 2}, {2, 4}, {4, 8}
    };
    int[][] tasks1 = new int[][]{
        {1, 3}, {2, 4}, {10, 11}, {10, 12}, {8, 9}
    };
    System.out.println(m.canFinish(tasks1, 32));
  }

}
