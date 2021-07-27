package com.leetcode.hard;

import java.util.HashMap;

public class LC87 {

    HashMap<String, Boolean> map = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        return solve(s1, s2);
    }

    private boolean solve(String s1, String s2) {
        if (s1.length() == 1) {
            return s1.equals(s2);
        }
        if (s1.equals(s2)) {
            return true;
        }
        String key = s1 + ":" + s2;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        for (int i = 1; i < s1.length(); i++) {
            if (solve(s1.substring(0, i), s2.substring(0, i)) && solve(s1.substring(i), s2.substring(i))
                    || (solve(s1.substring(0, i), s2.substring(s1.length() - i)) && solve(s1.substring(i), s2.substring(0, s1.length() - i)))) {
                map.put(key, true);
                return true;
            }
        }
        map.put(key, false);
        return false;
    }
}
