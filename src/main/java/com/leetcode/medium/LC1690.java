package com.leetcode.medium;

public class LC1690 {


    int getSum(int[] pre, int start, int end) {
        if (start > 0) {
            return pre[end] - pre[start - 1];
        }
        return pre[end];
    }

    public int stoneGameVII(int[] stones) {
        int[] pre = new int[stones.length];
        pre[0] = stones[0];
        for (int i = 1; i < stones.length; i++) {
            pre[i] = pre[i - 1] + stones[i];
        }
        Integer[][][] dp = new Integer[stones.length][stones.length][2];
        return go(pre, 0, stones.length - 1, 0, dp);
    }

    public int go(int[] pre, int start, int end, int player, Integer[][][] dp) {
        if (start >= end) {
            return 0;
        }
        int alice;
        if (dp[start][end][player] != null) {
            return dp[start][end][player];
        }
        if (player == 0) {
            int pickLeft = getSum(pre, start + 1, end) + go(pre, start + 1, end, 1, dp);
            int pickRight = getSum(pre, start, end - 1) + go(pre, start, end - 1, 1, dp);
            alice = Math.max(pickLeft, pickRight);
        } else {
            alice = Math.min(go(pre, start + 1, end, 0, dp) - getSum(pre, start + 1, end),
                    go(pre, start, end - 1, 0, dp) - getSum(pre, start, end - 1));
        }
        dp[start][end][player] = alice;
        return alice;
    }

    public static void main(String arg[]) {
        int[] stones = new int[]{7, 90, 5, 1, 100, 10, 10, 2};
        LC1690 l = new LC1690();
        System.out.println(l.stoneGameVII(stones));
    }
}
