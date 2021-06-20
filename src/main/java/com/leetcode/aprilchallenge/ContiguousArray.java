package com.leetcode.aprilchallenge;


/**
 * @author manoji on 4/13/20.
 */
public class ContiguousArray {

  int getSum(int[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] - pre[start - 1];
    }
    return pre[end];
  }

  public int findMaxLength(int[] nums) {
    int[] pre = new int[nums.length];
    pre[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      pre[i] = pre[i - 1] + nums[i];
    }

    int max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        int sum = getSum(pre, i, j);
        if ((j - i + 1) % 2 == 0 && sum == (j - i + 1) / 2 && (j - i + 1) > max) {
          max = j - i + 1;
        }
      }
    }

    return max == Integer.MIN_VALUE ? 0 : max;
  }


  public static void main(String args[]) {
    ContiguousArray contiguousArray = new ContiguousArray();
    System.out.println(contiguousArray.findMaxLength(new int[]{1}));
  }
}
