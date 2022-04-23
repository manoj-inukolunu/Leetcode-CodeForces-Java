package com.leetcode.hard;

public class LC1955 {
    public int countSpecialSubsequences(int[] nums) {
        int[] arr = new int[3];
        for (int num : nums) {
            if (num == 0) {
                arr[0] = 2 * arr[0] + 1;
            } else if (num == 1) {
                int extendZero = arr[0];
                int extendOne = 2 * arr[1];
                arr[1] = extendOne + extendZero;
            } else if (num == 2) {
                int extendOne = arr[1];
                int extendTwo = 2 * arr[2];
                arr[2] = extendOne + extendTwo;
            }
        }
        return arr[2];
    }

    public static void main(String[] args) {
        LC1955 l = new LC1955();
        System.out.println(l.countSpecialSubsequences(new int[]{0, 1, 2, 0, 1, 2}));
    }
}






