package com.leetcode.hard;

import java.util.Arrays;

public class LC233 {

    public int countDigitOne(int n) {
        int count = 0;
        StringBuilder buffer = new StringBuilder();
        for (int numLen = 1; numLen < String.valueOf(n).length(); numLen++) {
            buffer.append("0");
            for (int numOnes = 1; numOnes <= buffer.length(); numOnes++) {
                int[][][] dp = new int[buffer.length() + 1][2][numOnes + 1];
                for (int[][] row : dp) {
                    for (int[] r : row) {
                        Arrays.fill(r, -1);
                    }
                }
                count += numOnes * (solve(buffer.toString(), 0, 1, numOnes, dp));
            }
        }
        for (int numOnes = 1; numOnes <= String.valueOf(n).length(); numOnes++) {
            int[][][] dp = new int[buffer.length() + 1][2][numOnes + 1];
            for (int[][] row : dp) {
                for (int[] r : row) {
                    Arrays.fill(r, -1);
                }
            }
            count += numOnes * (solve(String.valueOf(n), 0, 0, numOnes, dp));
        }
        return count;
    }

    private int solve(String str, int idx, int isLess, int numOnes, int[][][] dp) {
        if (idx >= str.length()) {
            return numOnes == 0 ? 1 : 0;
        }
        int count = 0;
        if (dp[idx][isLess][numOnes] != -1) {
            return dp[idx][isLess][numOnes];
        }
        if (isLess == 1) {
            for (int i = 0; i <= 9; i++) {
                if (i == 0 && idx == 0) {
                    continue;
                }
                if (numOnes <= 0 && i == 1) {
                    continue;
                }
                if (numOnes == 0) {
                    count += solve(str, idx + 1, isLess, numOnes, dp);
                } else {
                    count += solve(str, idx + 1, isLess, (i == 1) ? (numOnes - 1) : numOnes, dp);
                }

            }
        } else {
            int curr = Character.getNumericValue(str.charAt(idx));
            for (int i = 0; i < curr; i++) {
                if (i == 0 && idx == 0) {
                    continue;
                }
                if (numOnes <= 0 && i == 1) {
                    continue;
                }
                if (numOnes == 0) {
                    count += solve(str, idx + 1, 1, numOnes, dp);
                } else {
                    count += solve(str, idx + 1, 1, (i == 1) ? (numOnes - 1) : numOnes, dp);
                }

            }
            if (numOnes == 0) {
                if (curr != 1) {
                    count += solve(str, idx + 1, 0, numOnes, dp);
                }
            } else {
                count += solve(str, idx + 1, 0, curr == 1 ? (numOnes - 1) : numOnes, dp);
            }

        }
        return dp[idx][isLess][numOnes] = count;
    }


    public static void main(String[] args) {
        LC233 l = new LC233();
        System.out.println((int) Math.pow(10, 9));
        System.out.println(l.countDigitOne(110));

    }


}
