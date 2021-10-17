package com.leetcode.hard;

public class LC1977 {
    int mod = (int) Math.pow(10, 9) + 7;

    public int numberOfCombinations(String num) {
        int[][] dp = new int[num.length() + 1][num.length() + 1];
        int[][] lcs = new int[num.length()][num.length()];
        for (int i = num.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < num.length() && j + 1 < num.length(); j++) {
                if (num.charAt(i) == num.charAt(j)) {
                    lcs[i][j] = 1 + lcs[i + 1][j + 1];
                }
            }
        }
        for (int i = 0; i < num.length(); i++) {
            int maxLen = i + 1;
            for (int len = 1; len <= maxLen; len++) {
                int endNumStart = i - len + 1;
                int prevNumStart = endNumStart - len;
                int curr;
                if (num.charAt(endNumStart) == '0') {
                    curr = 0;
                } else if (endNumStart == 0) {
                    curr = 1;
                } else {
                    int prevLen;
                    if (prevNumStart < 0) {
                        prevLen = endNumStart;
                    } else {
                        int longestCommonLen = lcs[prevNumStart][endNumStart];
                        if (longestCommonLen >= len || num.charAt(prevNumStart + longestCommonLen) <= num.charAt(endNumStart + longestCommonLen)) {
                            prevLen = len;
                        } else {
                            prevLen = len - 1;
                        }
                    }
                    curr = dp[endNumStart - 1][prevLen];
                }
                dp[i][len] = (dp[i][len - 1] % mod + curr % mod) % mod;
            }
        }
        return dp[num.length() - 1][num.length()];
    }

    public static void main(String[] args) {
        LC1977 l = new LC1977();
        System.out.println(l.numberOfCombinations("4641708053100330923271"));
    }
}
