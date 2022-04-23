package com.leetcode.hard;

import java.util.Arrays;

public class LC1987 {


    int mod = (int) Math.pow(10, 9) + 7;

    public int numberOfUniqueGoodSubsequences(String binary) {
        int len = binary.length();
        int[] last = new int[2];
        Arrays.fill(last, -1);
        long[] dp = new long[len];
        int start = binary.indexOf('1') + 1;
        dp[start - 1] = 1;
        for (int i = start; i < binary.length(); i++) {
            int ch = Character.getNumericValue(binary.charAt(i));
            dp[i] = 2 * dp[i - 1];
            if (last[ch] - 1 >= 0) {
                dp[i] -= dp[last[ch] - 1];
            }
            last[ch] = i;
        }
        return (int) dp[len - 1] + (binary.contains("0") ? 1 : 0);
    }

    public static void main(String[] args) {
        LC1987 l = new LC1987();
        System.out.println(l.numberOfUniqueGoodSubsequences("001"));
    }
}






