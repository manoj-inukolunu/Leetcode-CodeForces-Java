package com.leetcode.hard;

import java.util.HashMap;

public class LC1259 {
    HashMap<Integer, Integer> map = new HashMap<>();
    int mod = (int) Math.pow(10, 9) + 7;

    public int numberOfWays(int numPeople) {
        if (numPeople % 2 != 0) {
            return 0;
        }
        if (numPeople == 2 || numPeople == 0) {
            return 1;
        }
        if (numPeople <= 0) {
            return 0;
        }
        if (map.containsKey(numPeople)) {
            return map.get(numPeople);
        }
        long ways = 0;
        for (int second = 1; second < numPeople; second++) {
            if (second == numPeople - 1) {
                ways += numberOfWays(numPeople - 2);
                ways %= mod;
            } else {
                ways += ((long) numberOfWays(second - 1) * numberOfWays(numPeople - second - 1));
                ways %= mod;
            }
        }
        map.put(numPeople, (int) ways);
        return (int) ways;
    }

    public static void main(String[] args) {
        LC1259 l = new LC1259();
        System.out.println(l.numberOfWays(150));
    }
}






