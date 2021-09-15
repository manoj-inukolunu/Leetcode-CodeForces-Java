package com.leetcode.hard;

import java.util.HashSet;

public class LC753 {

    String ans = "";

    public String crackSafe(int n, int k) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buffer.append(i);
        }
        HashSet<String> seen = new HashSet<>();
        dfs(n, k, buffer, seen);
        return ans;
    }

    private boolean dfs(int n, int k, StringBuffer buffer, HashSet<String> seen) {
        if (seen.size() == Math.pow(k, n)) {
            ans = buffer.toString();
            return true;
        }
        String last = buffer.substring(buffer.length() - n + 1);
        for (int i = 0; i < k; i++) {
            String next = last + i;
            if (!seen.contains(next)) {
                seen.add(next);
                buffer.append(i);
                if (dfs(n, k, buffer, seen)) {
                    return true;
                }
                seen.remove(next);
                buffer.deleteCharAt(buffer.length() - 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LC753 l = new LC753();
        System.out.println(l.crackSafe(2, 4));
    }
}
