package com.leetcode.hard;

public class LC1872 {
    public int stoneGameVIII(int[] stones) {
        int[] pre = new int[stones.length];
        pre[0] = stones[0];
        for (int i = 1; i < stones.length; i++) {
            pre[i] = pre[i - 1] + stones[i];
        }
        Integer[][] dp = new Integer[stones.length][2];
        return go(stones, 0, 0, pre, dp);
    }

    private int go(int[] stones, int idx, int player, int[] pre, Integer[][] dp) {
        if (idx >= pre.length - 1) {
            return player == 0 ? pre[idx] : -pre[idx];
        }
        if (dp[idx][player] != null) {
            return dp[idx][player];
        }
        if (player == 0) {
            return dp[idx][player] = Math.max(go(stones, idx + 1, 0, pre, dp), pre[idx] + go(stones, idx + 1, 1, pre,
                    dp));
        } else {
            return dp[idx][player] = Math.min(go(stones, idx + 1, 1, pre, dp), -pre[idx] + go(stones, idx + 1, 0, pre,
                    dp));
        }
    }


    public static void main(String args[]) {
        int[] stones = new int[]{-10, -12};
        LC1872 l = new LC1872();
        System.out.println(l.stoneGameVIII(stones));
    }
}
