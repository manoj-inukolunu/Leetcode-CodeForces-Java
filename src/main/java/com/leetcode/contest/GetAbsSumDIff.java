package com.leetcode.contest;

import java.util.Arrays;

public class GetAbsSumDIff {

  public int[] getSumAbsoluteDifferences(int[] nums) {
    int[] leftArr = new int[nums.length];
    leftArr[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      leftArr[i] = leftArr[i - 1] + nums[i];
    }
    int[] ans = new int[nums.length];
    ans[0] = getSum(leftArr, 0, nums.length - 1) - ((nums.length) * nums[0]);
    for (int i = 1; i < nums.length - 1; i++) {
      int left = nums[i] * (i) - getSum(leftArr, 0, i - 1);
      int right = getSum(leftArr, i + 1, nums.length - 1) - ((nums.length - i - 1) * nums[i]);
      ans[i] = Math.abs(left) + Math.abs(right);
    }
    ans[ans.length - 1] = (nums[nums.length - 1] * nums.length) - (getSum(leftArr, 0, nums.length - 1));

    return ans;


  }

  int getSum(int[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] - pre[start - 1];
    }
    return pre[end];
  }

  public static void main(String args[]) {
    int[] arr = new int[]{2, 3, 5};
    GetAbsSumDIff g = new GetAbsSumDIff();
    arr = g.getSumAbsoluteDifferences(arr);
    System.out.println(Arrays.toString(arr));
  }

}
