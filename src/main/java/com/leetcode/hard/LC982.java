package com.leetcode.hard;

import java.util.HashMap;

public class LC982 {

    public int countTriplets(int[] nums) {
        HashMap<Integer, Integer> maskMap = new HashMap<>();
        for (int num : nums) {
            int mask = 0;
            for (int i = 0; i < 16; i++) {
                if ((num & (1 << i)) == 0) {
                    mask |= (1 << i);
                }
            }
            int s = mask;
            while (s > 0) {
                maskMap.put(s, maskMap.getOrDefault(s, 0) + 1);
                s = (s - 1) & mask;
            }
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                int val = nums[i] & nums[j];
                if (val == 0) {
                    count += nums.length;
                } else {
                    int mask = 0;
                    for (int k = 0; k < 16; k++) {
                        if ((val & (1 << k)) > 0) {
                            mask |= (1 << k);
                        }
                    }
                    count += maskMap.getOrDefault(mask, 0);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 7, 3};
        LC982 l = new LC982();
        System.out.println(l.countTriplets(arr));
        System.out.println(Integer.parseInt("110", 2) & Integer.parseInt("1", 2));
    }
}






