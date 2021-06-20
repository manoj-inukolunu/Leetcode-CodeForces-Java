package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;

public class FindCelebrity {

  private boolean knows(int a, int b) {
    return false;
  }

  public int findCelebrity(int n) {
    int[] indegree = new int[n];
    int[] outdegree = new int[n];

    Arrays.fill(indegree, 0);
    Arrays.fill(outdegree, 0);

    for (int i = 0; i < n; i++) {
      if (outdegree[i] == 0) {
        for (int j = 0; j < n; j++) {
          if (i != j) {
            if (knows(i, j)) {
              outdegree[i]++;
              indegree[j]++;
            }
            if (knows(j, i)) {
              outdegree[j]++;
              indegree[i]++;
            }
          }
        }
      }

    }

    System.out.println(Arrays.toString(indegree));
    System.out.println(Arrays.toString(outdegree));

    for (int i = 0; i < n; i++) {
      if (indegree[i] == n - 1 && outdegree[i] == 0) {
        return i;
      }
    }

    return -1;

  }

}
