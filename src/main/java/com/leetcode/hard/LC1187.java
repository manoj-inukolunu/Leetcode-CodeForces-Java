package com.leetcode.hard;

import java.util.Arrays;
import java.util.TreeMap;

public class LC1187 {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        arr2 = Arrays.stream(arr2).distinct().toArray();

        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        return solve(arr1, arr2, 0, -1, 0);
    }

    private int solve(int[] arr1, int[] arr2, int idx, int prevIdx, int swapped) {
        if (idx >= arr1.length) {
            return 0;
        }
        int min = Integer.MAX_VALUE / 2;
        int prev = swapped == 1 ? arr2[prevIdx] : arr1[idx - 1];
        int curr = arr1[idx];
        if (curr > prev) {
            min = solve(arr1, arr2, idx + 1, idx, 0);
            Integer higher = map.higherKey(prev);
            if (higher != null) {
                min = Math.min(min, 1 + solve(arr1, arr2, idx + 1, map.get(higher), 1));
            }
        } else {
            Integer higher = map.higherKey(prev);
            if (higher != null) {
                min = Math.min(min, 1 + solve(arr1, arr2, idx + 1, map.get(higher), 1));
            }
        }
        return min;
    }
}
