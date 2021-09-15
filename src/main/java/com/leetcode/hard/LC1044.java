package com.leetcode.hard;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LC1044 {

    long[] pow;
    long mod = (long) (Math.pow(10, 9) + 7);
    int prime = 123;

    private long mulMod(long a, long b, long mod) {
        return ((a % mod) * (b % mod)) % mod;
    }


    public String longestDupSubstring(String s) {
        int low = 0, high = s.length() - 1;
        int[] ans = new int[]{-1, -1};
        pow = new long[s.length()];
        pow[0] = 1;
        for (int i = 1; i < pow.length; i++) {
            pow[i] = mulMod(pow[i - 1], prime, mod);
        }
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int[] found = match(s, mid);
            if (found[0] != -1) {
                ans = found;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (ans[0] == -1) {
            ans = match(s, s.length() - 1);
            if (ans[0] == -1) {
                return "";
            }
        }
        return s.substring(ans[0], ans[1]);
    }

    private int[] match(String str, int len) {
        Set<Long> hashes = new HashSet<>();
        long hash = 0L;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(str.charAt(i));
            hash = (hash + mulMod(pow[len - i], (str.charAt(i) - 'a' + 1), mod)) % mod;
        }
        hashes.add(hash);
        for (int i = len; i < str.length(); i++) {
            builder.deleteCharAt(0);
            builder.append(str.charAt(i));
            long newHash = (hash - mulMod(pow[len], str.charAt(i - len) - 'a' + 1, mod) + mod) % mod;
            newHash = mulMod(newHash, prime, mod);
            newHash = (newHash + mulMod(pow[1], str.charAt(i) - 'a' + 1, mod)) % mod;
            System.out.println(builder + " " + newHash);
            if (hashes.contains(newHash)) {
                return new int[]{i - len + 1, i + 1};
            }
            hashes.add(newHash);
            hash = newHash;
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        String str = "duplicated";
        String str1 = "abcd";
        String str2 = "aa";
        String str3 = "banana";
        Scanner scanner = new Scanner(System.in);
        String str4 = scanner.nextLine();
        LC1044 l = new LC1044();
        System.out.println(l.longestDupSubstring(str4));
    }
}
