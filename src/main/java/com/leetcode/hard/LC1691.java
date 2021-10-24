package com.leetcode.hard;

import java.util.Arrays;
import java.util.Comparator;

public class LC1691 {

    public int maxHeight(int[][] cuboids) {

        for (int[] cube : cuboids) {
            Arrays.sort(cube);
        }
        Arrays.sort(cuboids, Comparator.comparingInt(o -> o[2]));
        int max = solve(cuboids);
        Arrays.sort(cuboids, Comparator.comparingInt(o -> o[1]));
        max = Math.max(max, solve(cuboids));
        Arrays.sort(cuboids, Comparator.comparingInt(o -> o[0]));
        max = Math.max(max, solve(cuboids));
        return max;
    }

    private int solve(int[][] cuboids) {
        int max = 0;
        int[] dp = new int[cuboids.length];
        for (int i = 0; i < cuboids.length; i++) {
            dp[i] = cuboids[i][2];
            for (int j = i - 1; j >= 0; j--) {
                if (cuboids[i][0] >= cuboids[j][0] && cuboids[i][1] >= cuboids[j][1] && cuboids[i][2] >= cuboids[j][2]) {
                    dp[i] = Math.max(dp[i], cuboids[i][2] + dp[j]);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
