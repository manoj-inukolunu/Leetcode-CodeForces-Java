package com.leetcode.hard;

import java.util.HashMap;

public class LC76 {

    String ans = "";

    public String minWindow(String s, String t) {
        HashMap<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }
        int lower = 1, higher = s.length();
        while (lower <= higher) {
            int len = lower + (higher - lower) / 2; // 6
            if (found(s, tMap, len)) {
                higher = len - 1;
            } else {
                lower = len + 1;
            }
        }
        return ans;
    }

    private boolean found(String str, HashMap<Character, Integer> tMap, int len) {
        HashMap<Character, Integer> sMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
        }
        if (valid(sMap, tMap)) {
            ans = str.substring(0, len);
            return true;
        }
        for (int i = 1; i + len <= str.length(); i++) {
            char toRemove = str.charAt(i - 1);
            char toAdd = str.charAt(i + len - 1);
            sMap.put(toAdd, sMap.getOrDefault(toAdd, 0) + 1);
            sMap.put(toRemove, sMap.get(toRemove) - 1);
            if (sMap.get(toRemove) <= 0) {
                sMap.remove(toRemove);
            }
            if (valid(sMap, tMap)) {
                ans = str.substring(i, i + len);
                return true;
            }
        }
        return false;
    }

    private boolean valid(HashMap<Character, Integer> sMap, HashMap<Character, Integer> tMap) {
        for (char ch : tMap.keySet()) {
            if (!(sMap.containsKey(ch) && sMap.get(ch) >= tMap.get(ch))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC76 l = new LC76();
        l.minWindow("ADOBECODEBANC", "ABC");
    }
}
