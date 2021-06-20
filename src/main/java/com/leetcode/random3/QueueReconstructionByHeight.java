package com.leetcode.random3;

import java.util.Arrays;
import java.util.Comparator;

public class QueueReconstructionByHeight {

  public int[][] reconstructQueue(int[][] people) {

    Arrays.sort(people, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        if (o1[0] == o2[0]) {
          return Integer.compare(o1[1], o2[1]);
        }
        return Integer.compare(o1[0], o2[0]);
      }
    });

    int[][] ans = new int[people.length][people[0].length];

    for (int[] row : ans) {
      Arrays.fill(row, -1);
    }

    for (int i = 0; i < people.length; i++) {
      int[] curr = people[i];
      int count = curr[1];
      for (int j = 0; j < ans.length; j++) {
        if (ans[j][0] == -1 && count == 0) {
          ans[j][0] = curr[0];
          ans[j][1] = curr[1];
          break;
        } else if (ans[j][0] == -1 || ans[j][0] >= people[i][0]) {
          count--;
        }
      }
    }

    return ans;
  }

}
