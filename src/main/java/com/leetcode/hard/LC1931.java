package com.leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LC1931 {

    int mod = (int) Math.pow(10, 9) + 7;

    Set<String> first = new HashSet<>();
    HashMap<String, Set<String>> next = new HashMap<>();
    HashMap<String, Integer> dp = new HashMap<>();

    public int colorTheGrid(int m, int n) {
        genFirst(new StringBuffer(), m);
        long count = 0;
        for (String str : first) {
            genNext(str, new StringBuffer());
        }
        for (String str : first) {
            count += solve(str, 1, n);
            count %= mod;
        }
        return (int) (count % mod);
    }

    private int solve(String prev, int currIdx, int n) {
        if (currIdx >= n) {
            return 1;
        }
        String key = prev + ":" + currIdx;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        long count = 0;
        for (String curr : next.get(prev)) {
            count += solve(curr, currIdx + 1, n);
            count %= mod;
        }
        dp.put(key, (int) (count % mod));
        return (int) (count % mod);
    }

    private void genNext(String curr, StringBuffer nextStr) {
        if (nextStr.length() == curr.length()) {
            Set<String> nextSet = next.getOrDefault(curr, new HashSet<>());
            nextSet.add(nextStr.toString());
            next.put(curr, nextSet);
            return;
        }
        for (char ch : "RGB".toCharArray()) {
            if (nextStr.length() != 0) {
                if (ch != nextStr.charAt(nextStr.length() - 1) && ch != curr.charAt(nextStr.length())) {
                    nextStr.append(ch);
                    genNext(curr, nextStr);
                    nextStr.deleteCharAt(nextStr.length() - 1);
                }
            } else if (ch != curr.charAt(0)) {
                nextStr.append(ch);
                genNext(curr, nextStr);
                nextStr.deleteCharAt(nextStr.length() - 1);
            }
        }
    }

    private void genFirst(StringBuffer curr, int len) {
        if (curr.length() == len) {
            first.add(curr.toString());
            return;
        }
        for (char ch : "RGB".toCharArray()) {
            if (curr.length() != 0 && ch != curr.charAt(curr.length() - 1)) {
                curr.append(ch);
                genFirst(curr, len);
                curr.deleteCharAt(curr.length() - 1);
            } else if (curr.length() == 0) {
                curr.append(ch);
                genFirst(curr, len);
                curr.deleteCharAt(curr.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        LC1931 l = new LC1931();
        System.out.println(l.colorTheGrid(5, 1000));
    }


}
