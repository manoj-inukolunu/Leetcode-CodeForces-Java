package com.leetcode.greedy;

public class MinimumDeletionCost {

  public int minCost(String s, int[] cost) {
    int count = 0, max = Integer.MIN_VALUE, i = 0, sum = 0;
    while (i < s.length()) {
      while (i + 1 < s.length() && (s.charAt(i) == s.charAt(i + 1))) {
        max = Math.max(max, cost[i]);
        sum += cost[i];
        i++;
      }
      if (i + 1 >= s.length()) {
        break;
      } else {
        max = Math.max(max, cost[i]);
        sum += cost[i];
      }
      if (max != Integer.MIN_VALUE) {
        count += (sum - max);
      }
      max = 0;
      sum = 0;
      i++;
    }
    if (s.charAt(s.length() - 1) == s.charAt(s.length() - 2)) {
      max = Math.max(max, cost[s.length() - 1]);
      sum += cost[s.length() - 1];
      count += (sum - max);
    }
    return count;
  }


  public static void main(String args[]) {
    MinimumDeletionCost m = new MinimumDeletionCost();
    System.out.println(m.minCost("aabaa", new int[]{1, 2, 3, 4, 1}));
  }

}
