package com.leetcode.hard;

import java.util.TreeMap;

public class LC732 {
    static class MyCalendarThree {

        TreeMap<Integer, Integer> delta;

        public MyCalendarThree() {
            delta = new TreeMap();
        }

        public int book(int start, int end) {
            delta.put(start, delta.getOrDefault(start, 0) + 1);
            delta.put(end, delta.getOrDefault(end, 0) - 1);

            int active = 0, ans = 0;
            for (int d : delta.values()) {
                active += d;
                if (active > ans) ans = active;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        MyCalendarThree m = new MyCalendarThree();
        System.out.println(m.book(10, 20));
        System.out.println(m.book(50, 60));
        System.out.println(m.book(10, 40));
        System.out.println(m.book(5, 15));
        System.out.println(m.book(5, 10));
        System.out.println(m.book(25, 55));
    }
}
