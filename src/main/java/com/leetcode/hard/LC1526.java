package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LC1526 {

    public int minNumberOperations(int[] target) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            List<Integer> list = map.getOrDefault(target[i], new ArrayList<>());
            list.add(i);
            map.put(target[i], list);
        }
        return solve(target, map, 0, target.length - 1, 0);
    }

    private int solve(int[] target, HashMap<Integer, List<Integer>> map, int start, int end, int increments) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return increments < target[start] ? target[start] - increments : 0;
        }

        int minInRange = rmq(target, start, end);
        int count = (minInRange - increments);
        List<Integer> list = map.get(minInRange);
        int val = Collections.binarySearch(list, start);
        int idx = val;
        if (val < 0) {
            idx = -val;
        }
        count += solve(target, map, start + 1, list.get(idx) - 1, minInRange);
        int last = -1;
        for (int i = idx; i + 1 < list.size() && i + 1 <= end; i++) {
            int nextStart = list.get(i) + 1;
            int nextEnd = list.get(i + 1) - 1;
            count += (solve(target, map, nextStart, nextEnd, minInRange));
            last = i;
        }
//        count += solve(target, map, );
        return count;
    }

    private int rmq(int[] arr, int start, int end) {
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 1, 2};
        LC1526 l = new LC1526();
        System.out.println(l.minNumberOperations(arr));
    }
}
