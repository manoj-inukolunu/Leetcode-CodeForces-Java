package com.leetcode.hard;

import java.util.LinkedList;
import java.util.TreeMap;

public class LC1823 {


    static class MKAverage {

        class SortedList {
            int sum = 0;
            int length;
            TreeMap<Integer, Integer> map = new TreeMap<>();

            void add(int m) {
                map.put(m, map.getOrDefault(m, 0) + 1);
                sum += m;
                length++;
            }

            boolean contains(int m) {
                return map.containsKey(m);
            }

            int lastKey() {
                return map.lastKey();
            }

            void remove(int key) {
                if (map.containsKey(key)) {
                    map.put(key, map.getOrDefault(key, 0) - 1);
                    if (map.get(key) <= 0) {
                        map.remove(key);
                    }
                    sum -= key;
                    length--;
                }
            }

            int removeFirst() {
                Integer first = map.firstKey();
                if (first != null) {
                    remove(first);
                }
                return first;
            }

            int removeLast() {
                Integer last = map.lastKey();
                if (last != null) {
                    remove(last);
                }
                return last;
            }

            boolean isEmpty() {
                return map.isEmpty();
            }
        }

        int m, k;
        SortedList left = new SortedList();
        SortedList mid = new SortedList();
        SortedList right = new SortedList();
        LinkedList<Integer> list = new LinkedList<>();

        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
        }

        public void addElement(int num) {
            list.add(num);
            if (left.isEmpty() || num <= left.lastKey()) {
                left.add(num);
            } else if (mid.isEmpty() || num <= mid.lastKey()) {
                mid.add(num);
            } else {
                right.add(num);
            }
            if (list.size() > m) {
                int toRemove = list.removeFirst();
                if (left.contains(toRemove)) {
                    left.remove(toRemove);
                } else if (mid.contains(toRemove)) {
                    mid.remove(toRemove);
                } else {
                    right.remove(toRemove);
                }
            }
            if (left.length > k) {
                mid.add(left.removeLast());
            } else if (left.length < k && !mid.isEmpty()) {
                left.add(mid.removeFirst());
            }
            if (mid.length > m - 2 * k) {
                right.add(mid.removeLast());
            } else if (mid.length < m - 2 * k && !right.isEmpty()) {
                mid.add(right.removeFirst());
            }
        }

        public int calculateMKAverage() {
            if (left.length + mid.length + right.length < m) {
                return -1;
            }
            return Math.floorDiv(mid.sum, mid.length);
        }
    }
}
