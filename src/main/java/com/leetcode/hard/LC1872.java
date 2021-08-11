package com.leetcode.hard;

public class LC1872 {

    int getSum(int[] pre, int start, int end) {
        if (start > 0) {
            return pre[end] - pre[start - 1];
        }
        return pre[end];
    }


    public int stoneGameVIII(int[] stones) {
        int[] pre = new int[stones.length];
        pre[0] = stones[0];
        for (int i = 1; i < stones.length; i++) {
            pre[i] = pre[i - 1] + stones[i];
        }
        Integer[][][] dp = new Integer[stones.length][2][2];
        return go(stones, 0, 0, 0, pre, dp);
    }

    private int go(int[] stones, int idx, int isAlice, int isBegin, int[] pre, Integer[][][] dp) {
        if (idx >= stones.length) {
            return 0;
        }
        if (dp[idx][isAlice][isBegin] != null) {
            return dp[idx][isAlice][isBegin];
        }
        int score;
        if (isAlice == 0) {
            score = Integer.MIN_VALUE;
            for (int i = (isBegin == 0 ? (idx + 1) : idx); i < stones.length; i++) {
                score = Math.max(score, go(stones, i + 1, 1, 1, pre, dp) + pre[i]);
            }
        } else {
            score = Integer.MAX_VALUE;
            for (int i = (isBegin == 0 ? (idx + 1) : idx); i < stones.length; i++) {
                score = Math.min(score, go(stones, i + 1, 0, 1, pre, dp) - pre[i]);
            }
        }
        dp[idx][isAlice][isBegin] = score;
        return dp[idx][isAlice][isBegin];
    }

    public static void main(String args[]) {
        int[] stones = new int[]{-10, -12};
        LC1872 l = new LC1872();
        System.out.println(l.stoneGameVIII(stones));
    }
}
