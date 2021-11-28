package com.leetcode.DP;

import java.util.Arrays;

public class LC647 {
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length() + 1][s.length() + 1];
        for (boolean[] row : dp) Arrays.fill(row, false);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == j) {
                    dp[i][j] = true;
                }
            }
        }
        int count = s.length();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j) && (j == i - 1 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LC647 l = new LC647();
        System.out.println(l.countSubstrings("abc"));
    }
}
