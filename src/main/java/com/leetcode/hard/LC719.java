package com.leetcode.hard;

import java.util.Arrays;

public class LC719 {

    private boolean valid(int[] nums, int dist, int k) {
        int count = 0, start = 0, end = 0;
        while (start < nums.length || end < nums.length) {
            while (end < nums.length && nums[end] - nums[start] <= dist) {
                end++;
            }
            count += (end - start - 1);
            start++;
        }

        return count >= k;
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int start = 0, end = nums[nums.length - 1] - nums[0];
        int ans = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (valid(nums, mid, k)) {
                end = mid - 1;
                ans = mid;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 6, 1};
        LC719 l = new LC719();
        System.out.println(l.smallestDistancePair(arr, 3));
    }
}
