package com.leetcode.hard;

import java.util.Arrays;

public class LC1402 {

    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        return solve(satisfaction, 0, 1);
    }

    private int solve(int[] satisfaction, int idx, int time) {
        if (idx >= satisfaction.length) {
            return 0;
        }
        int incl = satisfaction[idx] * time + solve(satisfaction, idx + 1, time + 1);
        int excl = solve(satisfaction, idx + 1, time);
        return Math.max(incl, excl);
    }

    public static void main(String[] args) {
        LC1402 l = new LC1402();
        int[] arr = new int[]{-2, 5, -1, 0, 3, -3};
        System.out.println(l.maxSatisfaction(arr));
    }
}
