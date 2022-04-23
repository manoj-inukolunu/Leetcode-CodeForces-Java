package com.leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LC2019 {

    HashMap<String, Set<Integer>> map = new HashMap<>();

    private int eval(String str) {
        int val;
        if (!str.contains("+")) {
            String[] s = str.split("\\*");
            val = Integer.parseInt(s[0]);
            for (int i = 1; i < s.length; i++) {
                val *= Integer.parseInt(s[i]);
            }
        } else {
            String[] s = str.split("\\+");
            val = eval(s[0]);
            for (int i = 1; i < s.length; i++) {
                val += eval(s[i]);
            }
        }
        return val;
    }

    public int scoreOfStudents(String s, int[] answers) {
        Set<Integer> set = solve(s);
        int sum = 0;
        int proper = eval(s);
        System.out.println("set = " + set);
        for (int ans : answers) {
            if (set.contains(ans)) {
                sum += (ans == proper ? 5 : 3);
            }
        }
        return sum;
    }

    private Set<Integer> solve(String str) {
        if (str.length() == 1) {
            Set<Integer> set = new HashSet<>();
            set.add(Integer.parseInt(str));
            return set;
        }
        if (!map.containsKey(str)) {
            Set<Integer> ret = new HashSet<>();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '+') {
                    Set<Integer> left = solve(str.substring(0, i));
                    Set<Integer> right = solve(str.substring(i + 1));
                    for (int num : left) {
                        for (int val : right) {
                            if (num + val < 1000) {
                                ret.add(num + val);
                            }
                        }
                    }
                } else if (str.charAt(i) == '*') {
                    Set<Integer> left = solve(str.substring(0, i));
                    Set<Integer> right = solve(str.substring(i + 1));
                    for (int num : left) {
                        for (int val : right) {
                            if (num * val < 1000) {
                                ret.add(num * val);
                            }
                        }
                    }
                }
            }
            map.put(str, ret);
            return ret;
        } else {
            return map.get(str);
        }
    }

    public static void main(String args[]) {
        String str = "4+4*8+8*4+4*8+4*8+4*4+8*4+8*4+4";
        LC2019 l = new LC2019();
        System.out.println(l.scoreOfStudents(str, new int[]{20, 13, 42}));
    }
}






