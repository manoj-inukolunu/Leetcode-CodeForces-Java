package com.leetcode.hard;


import java.util.Arrays;

public class LC2003 {

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        return new int[]{};
    }

    public static void main(String[] args) {
        LC2003 l = new LC2003();
        int[] parents = new int[]{-1, 0, 0, 2};
        int[] nums = new int[]{1, 2, 3, 4};
        int[] ans = l.smallestMissingValueSubtree(parents, nums);
        System.out.println(Arrays.toString(ans));
    }
}

