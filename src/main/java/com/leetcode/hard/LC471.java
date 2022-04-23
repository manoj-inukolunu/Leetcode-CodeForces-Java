package com.leetcode.hard;


import java.util.HashMap;

public class LC471 {

    HashMap<String, String> dp = new HashMap<>();

    public String encode(String str) {
        if (str.length() <= 4) {
            return str;
        }
        if (dp.containsKey(str)) {
            return dp.get(str);
        }
        String best = str;
        int strLen = str.length();
        for (int len = 1; len <= strLen / 2; len++) {
            String subStr = str.substring(0, len);
            if (strLen % len == 0 && str.replaceAll(subStr, "").isEmpty()) {
                String currBest = strLen / len + "[" + encode(subStr) + "]";
                if (currBest.length() < best.length()) {
                    best = currBest;
                }
            }
        }
        for (int i = 0; i + 1 < str.length(); i++) {
            String left = encode(str.substring(0, i + 1));
            String right = encode(str.substring(i + 1));
            if ((left + right).length() < best.length()) {
                best = (left + right);
            }
        }
        dp.put(str, best);
        return best;
    }

    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            char curr = s.charAt(i);
            char prev = s.charAt(i - 1);
            int last = Character.getNumericValue(prev) * 10 + Character.getNumericValue(curr);
            if (last == 0) {
                return 0;
            }
            if (curr == '0' && prev > '2') {
                return 0;
            } else {
                int var = i - 2 >= 0 ? dp[i - 2] : 1;
                if (curr == '0') {
                    dp[i] = var;
                } else {
                    dp[i] = dp[i - 1];
                    if (last <= 26 && prev != '0') {
                        dp[i] += var;
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }


    public static void main(String[] args) {
        LC471 l = new LC471();
        System.out.println(l.numDecodings("2206"));
    }
}



