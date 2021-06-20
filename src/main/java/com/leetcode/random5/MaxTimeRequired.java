package com.leetcode.random5;

public class MaxTimeRequired {

  int min = Integer.MAX_VALUE;

  public int minimumTimeRequired(int[] jobs, int k) {

    int[] cost = new int[k + 1];
    dfs(jobs, 0, cost, k);
    return min;
  }

  private void dfs(int[] jobs, int idx, int[] cost, int k) {
    if (idx >= jobs.length) {
      min = Math.min(max(cost), min);
      return;
    }
    for (int i = 1; i <= k; i++) {
      if (max(cost) < min) {
        cost[i] += jobs[idx];
        dfs(jobs, idx + 1, cost, k);
        cost[i] -= jobs[idx];
        if (cost[i] == 0) {
          break;
        }
      }
    }
  }

  private int max(int[] wSumMap) {
    int max = 0;
    for (int key : wSumMap) {
      max = Math.max(key, max);
    }
    return max;
  }

  public static void main(String args[]) {
    int[] jobs = new int[]{3, 2, 3};
    int k = 3;
    MaxTimeRequired m = new MaxTimeRequired();
    System.out.println(m.minimumTimeRequired(jobs, k));
  }

}
