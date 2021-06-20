package com.leetcode.s60day;

public class MinSubArray {

  public int minSubArrayLen(int s, int[] nums) {
    int sum = 0, start = 0, end = 0, ans = Integer.MAX_VALUE;
    while (end < nums.length) {
      if (sum < s) {
        sum += nums[end];
        end++;
      } else {
        ans = Math.min(ans, end - start - 1);
        sum -= nums[start];
        start++;
      }
    }
    while (sum > s) {
      sum -= nums[start];
      start++;
      if (sum >= s) {
        ans = Math.min(ans, end - start - 1);
      }
    }

    return ans == Integer.MAX_VALUE ? 0 : ans;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 2, 3, 4, 5};
    MinSubArray m = new MinSubArray();
    System.out.println(m.minSubArrayLen(11, arr));
  }

}
