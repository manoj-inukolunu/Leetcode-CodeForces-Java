package com.leetcode.hard;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LC996 {


    private int go(int[] nums, int idx, int mask, int fullMask, int[][] dp) {
        if (mask == fullMask) {
            return 1;
        }
        if (dp[idx][mask] != -1) {
            return dp[idx][mask];
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((mask & (1 << i)) == 0 && isPerfectSquare(nums[idx] + nums[i])) {
                count += go(nums, i, mask | (1 << i), fullMask, dp);
            }
        }
        dp[idx][mask] = count;
        return count;
    }

    private boolean isPerfectSquare(int a) {
        int val = (int) Math.sqrt(a);
        return val * val == a;
    }

    public int numSquarefulPerms(int[] nums) {
        int count = 0;
        int val = (1 << nums.length) - 1;
        int[][] dp = new int[5000][5000];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < nums.length; i++) {
            count += go(nums, i, (1 << i), val, dp);
        }
        int[] fact = new int[13];
        fact[1] = 1;
        for (int i = 2; i < fact.length; i++) {
            fact[i] = fact[i - 1] * i;
        }
        Map<Integer, Long> freq = Arrays.stream(nums).boxed().collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (long l : freq.values()) {
            count /= fact[(int) l];
        }
        return count;
    }


    public static void main(String args[]) {
        LC996 l = new LC996();
        int[] arr = new int[]{2, 2, 2};
        System.out.println(l.numSquarefulPerms(arr));

    }


}
