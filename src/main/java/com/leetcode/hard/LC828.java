package com.leetcode.hard;

import java.util.HashMap;

public class LC828 {

    public int uniqueLetterString(String s) {
        int total = 0;
        HashMap<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                int[] last = map.get(ch);
                total += (i - last[0]);
                total += ((i - last[0] - 1) * last[1]);
                map.put(ch, new int[]{i, i - last[0]});
            } else {
                map.put(ch, new int[]{i, i + 1});
                total += (i + 1);
            }
        }
        for (int[] last : map.values()) {
            total += ((s.length() - last[0] - 1) * last[1]);
        }
        return total;
    }

    public static void main(String[] args) {
        LC828 l = new LC828();
        System.out.println(l.uniqueLetterString("LEETCODE"));
    }
}
