package com.leetcode.random3;

public class MaxDot {

  public int maxDotProduct(int[] nums1, int[] nums2) {
    return dfs(nums1, nums2, 0, 0);
  }

  private int dfs(int[] nums1, int[] nums2, int a, int b) {
    int curr = Integer.MIN_VALUE;
    if (a + 1 < nums1.length && b + 1 < nums2.length) {
      int val = dfs(nums1, nums2, a + 1, b + 2);
      curr = nums1[a] * nums2[b] + (val != Integer.MIN_VALUE ? val : 0);
    }
    int c1 = Integer.MIN_VALUE;
    if (a + 1 < nums1.length) {
      c1 = dfs(nums1, nums2, a + 1, b);
    }
    int c2 = Integer.MIN_VALUE;
    if (b + 1 < nums2.length) {
      c2 = dfs(nums1, nums2, a, b + 1);
    }
    return Math.max(curr, Math.max(c1, c2));
  }

  public static void main(String args[]) {
    MaxDot m = new MaxDot();
    System.out.println(m.maxDotProduct(new int[]{-5, -1, -2}, new int[]{3, 3, 5, 5}));
  }


}
