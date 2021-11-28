package com.leetcode.hard;

public class LC1000 {


    public int mergeStones(int[] stones, int k) {
        int val = breakAndMerge(stones, 0, stones.length - 1, k, k);
        return val == Integer.MAX_VALUE ? -1 : val;
    }


    private int breakAndMerge(int[] arr, int start, int end, int pieces, int k) {

        if (pieces == 1) {
            if (end - start + 1 == k) {
                if (end != start) {
                    return sum(arr, start, end) + sum(arr, start, end);
                }
                return arr[end];
            }
            return (end - start + 1) < k ? Integer.MAX_VALUE :
                    (breakAndMerge(arr, start, end, k, k) + breakAndMerge(arr, start, end, k, k));
        }
        if (start == end) {
            return arr[start];
        }
        if (pieces <= 0 || (end - start + 1) < k) {
            return Integer.MAX_VALUE;
        }
        int best = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            int left = breakAndMerge(arr, start, i, k, k);
            if (i - start + 1 != 1 & left != Integer.MAX_VALUE) {
                left += left;
            }
            if (left != Integer.MAX_VALUE) {
                int right = breakAndMerge(arr, i + 1, end, pieces - 1, k);
                if (right != Integer.MAX_VALUE) {
                    best = Math.min(best, left + right);
                }
            }
        }
        return best;
    }

    private int sum(int[] arr, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        LC1000 l = new LC1000();
        System.out.println(l.mergeStones(new int[]{3, 2, 4, 1}, 2));
    }
}
