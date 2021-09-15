package com.leetcode.hard;

import java.util.TreeSet;

public class LC1675 {

    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            if (num % 2 != 0) {
                set.add(num * 2);
            } else {
                set.add(num);
            }
        }
        int ans = Integer.MAX_VALUE;
        while (true) {
            int max = set.last();
            ans = Math.min(ans, max - set.first());
            if (max % 2 != 0) {
                return ans;
            } else {
                set.remove(max);
                max /= 2;
                set.add(max);
            }
        }

    }
}
