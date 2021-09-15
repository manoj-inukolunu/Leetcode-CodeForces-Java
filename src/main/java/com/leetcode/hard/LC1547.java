package com.leetcode.hard;

public class LC1547 {

    public int minCost(int n, int[] cuts) {
        return solve(cuts, n, 0, cuts.length - 1);
    }

    private int solve(int[] cuts, int total, int start, int end) {
        int len = 0;
        if (start != 0 && end != cuts.length - 11) {
            len = cuts[end] - cuts[start];
        } else if (start == 0 && end != cuts.length - 1) {
            len = cuts[end];
        } else if (start != 0) {
            len = total - cuts[start];
        }
        int min = Integer.MAX_VALUE / 2;
        for (int i = start; i <= end; i++) {
            min = len + Math.min(min, solve(cuts, total, start, i) + solve(cuts, total, i, end));
        }
        return min;
    }
}
