package com.leetcode.hard;

public class LC1458 {

    public int maxDotProduct(int[] nums1, int[] nums2) {
        Integer[][][] dp = new Integer[nums1.length][nums2.length][2];
        return solve(nums1, nums2, 0, 0, 0, dp);
    }

    private int solve(int[] nums1, int[] nums2, int i, int j, int atleastOne, Integer[][][] dp) {
        if (i >= nums1.length || j >= nums2.length) {
            return atleastOne == 1 ? 0 : Integer.MIN_VALUE / 2;
        }
        if (dp[i][j][atleastOne] != null) {
            return dp[i][j][atleastOne];
        }
        int best = Integer.MIN_VALUE;
        best = Math.max(best, nums1[i] * nums2[j] + solve(nums1, nums2, i + 1, j + 1, 1, dp));
        best = Math.max(best, solve(nums1, nums2, i + 1, j, atleastOne, dp));
        best = Math.max(best, solve(nums1, nums2, i, j + 1, atleastOne, dp));

        return dp[i][j][atleastOne] = best;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{-1, -1};
        int[] nums2 = new int[]{1, 1};

        LC1458 l = new LC1458();
        System.out.println(l.maxDotProduct(nums1, nums2));
    }
}
