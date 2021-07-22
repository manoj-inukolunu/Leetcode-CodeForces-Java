package com.leetcode.common;

import java.util.TreeMap;

public class SortedList {

    int sum = 0;
    int length;
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public int size() {
        return length;
    }

    public void add(int m) {
        map.put(m, map.getOrDefault(m, 0) + 1);
        sum += m;
        length++;
    }

    boolean contains(int m) {
        return map.containsKey(m);
    }

    public int firstKey() {
        return map.firstKey();
    }

    public int lastKey() {
        return map.lastKey();
    }

    public void remove(int key) {
        if (map.containsKey(key)) {
            map.put(key, map.getOrDefault(key, 0) - 1);
            if (map.get(key) <= 0) {
                map.remove(key);
            }
            sum -= key;
            length--;
        }
    }

    public int removeFirst() {
        Integer first = map.firstKey();
        if (first != null) {
            remove(first);
        }
        return first;
    }

    public int removeLast() {
        Integer last = map.lastKey();
        if (last != null) {
            remove(last);
        }
        return last;
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

}
