package com.leetcode.hard;

import java.util.Arrays;

public class LC514 {

    public int findRotateSteps(String ring, String key) {
        int[][] dp = new int[2 * ring.length()][key.length() + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(ring, key, 0, 0, dp);
    }

    private int solve(String ring, String key, int ringIdx, int keyIdx, int[][] dp) {
        if (keyIdx >= key.length()) {
            return 0;
        }
        if (dp[ringIdx][keyIdx] != -1) {
            return dp[ringIdx][keyIdx];
        }
        int min = 10000;
        for (int i = 0; i < ring.length(); i++) {
            if (ring.charAt(i) == key.charAt(keyIdx)) {
                min = Math.min(min,
                        1 + Math.min(Math.abs(i - ringIdx), ring.length() - Math.abs(i - ringIdx)) + solve(ring, key,
                                i, keyIdx + 1, dp));
            }
        }
        return dp[ringIdx][keyIdx] = min;
    }

    public static void main(String[] args) {
        LC514 l = new LC514();
        System.out.println(l.findRotateSteps("xrrakuulnczywjs", "jrlucwzakzussrlckyjjsuwkuarnaluxnyzcnrxxwruyr"));
    }
}
