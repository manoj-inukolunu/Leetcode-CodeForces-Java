package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;
import java.util.HashMap;

public class LC1494 {


    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] prereq = new int[n];
        for (int[] req : relations) {
            prereq[req[1] - 1] |= 1 << (req[0] - 1);
        }
        int[] dp = new int[(1 << n)];
        Arrays.fill(dp, -1);
        return solve(0, n, k, prereq, dp);
    }

    private int solve(int mask, int n, int k, int[] prereq, int[] dp) {
        if (mask == (1 << n) - 1) {
            return 0;
        }
        if (dp[mask] != -1) {
            return dp[mask];
        }
        int availableCourses = 0;
        for (int i = 0; i < n; i += 1) {
            if ((mask & prereq[i]) == prereq[i] && ((mask & (1 << i)) != 0)) {
                availableCourses |= 1 << i;
            }
        }
//        availableCourses &= ~mask;
        int best = Integer.MAX_VALUE / 2;
        for (int submask = availableCourses; submask > 0; submask = (submask - 1) & availableCourses) {
            if (Integer.bitCount(submask) <= k) {
                best = Math.min(best, 1 + solve(mask | submask, n, k, prereq, dp));
            }
        }
        return dp[mask] = best;
    }

    HashMap<Integer, Integer> map = new HashMap<>();

    public int leastOpsExpressTarget(int x, int target) {
        if (target < x) {
            return Math.min(2 * target - 1, 2 * (x - target));
        }
        if (map.containsKey(target)) {
            return map.get(target);
        }
        long sum = x, ops = 0;
        while (sum < target) {
            sum *= x;
            ops++;
        }
        long lower = ops + leastOpsExpressTarget(x, (int) (target - Math.floorDiv(sum, x)));
        if (sum - target >= target) {
            map.put(target, (int) lower);
            return (int) lower;
        }
        map.put(target, (int) Math.min(lower, ops + 1 + leastOpsExpressTarget(x, (int) sum - target)));
        return map.get(target);
    }


    public static void main(String[] args) {
        int[][] dependencies = Utils.convertToTwoDIntArray("[[2,1],[3,1],[1,4]]");
        LC1494 l = new LC1494();
        System.out.println(l.minNumberOfSemesters(4, dependencies, 2));
    }
}






