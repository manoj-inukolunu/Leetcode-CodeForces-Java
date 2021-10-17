package com.leetcode.hard;

import java.util.Arrays;

public class LC1879 {


    public int minimumXORSum(int[] nums1, int[] nums2) {
        int[] dp = new int[(1 << nums2.length) + 1];

        Arrays.fill(dp, -1);

        return solve(nums1, nums2, 0, dp);
    }

    private int solve(int[] nums1, int[] nums2, int state, int[] dp) {
        int idx = Integer.bitCount(state);
        if (idx >= nums1.length) {
            return 0;
        }
        if (dp[state] != -1) {
            return dp[state];
        }
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < nums2.length; i++) {
            if ((state & (1 << i)) == 0) {
                best = Math.min(best, (nums1[idx] ^ nums2[i]) + solve(nums1, nums2, state | (1 << i), dp));
            }
        }
        return dp[state] = best;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{2, 3};

        LC1879 l = new LC1879();
        System.out.println(l.minimumXORSum(nums1, nums2));
    }


}
