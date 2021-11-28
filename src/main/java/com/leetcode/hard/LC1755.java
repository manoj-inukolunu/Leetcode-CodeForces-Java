package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class LC1755 {
    HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();

    public int minAbsDifference(int[] nums, int goal) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        int mid = nums.length / 2;
        subsetSum(Arrays.copyOfRange(nums, 0, mid), 0, a, 0, 0);
        subsetSum(Arrays.copyOfRange(nums, mid, nums.length), 0, b, 0, 0);

        return 1;
    }

    private void subsetSum(int[] nums, int idx, List<Integer> list, int sum, int size) {
        if (idx >= nums.length) {
            TreeSet<Integer> set = map.getOrDefault(size, new TreeSet<>());
            set.add(sum);
            map.put(size, set);
            return;
        }
        subsetSum(nums, idx + 1, list, sum + nums[idx], size + 1);
        subsetSum(nums, idx + 1, list, sum, size);
    }


    public static void main(String[] args) {
        LC1755 l = new LC1755();
        int[] arr = new int[]{3, 9, 7, 3};
        System.out.println(l.minAbsDifference(arr, Arrays.stream(arr).sum() / 2));
    }
}
