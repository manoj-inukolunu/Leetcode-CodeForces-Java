/*
package com.leetcode.random3;

public class StoneGameVII {


  class Pair {

    int alice, bob;

    public Pair(int alice, int bob) {
      this.alice = alice;
      this.bob = bob;
    }
  }

  public int stoneGameVII(int[] stones) {
    int[] pre = new int[stones.length];
    pre[0] = stones[0];
    for (int i = 1; i < stones.length; i++) {
      pre[i] = pre[i - 1] + stones[i];
    }
    Pair[][][] dp = new Pair[stones.length + 1][stones.length + 1][2];
    Pair p = dfs(pre, 0, pre.length - 1, 0, dp);
    return p.alice - p.bob;
  }

  int getSum(int[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] - pre[start - 1];
    }
    return pre[end];
  }

  private Pair dfs(int[] pre, int start, int end, int player, Pair[][][] dp) {
    if (start >= end) {
      dp[start][end][player] = new Pair(0, 0);
      return new Pair(0, 0);
    }
    if (dp[start][end][player] != null) {
      return dp[start][end][player];
    }
    if (player == 0) {
      Pair pStart = dfs(pre, start + 1, end, 1, dp);
      pStart.alice += getSum(pre, start + 1, end);
      Pair pEnd = dfs(pre, start, end - 1, 1, dp);
      pEnd.alice += getSum(pre, start, end - 1);
      Pair ret;
      if (pStart.alice - pStart.bob > pEnd.alice - pEnd.bob) {
        ret = pStart;
      } else {
        ret = pEnd;
      }
      dp[start][end][player] = ret;
      return ret;
    }

    Pair pStart = dfs(pre, start + 1, end, 0, dp);
    pStart.bob += getSum(pre, start + 1, end);
    Pair pEnd = dfs(pre, start, end - 1, 0, dp);
    pEnd.bob += getSum(pre, start, end - 1);
    Pair ret;
    if (pStart.alice - pStart.bob < pEnd.alice - pEnd.bob) {
      ret = pStart;
    } else {
      ret = pEnd;
    }
    dp[start][end][player] = ret;
    return dp[start][end][player];
  }

  class Solution {

    public int stoneGameVII(int[] stones) {
      int[] pre = new int[stones.length];
      pre[0] = stones[0];
      for (int i = 1; i < stones.length; i++) {
        pre[i] = pre[i - 1] + stones[i];
      }
      return dfs(pre, 0, pre.length - 1, 1, 0);
    }

    int getSum(int[] pre, int start, int end) {
      if (start > 0) {
        return pre[end] - pre[start - 1];
      }
      return pre[end];
    }

    private int dfs(int[] pre, int start, int end, int player, int sum) {
      if (start >= end) {
        return sum;
      }
      if (player == 1) {
        return Math
            .max(dfs(pre, start + 1, end, 2, a + getSum(pre, start + 1, end), b), dfs(pre, start, end - 1, 2, a + getSum(pre, start, end - 1), b));

      }
      return Math
          .min(dfs(pre, start + 1, end, 1, a, b + getSum(pre, start + 1, end)), dfs(pre, start, end - 1, 1, a, b + getSum(pre, start, end - 1)));
    }
  }

  public static void main(String args[]) {
    StoneGameVII s = new StoneGameVII();
    System.out.println(s.stoneGameVII(new int[]{5, 3, 1, 4, 2}));
  }


}
*/
