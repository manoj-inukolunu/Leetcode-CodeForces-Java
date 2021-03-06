package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LC2009 {

    public int minOperations(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i);
        }
        int count = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int min = list.get(i);
            int max = nums.length - 1 + min;
            Integer key = map.floorKey(max);
            int idx = map.get(key);
            int numElements = idx - i + 1;
            count = Math.min(count, nums.length - numElements);
        }
        return count;
    }

    public static void main(String[] args) {
        LC2009 l = new LC2009();
        System.out.println(l.minOperations(new int[]{1, 10, 100, 1000}));
    }
}
