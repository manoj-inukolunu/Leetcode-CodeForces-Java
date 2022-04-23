package com.leetcode.hard;

import java.util.Arrays;

public class LC1547 {

    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int[] newCuts = new int[cuts.length + 2];
        newCuts[0] = 0;
        newCuts[newCuts.length - 1] = n;
        for (int i = 0; i < cuts.length; i++) {
            newCuts[i + 1] = cuts[i];
        }
        return solve(newCuts, n, 0, newCuts.length - 1);
    }

    private int solve(int[] cuts, int totalLen, int start, int end) {
//        System.out.println(start + " " + end);
        if (start >= end) {
            return 0;
        }
        if (start == 0 && end == 1) {
            return 0;
        }
        if (start == cuts.length - 2 && end == cuts.length - 1) {
            return 0;
        }
        if (start == end - 1) {
            return 0;
        }
        int stickLen;
        if (start == 0 && end == cuts.length - 1) {
            stickLen = totalLen;
        } else if (start == 0) {
            stickLen = cuts[end];
        } else if (end == cuts.length - 1) {
            stickLen = totalLen - cuts[start];
        } else {
            stickLen = cuts[end] - cuts[start];
        }
        int min = Integer.MAX_VALUE / 2;
        for (int i = start + 1; i < end; i++) {
            int left = solve(cuts, totalLen, start, i);
            int right = solve(cuts, totalLen, i, end);
            if (left != Integer.MAX_VALUE / 2 && right != Integer.MAX_VALUE / 2) {
                min = Math.min(min, stickLen + solve(cuts, totalLen, start, i) + solve(cuts, totalLen, i, end));
            }
        }
        return min;
    }

    public static void main(String[] args) {
        LC1547 l = new LC1547();
        System.out.println(l.minCost(9, new int[]{5, 6, 1, 4, 2}));
    }
}
