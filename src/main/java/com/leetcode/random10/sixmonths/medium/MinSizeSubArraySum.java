package com.leetcode.random10.sixmonths.medium;

/**
 * @author manoji on 7/19/20.
 */
public class MinSizeSubArraySum {

  public int minSubArrayLen(int s, int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    int start = 0, end = 0;
    int min = Integer.MAX_VALUE;
    int sum = nums[start];
    while (true) {
      if (sum < s) {
        end++;
        if (end >= nums.length) {
          break;
        }
        sum += nums[end];
      } else {
        min = Math.min(end - start, min) + 1;
        sum -= nums[start];
        start++;
        if (start >= nums.length) {
          break;
        }
      }
    }
    return min;

  }


  public static void main(String args[]) {
    int arr[] = new int[]{2, 3, 1, 2, 4, 3};
    MinSizeSubArraySum m = new MinSizeSubArraySum();

    System.out.println(m.minSubArrayLen(7, arr));
  }

}
