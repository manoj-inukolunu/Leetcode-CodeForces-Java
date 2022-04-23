package com.leetcode.hard;


import java.math.BigInteger;
import java.util.Arrays;

public class LC940 {


    public int distinctSubseqII(String str) {
        int MAX_CHAR = 256;
        int mod = (int) (Math.pow(10, 9) + 7);
        int[] last = new int[MAX_CHAR];
        Arrays.fill(last, -1);
        int n = str.length();
        BigInteger[] dp = new BigInteger[n + 1];
        dp[0] = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1].multiply(BigInteger.valueOf(2));
            if (last[str.charAt(i - 1)] != -1) {
                dp[i] = dp[i].subtract(dp[last[str.charAt(i - 1)]]);
            }
            last[str.charAt(i - 1)] = (i - 1);
        }
        return dp[n].subtract(BigInteger.ONE).mod(BigInteger.valueOf(mod)).intValue();
    }

    public int solve(String str) {
        int mod = (int) Math.pow(10, 9) + 7;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        long[] dp = new long[str.length() + 1];
        dp[0] = 1;
        last[str.charAt(0) - 'a'] = 0;
        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            long toSubtract = last[ch - 'a'] > 0 ? dp[last[ch - 'a'] - 1] : 0;
            dp[i] = (dp[i - 1] % mod + dp[i - 1] % mod) % mod;
            dp[i] = (dp[i] % mod + (last[ch - 'a'] == -1 ? 1 : 0) % mod) % mod;
            dp[i] = (mod + dp[i] % mod - toSubtract % mod) % mod;
            last[ch - 'a'] = i;
        }
        System.out.println(Arrays.toString(dp));
        return (int) dp[str.length() - 1];
    }

    public static void main(String[] args) {
        LC940 l = new LC940();
        System.out.println(l.solve(
                "aba"
        ));
    }
}



