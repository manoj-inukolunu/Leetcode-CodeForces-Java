package com.leetcode.DP.hard;

import java.util.Arrays;
import java.util.HashMap;

public class LC446 {


    public int numberOfArithmeticSlices(int[] nums) {

        HashMap<Integer, Integer>[] dp = new HashMap[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = new HashMap<>();
            for (int j = i - 1; j >= 0; j--) {
                int val = dp[i].getOrDefault(nums[i] - nums[j], 0);
                dp[i].put(nums[i] - nums[j], val + 1);
            }
        }

        HashMap<Integer, Integer>[] dp1 = new HashMap[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int diff = nums[i] - nums[j];
            }
        }
        int count = 0;

        /*for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int diff = nums[i] - nums[j];
                int val = dp[i].getOrDefault(diff, 0);
                if (dp[j].get(diff) != null && dp[j].get(diff) > 0) {
                    dp[i].put(diff, val + dp[j].get(diff));
                }
            }
        }*/

        System.out.println(Arrays.toString(dp));

        return count;

    }

    public static void main(String args[]) {
        LC446 l = new LC446();
        int[] arr = new int[]{2, 2, 4, 6, 8};
        l.numberOfArithmeticSlices(arr);
    }
}
