package com.leetcode.hard;

public class LC943 {

    public String shortestSuperstring(String[] words) {
        String[][] toAdd = new String[words.length][words.length];
        String[][] dp = new String[words.length + 1][(1 << (words.length + 1))];

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;
                for (int k = 0; k < words[i].length(); k++) {
                    String str = words[i].substring(k);
                    if (words[j].startsWith(str)) {
                        toAdd[i][j] = words[j].substring(str.length());
                        break;
                    }
                }
                if (toAdd[i][j] == null) {
                    toAdd[i][j] = words[j];
                }
            }
        }
        String ans = "";
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String str = words[i] + solve(words, toAdd, i, (1 << i), dp);
            if (str.length() < minLen) {
                minLen = str.length();
                ans = str;
            }
        }
        return ans;
    }

    private String solve(String[] arr, String[][] overlap, int last, int mask, String[][] dp) {
        if (mask == (1 << arr.length)) {
            return "";
        }
        if (dp[last][mask] != null) {
            return dp[last][mask];
        }
        int minLen = Integer.MAX_VALUE;
        String ret = "";
        for (int i = 0; i < arr.length; i++) {
            if ((mask & (1 << i)) == 0) {
                String curr = overlap[last][i] + solve(arr, overlap, i, mask | (1 << i), dp);
                if (curr.length() < minLen) {
                    minLen = curr.length();
                    ret = curr;
                }
            }
        }
        return dp[last][mask] = ret;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"afabcde", "ab", "cd"};
    }
}
