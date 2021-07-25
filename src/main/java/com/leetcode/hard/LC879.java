package com.leetcode.hard;

import java.util.Arrays;

public class LC879 {

    int mod = (int) Math.pow(10, 9) + 7;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][][] dp = new int[n][group.length + 2][minProfit + 10];
        for (int[][] r : dp)
            for (int[] d : r) {
                Arrays.fill(d, -1);
            }
        return (int) (solve(n, minProfit, group, profit, 0, 0, 0, dp) % mod);
    }

    private long solve(int n, int minProfit, int[] group, int[] profit, int idx, int members, int totalProfit, int[][][] dp) {
        if (idx >= group.length) {
            return totalProfit >= minProfit ? 1 : 0;
        }
        if (totalProfit >= minProfit) {
            totalProfit = minProfit;
        }
        if (dp[members][idx][totalProfit] != -1) {
            return dp[members][idx][totalProfit];
        }
        long count = 0;
        if (members + group[idx] <= n) {
            count = (solve(n, minProfit, group, profit, idx + 1, members + group[idx], totalProfit + profit[idx], dp) % mod) % mod;
        }
        count = (count % mod + solve(n, minProfit, group, profit, idx + 1, members, totalProfit, dp) % mod) % mod;
        dp[members][idx][totalProfit] = (int) (count % mod);
        return dp[members][idx][totalProfit] % mod;
    }

    public static void main(String args[]) {
        LC879 l = new LC879();
        int[] group = new int[]{80, 40};
        int[] profit = new int[]{88, 88};
        System.out.println(l.profitableSchemes(10, 5, group, profit));
    }
}
