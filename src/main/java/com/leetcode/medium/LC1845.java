package com.leetcode.medium;

import java.util.TreeMap;

public class LC1845 {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public LC1845(int n) {
        map.put(1, n);
    }

    public int reserve() {
        Integer first = map.firstKey();
        Integer rangeEnd = map.get(first);
        map.remove(first);
        if (rangeEnd != -1) {
            map.put(first + 1, rangeEnd);
        }
        return first;
    }

    public void unreserve(int seatNumber) {
        map.put(seatNumber, -1);
    }
}
