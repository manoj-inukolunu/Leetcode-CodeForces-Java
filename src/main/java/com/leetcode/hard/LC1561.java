package com.leetcode.hard;

public class LC1561 {
    public int getLengthOfOptimalCompression(String s, int k) {
        return solve('{', 0, 0, k, s);
    }

    private int solve(char lastChar, int count, int idx, int k, String str) {
        if (idx >= str.length()) {
            return k == 0 ? 0 : Integer.MAX_VALUE / 2;
        }
        int best = Integer.MAX_VALUE / 2;
        char curr = str.charAt(idx);
        if (lastChar == curr) {
            int val = 0;
            if (count == 1 || count == 9 || count == 99) {
                val = 1;
            }
            best = Math.min(best, val + solve(curr, count + 1, idx + 1, k, str));
            if (k > 0) {
                //delete char
                best = Math.min(best, solve(lastChar, count, idx + 1, k - 1, str));
            }

        } else {
            best = Math.min(best, 1 + solve(curr, 1, idx + 1, k, str));
            if (k > 0) {
                //delete char
                best = Math.min(best, solve(lastChar, count, idx + 1, k - 1, str));
            }
        }
        return best;
    }

    public static void main(String[] args) {
        LC1561 l = new LC1561();
        System.out.println(l.getLengthOfOptimalCompression("aaaaaaaaaaa", 0));
    }
}






