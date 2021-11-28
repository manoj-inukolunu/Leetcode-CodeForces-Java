package com.leetcode.hard;

import java.util.Arrays;

public class LC1147 {
    int mod = (int) Math.pow(10, 9) + 7;
    String str;

    public long power(long num, int pow, int mod) {
        long res = 1;
        num = num % mod;
        while (pow != 0) {
            if ((pow & 1) != 0) {
                res = (res * num) % mod;
            }
            pow >>= 1;
            num = (num * num) % mod;
        }
        return res;
    }

    public long getHash(long[] hash, long[] inv, int start, int end, int mod) {
        if (start > 0) {
            return ((mod + (hash[end] - hash[start - 1])) * inv[start]) % mod;
        }
        return ((mod + (hash[end])) * inv[start]) % mod;
    }

    public int longestDecomposition(String text) {
        str = text;
        int len = text.length();
        long[] pow = new long[len];
        long[] inv = new long[len];
        inv[0] = 1;
        pow[0] = 1;
        int mod = (int) Math.pow(10, 9) + 7;
        int p = 31;
        for (int i = 1; i < text.length(); i++) {
            pow[i] = pow[i - 1] * p;
            pow[i] %= mod;
            inv[i] = power(pow[i], mod - 2, mod);/*BigInteger.valueOf(pow[i]).modInverse(BigInteger.valueOf(mod))
                    .longValue();*/
        }
        long hash = 0;
        long[] hashArr = new long[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            hash = hash + (ch - 'a' + 1) * pow[i];
            hash %= mod;
            hashArr[i] = hash;
        }
        int[][] dp = new int[text.length()][text.length()];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(hashArr, inv, 0, text.length() - 1, dp);
    }

    private int solve(long[] hashArr, long[] inv, int start, int end, int[][] dp) {
        if (start == end) {
            return 1;
        }
        if (dp[start][end] != -1) {
            return dp[start][end];
        }
        int best = Integer.MIN_VALUE / 2;
        int maxLen = (end - start + 1) / 2;
        for (int len = 1; len <= maxLen; len++) {
            if (getHash(hashArr, inv, start, start + len - 1, mod) == getHash(hashArr, inv, end - len + 1, end,
                    mod) && start + len <= end - len) {
                best = Math.max(best, 2 + solve(hashArr, inv, start + len, end - len, dp));
            }
        }
        //check mid
        if ((end - start + 1) % 2 == 0 && getHash(hashArr, inv, start, start + maxLen - 1, mod) == getHash(hashArr, inv,
                end - maxLen + 1, end, mod)) {
            best = Math.max(best, 2);
        }
        return dp[start][end] = (best == Integer.MIN_VALUE / 2 ? 1 : best);
    }

    public static void main(String[] args) {
        LC1147 l = new LC1147();
        System.out.println(l.longestDecomposition("elvetoelveto"));
    }
}






