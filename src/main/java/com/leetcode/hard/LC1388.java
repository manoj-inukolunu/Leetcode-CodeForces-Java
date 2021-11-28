package com.leetcode.hard;

public class LC1388 {

    public int maxSizeSlices(int[] slices) {
        if (slices.length == 3) {
            return Math.max(slices[0], Math.max(slices[1], slices[2]));
        }
        int best = solve(slices, 1, slices.length - 1, 0);
        return Math.max(best, solve(slices, 0, slices.length - 2, 0));
    }

    private int solve(int[] arr, int start, int end, int count) {
        if (start > end) {
            return 0;
        }
        if (count > arr.length / 3) {
            return 0;
        }
        if (end - start + 1 == 3) {
            return Math.max(arr[start], Math.max(arr[end], arr[start + 1]));
        }
        int best = arr[start] + solve(arr, start + 2, end, count + 1);
        best = Math.max(best, solve(arr, start + 1, end, count));
        return best;
    }

    public static void main(String[] args) {
        LC1388 l = new LC1388();
        System.out.println(l.maxSizeSlices(new int[]{3, 1, 2}));
    }
}
