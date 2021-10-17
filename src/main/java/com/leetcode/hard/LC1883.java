package com.leetcode.hard;


import java.util.Arrays;

public class LC1883 {

    double totalDist = 0;

    public int minSkips(int[] dist, int speed, int hoursBefore) {
        totalDist = Arrays.stream(dist).sum();
        long val = solve(dist, 0, 0, 0, speed, hoursBefore);
        return val >= Integer.MAX_VALUE ? -1 : (int) val;
    }

    private long solve(int[] dist, int idx, double timeWaited, double extra, int speed, double hoursBefore) {
        if (idx >= dist.length) {
            double totalTime = (totalDist / speed) + timeWaited;
            return totalTime > hoursBefore ? Integer.MAX_VALUE : 0;
        }
        long best;
        double time = (double) dist[idx] / speed;
        double decimal = (time - (int) time);
        if (extra > 0) {
            //wait
            best = solve(dist, idx + 1, timeWaited + (1 - extra), decimal, speed, hoursBefore);
            boolean shouldCarryOver = (time + extra) % 1 != 0;
            double carryOver = (time + extra) - (int) (time + extra);
            best = Math.min(best, 1 + solve(dist, idx + 1, timeWaited, shouldCarryOver ? (carryOver) : 0, speed,
                    hoursBefore));
        } else {
            best = solve(dist, idx + 1, timeWaited, decimal, speed, hoursBefore);
        }
        return best;
    }

    public static void main(String[] args) {
        int[] dist = new int[]{1, 3, 2};
        int speed = 4;
        int hoursBefore = 2;
        LC1883 l = new LC1883();
        System.out.println(l.minSkips(dist, speed, hoursBefore));
    }
}
