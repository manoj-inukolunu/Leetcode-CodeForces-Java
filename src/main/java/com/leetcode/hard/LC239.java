package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LC239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peekLast() < i - k + 1) {
                queue.pollLast();
            }
            while (!queue.isEmpty() && nums[queue.peekFirst()] < nums[i]) {
                queue.pollFirst();
            }
            queue.addFirst(i);
            if (i >= k - 1) {
                list.add(nums[queue.peekLast()]);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        LC239 l = new LC239();
        int[] arr = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] ans = l.maxSlidingWindow(arr, 3);
        System.out.println(Arrays.toString(ans));
    }
}
