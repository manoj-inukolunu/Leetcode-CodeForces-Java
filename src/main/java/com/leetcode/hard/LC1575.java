package com.leetcode.hard;

import java.util.Arrays;

public class LC1575 {
    int mod = (int) (Math.pow(10, 9) + 7);

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int[][] dp = new int[locations.length + 1][fuel + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(locations, start, finish, fuel, dp);
    }

    private int solve(int[] locations, int curr, int finish, int fuel, int[][] dp) {
        if (fuel == 0) {
            return curr == finish ? 1 : 0;
        }
        if (dp[curr][fuel] != -1) {
            return dp[curr][fuel];
        }
        long ways = 0;
        if (curr == finish) {
            ways = 1;
        }
        for (int i = 0; i < locations.length; i++) {
            if (i == curr) {
                continue;
            }
            int neededFuel = Math.abs(locations[i] - locations[curr]);
            if (fuel >= neededFuel) {
                ways += solve(locations, i, finish, fuel - neededFuel, dp);
                ways %= mod;
            }
        }
        return dp[curr][fuel] = (int) ways % mod;
    }

    public static void main(String[] args) {
        int[] locations = new int[]{1, 2, 3};
        LC1575 l = new LC1575();
        System.out.println(l.countRoutes(locations, 0, 2, 40));
    }
}
