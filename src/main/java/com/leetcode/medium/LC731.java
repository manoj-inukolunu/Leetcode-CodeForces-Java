package com.leetcode.medium;

import java.util.TreeMap;

public class LC731 {


    class MyCalendarTwo {

        TreeMap<Integer, Integer> sBooked = new TreeMap<>();
        TreeMap<Integer, Integer> dBooked = new TreeMap<>();

        public MyCalendarTwo() {

        }

        public boolean book(int start, int end) {
            boolean doubleBooked = checkBooked(start, end, dBooked);
            if (!doubleBooked) {
                boolean singleBooked = checkBooked(start, end, sBooked);
                if (!singleBooked) {
                    sBooked.put(start, end);
                    return true;
                } else {

                }
            }
            return false;
        }

        private boolean checkBooked(int start, int end, TreeMap<Integer, Integer> map) {
            if (map.containsKey(start)) {
                return true;
            }
            Integer lower = map.lowerKey(start);
            if (lower != null && overlaps(start, end, lower, map.get(lower))) {
                return true;
            }
            Integer higher = map.higherKey(start);
            return higher != null && overlaps(start, end, higher, map.get(higher));
        }

        private boolean overlaps(Integer start1, Integer end1, Integer start2, Integer end2) {
            return Math.max(start1, start2) < Math.min(end1, end2);
        }
    }
}
