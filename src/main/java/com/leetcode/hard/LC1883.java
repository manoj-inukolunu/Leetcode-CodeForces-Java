package com.leetcode.hard;


import java.util.Arrays;

public class LC1883 {

    public int minSkips(int[] dist, int speed, int hoursBefore) {
        double[][] dp = new double[dist.length + 2][dist.length + 2];
        for (double[] row : dp) {
            Arrays.fill(row, -1d);
        }
        for (int skips = 0; skips < dist.length; skips++) {
            double val = solve(dist, 0, skips, speed, dp);
            if (val <= hoursBefore) {
                return skips;
            }
        }
        return -1;
    }

    private double solve(int[] dist, int idx, int skips, double speed, double[][] dp) {
        if (idx >= dist.length) {
            return 0;
        }
        if (dp[idx][skips] != -1) {
            return dp[idx][skips];
        }
        if (skips > 0) {
            double best = (dist[idx] / speed) + solve(dist, idx + 1, skips - 1, speed, dp);
            best = Math.min(best, Math.ceil(dist[idx] / speed + solve(dist, idx + 1, skips, speed, dp)));
            return dp[idx][skips] = best;
        } else {
            double best = Math.ceil(dist[idx] / speed + solve(dist, idx + 1, skips, speed, dp));
            return dp[idx][skips] = best;
        }
    }

    public static void main(String[] args) {
        int[] dist = new int[]{7, 3, 5, 5};
        int speed = 1;
        int hoursBefore = 10;
        LC1883 l = new LC1883();
        System.out.println(l.minSkips(dist, speed, hoursBefore));
    }
}
