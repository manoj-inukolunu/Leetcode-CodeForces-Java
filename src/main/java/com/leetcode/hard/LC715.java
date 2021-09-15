package com.leetcode.hard;

import java.util.TreeMap;

public class LC715 {
    static class RangeModule {

        TreeMap<Integer, Integer> ranges = new TreeMap<>();

        public RangeModule() {

        }


        public void addRange(int left, int right) {
            Integer l = ranges.floorKey(left);
            Integer r = ranges.floorKey(right);
            if (l != null && ranges.get(l) >= left) {
                left = l;
            }
            if (r != null && ranges.get(r) >= right) {
                right = ranges.get(r);
            }
            ranges.put(left, right);
            ranges.subMap(left, false, right, true).clear();
        }

        public boolean queryRange(int left, int right) {
            Integer floor = ranges.floorKey(left);
            return floor != null && ranges.get(floor) >= right;
        }


        public void removeRange(int left, int right) {
            Integer l = ranges.floorKey(left);
            Integer r = ranges.floorKey(right);
            if (l != null && ranges.get(l) >= left) {
                ranges.put(l, left);
            }
            if (r != null && ranges.get(r) >= right) {
                ranges.put(right, ranges.get(r));
            }
            ranges.subMap(left, true, right, false).clear();
        }

    }


    public static void main(String[] args) {
        RangeModule r = new RangeModule();
        r.addRange(6, 8);
        r.removeRange(7, 8);
        r.removeRange(8, 9);
        r.addRange(8, 9);
        r.removeRange(1, 3);
        r.addRange(1, 8);
        System.out.println(r.queryRange(2, 4));
        System.out.println(r.queryRange(2, 9));
        System.out.println(r.queryRange(4, 6));
    }
}
