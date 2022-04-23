package com.leetcode.hard;

public class LC2060 {
    boolean visited[][][] = new boolean[41][41][2001];

    public boolean possiblyEquals(String s1, String s2) {
        return dfs(s1.toCharArray(), s2.toCharArray(), 0, 0, 0);
    }

    private boolean processDigits(char[] str, int idx, int sign, char[] s1, char[] s2, int i, int j, int diff) {
        for (int val = 0; idx < str.length && isDigit(str[idx]); ) {
            val = val * 10 + (str[idx++] - '0');
            if (dfs(s1, s2, i, j, diff + val * sign)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(char[] s1, char[] s2, int i, int j, int diff) {
        if (i >= s1.length && j >= s2.length) {
            return diff == 0;
        }
        if (!visited[i][j][1000 + diff]) {
            visited[i][j][1000 + diff] = true;
            if (i < s1.length && isDigit(s1[i])) {
                return processDigits(s1, i, -1, s1, s2, i, j, diff);
            }
            if (j < s2.length && isDigit(s2[j])) {
                return processDigits(s2, j, 1, s1, s2, i, j, diff);
            }
            if (diff > 0) {
                return i < s1.length && dfs(s1, s2, i + 1, j, diff - 1);
            }
            if (diff < 0) {
                return j < s2.length && dfs(s1, s2, i, j + 1, diff + 1);
            }
            return i < s1.length && j < s2.length && s1[i] == s2[j] && dfs(s1, s2, i + 1, j + 1, diff);
        }
        return false;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        LC2060 l = new LC2060();
        System.out.println(l.possiblyEquals("l123e", "44"));
    }
}






