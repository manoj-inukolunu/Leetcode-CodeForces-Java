package com.leetcode.hard;

import java.util.TreeMap;

public class LC862 {

    public int shortestSubarray(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ans = Integer.MAX_VALUE, currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            if (currSum >= k) {
                ans = Math.min(ans, i);
            }
            Integer lower = map.lowerKey(currSum - k);
            while (lower != null) {
                ans = Math.min(ans, i - map.get(lower));
                map.remove(lower);
                lower = map.lowerKey(lower);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;

    }
}
