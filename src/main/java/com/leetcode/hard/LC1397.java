package com.leetcode.hard;

import java.util.Arrays;

public class LC1397 {
    int mod = (int) Math.pow(10, 9) + 7;

    private int[][][][] newDp(int x, int y, int z, int t) {
        int[][][][] dp = new int[x][y][z][t];
        for (int[][][] a : dp) {
            for (int[][] b : a) {
                for (int[] c : b)
                    Arrays.fill(c, -1);
            }
        }
        return dp;
    }

    String s1, s2, evil;
    int[][] eDp;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        long count = 0;
        this.s1 = s1;
        this.s2 = s2;
        this.evil = evil;
        eDp = solveE(evil);
        int[][][][] dp = newDp(s1.length(), s2.length(), 2, 2);
        for (char ch = s1.charAt(0); ch <= s2.charAt(0); ch++) {
            count += solve(1, ch == evil.charAt(0) ? 1 : 0, s1.charAt(0) == ch ? 1 : 0, s2.charAt(0) == ch ? 1 : 0, dp);
            count %= mod;
        }
        return (int) count % mod;
    }

    public int[][] solveE(String str) {
        int[][] dp = new int[str.length() + 1][26];
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            b.append(str.charAt(i));
            for (char ch = 'a'; ch <= 'z'; ch++) {
                b.append(ch);
                for (int j = b.length() - 1; j >= 0; j--) {
                    String s = b.substring(j, b.length());
                    if (str.startsWith(s)) {
                        dp[i + 1][ch - 'a'] = s.length();
                    }
                }
                b.deleteCharAt(b.length() - 1);
            }
        }
        return dp;
    }

    private int solve(int idx, int eIdx, int isS1Prefix, int isS2Prefix, int[][][][] dp) {
        if (eIdx >= evil.length()) {
            return 0;
        }
        if (idx >= s1.length()) {
            return 1;
        }

        if (dp[idx][eIdx][isS1Prefix][isS2Prefix] != -1) {
            return dp[idx][eIdx][isS1Prefix][isS2Prefix];
        }
        long count = 0;
        char start = 'a', end = 'z';
        for (char ch = start; ch <= end; ch++) {
            if ((isS2Prefix == 1 && ch > s2.charAt(idx)) || (isS1Prefix == 1 && ch < s1.charAt(idx))) {
                continue;
            }
            if (ch == evil.charAt(eIdx)) {
                count += solve(idx + 1, eIdx + 1, getPrefix(isS1Prefix, ch, s1.charAt(idx)),
                        getPrefix(isS2Prefix, ch, s2.charAt(idx)), dp);
            } else {
                count += solve(idx + 1, eDp[eIdx][ch - 'a'], getPrefix(isS1Prefix, ch, s1.charAt(idx)),
                        getPrefix(isS2Prefix, ch, s2.charAt(idx)), dp);
                count %= mod;
            }
        }
        return dp[idx][eIdx][isS1Prefix][isS2Prefix] = (int) (count % mod);
    }

    private int getPrefix(int currPrefix, char ch, char toAdd) {
        if (currPrefix == 1) {
            return ch == toAdd ? 1 : 0;
        }
        return 0;
    }

    public static void main(String[] args) {
        LC1397 l = new LC1397();
        System.out.println(l.findGoodStrings(93,
                "kazxkmmctdgtrplfggliycskmbfzjkrsthahcrxaaylpdykqfyejwteexytvxgjrbviconioomfpznawsseisfcpfkuvx",
                "lajtokckoizywvirjhccouuhjkkshdtzchzmiccujzpeqzcvfekdqjgrbkzrwfnfwhyvzrrrakiausbleeimmthaqqouh",
                "kpvphwnkrtxuiuhb"));
    }
}
