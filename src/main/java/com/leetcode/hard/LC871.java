package com.leetcode.hard;


import java.util.Arrays;

public class LC871 {

    private int minRefuelStops(int target, int start, int[][] stations) {
        int[][] dp = new int[stations.length + 1][stations.length + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        for (int stops = 0; stops < stations.length; stops++) {
            int maxDist = solve(stations, 0, 0, start);
            if (maxDist >= target) {
                return stops;
            }
        }
        return -1;
    }

    private int solve(int[][] stations, int curr, int refills, int startFuel) {
        if (curr < 0) {
            return startFuel;
        }
        //fuel at curr
        int best = solve(stations, curr - 1, refills, startFuel);
        if (refills > 0) {
            int refill = solve(stations, curr - 1, refills - 1, startFuel);
            best = Math.max(best, refill < stations[curr][0] ? Integer.MIN_VALUE / 2 : stations[curr][1] + refill);
        }
        return best;
    }

    public static void main(String args[]) {

    }
}



