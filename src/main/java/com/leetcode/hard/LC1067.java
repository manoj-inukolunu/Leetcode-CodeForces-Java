package com.leetcode.hard;

import java.util.Arrays;

public class LC1067 {
    int digit;

    public int digitsCount(int d, int low, int high) {
        digit = d;
        if (low == 1) {
            return solve(high);
        } else {
            return solve(high) - solve(low - 1);
        }
    }


    private int solve(int num) {
        String str = String.valueOf(num);
        int maxLen = str.length();
        int count = 0;
        // All Numbers less than length.
        for (int len = 1; len < maxLen; len++) {
            //How many Numbers with exactly k digits
            for (int k = 1; k <= len; k++) {
                int[][][] dp = new int[len + 1][k + 1][2];
                fill(dp);
                count += k * go(str, len, 0, 0, k, dp);
            }
        }
        //All Numbers equal to length
        for (int k = 1; k <= maxLen; k++) {
            int[][][] dp = new int[maxLen + 1][k + 1][2];
            fill(dp);
            count += k * go(str, maxLen, 0, 1, k, dp);
        }
        return count;
    }

    void fill(int[][][] dp) {
        for (int[][] one : dp) {
            for (int[] row : one) {
                Arrays.fill(row, -1);
            }
        }
    }

    private int go(String str, int len, int idx, int isLess, int k, int[][][] dp) {
        if (idx >= len) {
            return k == 0 ? 1 : 0;
        }
        if (dp[idx][k][isLess] != -1) {
            return dp[idx][k][isLess];
        }
        int total = 0;
        int curr = Character.getNumericValue(str.charAt(idx));
        if (len < str.length()) {
            for (int i = 0; i <= 9; i++) {
                if (i == 0 && idx == 0) {
                    continue;
                }
                if (i == digit && k <= 0) {
                    continue;
                }
                total += go(str, len, idx + 1, 0, (i == digit) ? k - 1 : k, dp);
            }
        } else {
            if (isLess == 0) {
                for (int i = 0; i <= 9; i++) {
                    if (i == digit && k <= 0) {
                        continue;
                    }
                    total += go(str, len, idx + 1, 0, (i == digit) ? k - 1 : k, dp);
                }
            } else {
                for (int i = 0; i < curr; i++) {
                    if (idx == 0 && i == 0) {
                        continue;
                    }
                    if (i == digit && k <= 0) {
                        continue;
                    }
                    total += go(str, len, idx + 1, 0, (i == digit) ? k - 1 : k, dp);
                }
                if (curr == digit) {
                    if (k > 0) {
                        total += go(str, len, idx + 1, 1, k - 1, dp);
                    }
                } else {
                    total += go(str, len, idx + 1, 1, k, dp);
                }
            }
        }
        return dp[idx][k][isLess] = total;
    }

    public static void main(String[] args) {
        LC1067 l = new LC1067();
        System.out.println(l.digitsCount(1, 1, 13));
    }
}
