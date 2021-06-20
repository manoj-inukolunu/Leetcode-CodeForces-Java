package com.leetcode.random5;

public class LargestNumber {


  public String largestNumber(int[] cost, int target) {
    return dfs(cost, 0, target);
  }

  private String dfs(int[] cost, int idx, int target) {
    if (idx >= cost.length) {
      if (target == 0) {
        return "0";
      }
      return null;
    }
    String max = "A";
    if (target - cost[idx] >= 0) {
      String ret = dfs(cost, idx, target - cost[idx]);
      if (ret != null) {
        ret += idx;
        max = (ret.compareTo(max) > 0) ? ret : max;
      }
    }
    String ret = dfs(cost, idx + 1, target);
    if (ret != null) {
      ret += idx;
      max = (ret.compareTo(max) > 0) ? ret : max;
    }
    return max;
  }

  public static void main(String args[]) {
    LargestNumber l = new LargestNumber();
    System.out.println(l.largestNumber(new int[]{4, 3, 2, 5, 6, 7, 2, 5, 5}, 9));
  }

}
