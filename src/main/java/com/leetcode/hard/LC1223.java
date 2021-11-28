package com.leetcode.hard;

public class LC1223 {
    int mod = (int) Math.pow(10, 9) + 7;

    public int dieSimulator(int n, int[] rollMax) {
        long count = 0;
        Integer[][][] dp = new Integer[n + 2][7][17];
        for (int i = 1; i <= 6; i++) {
            count += solve(2, i, 1, rollMax, n, dp);
            count %= mod;
        }
        return (int) count % mod;
    }

    private int solve(int currRoll, int prevNum, int prevNumTimes, int[] rollMax, int n, Integer[][][] dp) {
        if (currRoll > n) {
            return 1;
        }
        if (dp[currRoll][prevNum][prevNumTimes] != null) {
            return dp[currRoll][prevNum][prevNumTimes];
        }
        long count = 0;
        for (int i = 1; i <= 6; i++) {
            if (prevNum == i) {
                if (prevNumTimes + 1 <= rollMax[i - 1]) {
                    count += solve(currRoll + 1, i, prevNumTimes + 1, rollMax, n, dp);
                    count %= mod;
                }
            } else {
                count += solve(currRoll + 1, i, 1, rollMax, n, dp);
                count %= mod;
            }
        }
        return dp[currRoll][prevNum][prevNumTimes] = (int) count % mod;
    }


    public static void main(String[] args) {
        LC1223 l = new LC1223();
        System.out.println(l.dieSimulator(3, new int[]{1, 1, 1, 2, 2, 3}));
    }
}
