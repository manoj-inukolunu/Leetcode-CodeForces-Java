package com.leetcode.hard;

import java.util.Arrays;
import java.util.TreeMap;

public class LC1187 {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        arr2 = Arrays.stream(arr2).distinct().toArray();

        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        int[][][] dp = new int[arr1.length + 1][arr2.length + 1][2];
        for (int[][] d : dp) {
            for (int[] row : d) {
                Arrays.fill(row, -1);
            }
        }
        int count;
        count = solve(arr1, arr2, 1, 0, 0, dp);
        for (int i = 0; i < arr2.length; i++) {
            count = Math.min(count, 1 + solve(arr1, arr2, 1, 0, 1, dp));
        }
        return count >= 10000 ? -1 : count;
    }

    private int solve(int[] arr1, int[] arr2, int idx, int prevIdx, int swapped, int[][][] dp) {
        if (idx >= arr1.length) {
            return 0;
        }
        if (dp[idx][prevIdx][swapped] != -1) {
            return dp[idx][prevIdx][swapped];
        }
        int min = Integer.MAX_VALUE / 2;
        int prev = swapped == 1 ? arr2[prevIdx] : arr1[idx - 1];
        int curr = arr1[idx];
        if (curr > prev) {
            min = solve(arr1, arr2, idx + 1, idx, 0, dp);
            Integer higher = map.higherKey(prev);
            if (higher != null) {
                min = Math.min(min, 1 + solve(arr1, arr2, idx + 1, map.get(higher), 1, dp));
            }
        } else {
            Integer higher = map.higherKey(prev);
            if (higher != null) {
                min = Math.min(min, 1 + solve(arr1, arr2, idx + 1, map.get(higher), 1, dp));
            }
        }
        return dp[idx][prevIdx][swapped] = min;
    }
}
