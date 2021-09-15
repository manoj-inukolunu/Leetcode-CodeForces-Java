package com.leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;

public class LC1425 {
    public int constrainedSubsetSum(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[]{0, nums[0]});
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i];
            while (!deque.isEmpty() && deque.peekLast()[0] < i - k) {
                deque.poll();
            }
            if (!deque.isEmpty()) {
                int queueMax = deque.peekFirst()[1];
                dp[i] = Math.max(dp[i], queueMax + nums[i]);
                max = Math.max(max, dp[i]);
                while (!deque.isEmpty() && deque.peekFirst()[1] < dp[i]) {
                    deque.poll();
                }
                deque.addLast(new int[]{i, dp[i]});
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3574, -1795, 5928, 9488, -4282};
        int k = 2;
        LC1425 l = new LC1425();
        System.out.println(l.constrainedSubsetSum(arr, k));
    }
}
