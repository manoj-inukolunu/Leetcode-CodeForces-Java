package com.leetcode.medium;

import java.util.HashMap;

public class LC1865 {
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] num;
    int[] num1;

    public LC1865(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], map.getOrDefault(nums2[i], 0) + 1);
        }
        this.num = nums2;
        this.num1 = nums1;
    }

    public void add(int idx, int val) {
        int prev = num[idx];
        num[idx] += val;
        map.put(prev, map.get(prev) - 1);
        if (map.get(prev) <= 0) {
            map.remove(prev);
        }
        map.put(num[idx], map.getOrDefault(num[idx], 0) + 1);
    }

    public int count(int tot) {
        int counts = 0;
        for (int i = 0; i < num1.length; i++) {
            int val = tot - num1[i];
            counts += map.getOrDefault(val, 0);
        }
        return counts;
    }
}
